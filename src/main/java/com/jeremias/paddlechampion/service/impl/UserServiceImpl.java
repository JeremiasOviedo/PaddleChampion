package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.auth.dto.ResponseUserDto;
import com.jeremias.paddlechampion.auth.dto.UserRegistrationDto;
import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.entity.RoleEntity;
import com.jeremias.paddlechampion.entity.TeamEntity;
import com.jeremias.paddlechampion.entity.UserEntity;
import com.jeremias.paddlechampion.enumeration.RoleName;
import com.jeremias.paddlechampion.mapper.TeamMap;
import com.jeremias.paddlechampion.mapper.UserMap;
import com.jeremias.paddlechampion.mapper.exception.ParamNotFound;
import com.jeremias.paddlechampion.repository.RoleRepository;
import com.jeremias.paddlechampion.repository.TeamRepository;
import com.jeremias.paddlechampion.repository.UserRepository;
import com.jeremias.paddlechampion.service.IUserService;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.jeremias.paddlechampion.service.IUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserMap userMap;
    @Autowired
    private TeamMap teamMap;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    IUtilService util;


    public UserDto save(UserDto dto) {

        UserEntity entity = userMap.userDto2Entity(dto);

        userRepo.save(entity);

        return dto;
    }

    @Override
    public PageDto<ResponseUserDto> listAllUsers(Pageable page, HttpServletRequest request) {

        PageDto<ResponseUserDto> pageDto = new PageDto<>();
        Map<String,String> links = new HashMap<>();
        List<ResponseUserDto> listDto = new ArrayList<>();
        Page<UserEntity> elements = userRepo.findAll(page);

        elements.getContent().forEach(element -> listDto.add(userMap.userAuthEntity2Dto(element)));
        links.put("next",elements.hasNext()?util.makePaginationLink(request,page.getPageNumber()+1):"");
        links.put("previous",elements.hasPrevious()?util.makePaginationLink(request,page.getPageNumber()-1):"");

        pageDto.setContent(listDto);
        pageDto.setLinks(links);

        return pageDto;
    }
    @Override
    public ResponseUserDto getUser(Long id) {

        UserEntity entity = userRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("User ID invalid"));
        ResponseUserDto user = userMap.userAuthEntity2Dto(entity);

        return user;

    }

    @Override
    public boolean delete(Long id) {

        UserEntity entity = userRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("User ID is invalid"));

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        RoleEntity isAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
        UserEntity user = userRepo.findByEmail(userEmail);


        if (user == entity || user.getRole() == isAdmin) {
            List<TeamEntity> teams = entity.getTeams();

            for (TeamEntity team : teams) {
                team.deleteUserFromTeam(entity);
                teamRepository.save(team);
            }

            RoleEntity role = entity.getRole();
            role.getUsers().remove(entity);

            userRepo.delete(entity);

            return true;
        }

        return false;
    }

    @Override
    public ResponseUserDto update(Long id, UserRegistrationDto dto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        UserEntity entity = userRepo.findById(id).orElseThrow(
                () -> new ParamNotFound("User ID is invalid"));

        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepo.findByEmail(userEmail);

        if (user != entity) {
            throw new RuntimeException("You can only update your own information");
        }


        if (dto.getFirstName() != null) {
            entity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            entity.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getCategory() != null) {
            entity.setCategory(dto.getCategory());
        }
        if (dto.getPassword() != null) {

            if (dto.getPassword().equals(dto.getPasswordConfirm())) {
                entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

            } else throw new ParamNotFound("Passwords must coincide");

        }


        UserEntity entitySaved = userRepo.save(entity);

        return userMap.userAuthEntity2Dto(entity);
    }
}
