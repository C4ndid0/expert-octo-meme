package br.com.candido.zero.controller;

import br.com.candido.zero.dto.UserDTO;
import br.com.candido.zero.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.listAll();
    }

    @GetMapping("{id}")
    public  UserDTO getUserById(@PathVariable Long id){
        return userService.findById(id);
    }


    @PostMapping
    public void addUser(@RequestBody UserDTO user){
        userService.addUser(user);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO user){
        return userService.update(user);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

}
