package com.jeremias.paddlechampion.auth.service;

import com.jeremias.paddlechampion.auth.dto.ResponseUserDto;
import com.jeremias.paddlechampion.auth.dto.UserAuthDto;
import com.jeremias.paddlechampion.entity.RoleEntity;
import com.jeremias.paddlechampion.entity.UserEntity;
import com.jeremias.paddlechampion.enumeration.RoleName;
import com.jeremias.paddlechampion.mapper.UserMap;
import com.jeremias.paddlechampion.mapper.exception.RepeatedUsername;
import com.jeremias.paddlechampion.repository.RoleRepository;
import com.jeremias.paddlechampion.repository.UserRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustomService implements UserDetailsService {

  @Autowired
  private UserRepository userRepo;
  @Autowired
  private RoleRepository roleRepo;
  @Autowired
  private UserMap userMap;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepo.findByEmail(email);
    if (userEntity == null) {
      throw new UsernameNotFoundException("username or password not found");
    }
    return UserDetailsImpl.build(userEntity);
  }

  public ResponseUserDto save(@Valid ResponseUserDto userDto) throws RepeatedUsername {
    if (userRepo.findByEmail(userDto.getEmail()) != null) {
      throw new RepeatedUsername("repeated user");
    }
    UserEntity entity = userMap.userAuthDto2Entity(userDto);

    RoleEntity role = roleRepo.findByName(RoleName.ROLE_USER);
    entity.setRole(role);

    UserEntity entitySaved = this.userRepo.save(entity);


    ResponseUserDto responseUserDto = userMap.userAuthEntity2Dto(entitySaved);


    return responseUserDto;


  }

  public void saveAdmin(@Valid UserAuthDto userDto) throws RepeatedUsername {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    if (userRepo.findByEmail(userDto.getEmail()) != null) {
      throw new RepeatedUsername("Username repetido");
    }
    UserEntity userEntity = new UserEntity();
    userEntity.setEmail(userDto.getEmail());
    userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    userEntity.setFirstName(userDto.getFirstName());
    userEntity.setLastName(userDto.getLastName());
    userEntity.setRole(roleRepo.findByName(RoleName.ROLE_ADMIN));
    userEntity = this.userRepo.save(userEntity);
  }


}
