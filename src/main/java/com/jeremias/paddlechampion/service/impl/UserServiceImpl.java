package com.jeremias.paddlechampion.service.impl;

import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.UserDto;
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

  @Override
  public List<UserDto> listAllUsers() {
    return null;
  }

  @Override
  public UserDto findById(Long id) {
    return null;
  }

  @Override
  public PageDto<UserDto> findAllUsers(Pageable pageable, HttpServletRequest request) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public UserDto update(Long id, UserDto dto) {
    return null;
  }
}
