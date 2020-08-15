package com.simpletask.qbuilder.controllers;

import com.simpletask.qbuilder.DTO.PaginationSearchDTO;
import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(value ="User operations", description = "Operations for managing users, reseting passwords and filtering users")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService=userService;}


    @ApiOperation(value = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> findAll() {
        return new ResponseEntity<List<UserDTO>>(userService.findAll(),HttpStatus.OK);
    }

    @ApiOperation(value = "Get all tests for a specific interviewers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("interviewers")
    public ResponseEntity<Page<UserDTO>> getInterviewers(@RequestBody PaginationSearchDTO paginationSearchDTO){
        return new ResponseEntity<>(userService.getInterviewers(paginationSearchDTO),HttpStatus.OK);
    }

    @ApiOperation(value = "Find an user by his email")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/users/email/{userMmail}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable ("user_email") String userEmail){
        return new ResponseEntity<>(userService.findByEmail(userEmail), HttpStatus.OK);
    }

    @ApiOperation(value = "Reset user's password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/password/change/{id}")
    public ResponseEntity<UserDTO> changePassword(@RequestParam("oldPassword") String oldPassword,
                                                  @RequestParam("newPassword") String newPassword,
                                                  @RequestParam("confirmPassword") String confirmPassword,
                                                  @PathVariable("id") Integer userId){

       try{
           return new ResponseEntity<>(userService.changePassword(oldPassword,newPassword,confirmPassword,userId),HttpStatus.OK);
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }
}
