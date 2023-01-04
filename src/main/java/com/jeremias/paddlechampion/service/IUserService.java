package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.auth.dto.ResponseUserDto;
import com.jeremias.paddlechampion.auth.dto.UserRegistrationDto;
import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

  List<UserDto> listAllUsers();

  ResponseUserDto getUser(Long id);

  PageDto<ResponseUserDto> findAllUsers(Pageable pageable, HttpServletRequest request);

  boolean delete(Long id);

  ResponseUserDto update(Long id, UserRegistrationDto dto);

}

