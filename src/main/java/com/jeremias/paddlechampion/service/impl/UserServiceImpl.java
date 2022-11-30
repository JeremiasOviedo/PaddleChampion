package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.entity.UserEntity;
import com.jeremias.paddlechampion.mapper.TeamMap;
import com.jeremias.paddlechampion.mapper.UserMap;
import com.jeremias.paddlechampion.repository.UserRepository;
import com.jeremias.paddlechampion.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private UserMap userMap;
  @Autowired
  private TeamMap teamMap;


  public UserDto save(UserDto dto) {

    UserEntity entity = userMap.userDto2Entity(dto);

    userRepo.save(entity);

    return dto;
  }

  @Override
  public List<UserDto> listAllUsers() {

    List<UserEntity> entities = userRepo.findAll();
    List<UserDto> dtos = userMap.userEntityList2DtoList(entities);

    return dtos;
  }

  @Override
  public UserDto findById(Long id) {

    UserEntity entity = userRepo.findByUserId(id);
    UserDto user = userMap.userEntity2Dto(entity);

    return user;
  }

  @Override
  public PageDto<UserDto> findAllUsers(Pageable pageable, HttpServletRequest request) {
    return null;
  }

  @Override
  public void delete(Long id) {

    if (!userRepo.existsById(id)) {
      System.out.println("User doesn't exist");
    } else {
      userRepo.deleteById(id);
    }
  }

  @Override
  public UserDto update(Long id, UserDto dto) {
    UserEntity entity = userRepo.findByUserId(id);

    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setEmail(dto.getEmail());
    entity.setCategory(dto.getCategory());
    entity.setTeams(teamMap.teamDto2EntityList(dto.getTeams()));

    userRepo.save(entity);

    return dto;
  }
}