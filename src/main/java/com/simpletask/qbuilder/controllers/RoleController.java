package com.simpletask.qbuilder.controllers;

import com.simpletask.qbuilder.DTO.RoleDTO;
import com.simpletask.qbuilder.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Api(value ="Roles operations", description = "Operations for managing user roles")
@RestController
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) { this.roleService=roleService; }


    @ApiOperation(value = "Returns a list of all user roles")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved data"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("roles")
    public ResponseEntity<List<RoleDTO>> getAll(){
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
}
