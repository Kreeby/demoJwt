package net.test.jwtappdemo.rest;

import net.test.jwtappdemo.dto.UserDTO;
import net.test.jwtappdemo.model.User;
import net.test.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UsersRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UsersRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('A')")
    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "id") Long id){

        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDTO result = UserDTO.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}