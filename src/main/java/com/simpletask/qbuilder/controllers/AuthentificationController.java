package com.simpletask.qbuilder.controllers;

import com.simpletask.qbuilder.DTO.UserDTO;
import com.simpletask.qbuilder.entities.User;
import com.simpletask.qbuilder.services.AuthentificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Api(value ="Authentication operations ", description = "Operations used for signing up a new user")
@RestController
public class AuthentificationController {
    private AuthentificationService authentificationService;


    @Autowired
    public AuthentificationController(AuthentificationService authentificationService) {
        this.authentificationService=authentificationService;
    }

    @ApiOperation(value = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("auth/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody User user) {
        UserDTO u = authentificationService.addUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

}
