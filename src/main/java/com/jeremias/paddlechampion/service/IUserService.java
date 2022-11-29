package com.jeremias.paddlechampion.service;

import com.jeremias.paddlechampion.dto.UserDto;
import com.jeremias.paddlechampion.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

List<UserDto> listAllUsers();

}
