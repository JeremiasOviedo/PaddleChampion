package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.PageDto;
import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

  List<UserDto> listAllUsers();

  UserDto findById(Long id);

  PageDto<UserDto> findAllUsers(Pageable pageable, HttpServletRequest request);

  void delete(Long id);

  UserDto update(Long id, UserDto dto);

}