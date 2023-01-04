package com.jeremias.paddlechampion.controller;

import com.jeremias.paddlechampion.auth.dto.ResponseUserDto;
import com.jeremias.paddlechampion.auth.dto.UserRegistrationDto;
import com.jeremias.paddlechampion.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    @ApiOperation(value = "Get User Info",
            notes = "Gets all the user information, including tournaments that manages and teams that belongs to.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID is invalid (must use numbers value only)"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable(name = "id") Long idUser) {

        ResponseUserDto dto = userService.getUser(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(dto);


    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/{id}")
    @ApiOperation(value = "Updates your Info",
            notes = "Updates the user information, only can update your own information")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID is invalid (User number values only)"),
            @ApiResponse(code = 401, message = "You can only update your own information"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<ResponseUserDto> updateUser(@PathVariable(name = "id") Long idUser, @RequestBody UserRegistrationDto dto) {

        ResponseUserDto responseUserDto = userService.update(idUser, dto);

        return ResponseEntity.status(HttpStatus.OK).body(responseUserDto);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an user",
            notes = "Deletes an user, you can only delete your own user unless you are an Admin")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "User ID is invalid (User number values only)"),
            @ApiResponse(code = 401, message = "You can only delete your own user"),
            @ApiResponse(code = 404, message = "User not found")})
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long idUser) {

      if (userService.delete(idUser)) {
          return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
      } else {

          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User cannot be deleted");
      }


    }

}
