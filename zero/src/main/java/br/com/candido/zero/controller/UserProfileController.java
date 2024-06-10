package br.com.candido.zero.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.candido.zero.dto.UserProfileDTO;
import br.com.candido.zero.service.UserProfileService;

@RestController
@RequestMapping(value = "/user-profile")
@CrossOrigin
public class UserProfileController {
    public UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("{id}")
    public UserProfileDTO getUserProfileByID(@PathVariable Long id) {
        return userProfileService.getUserProfileByID(id);
    }

    @GetMapping
    public List<UserProfileDTO> getAllProfiles() {
        return userProfileService.getAllProfiles();
    }

    @PostMapping
    public void insertUserProfile(@RequestBody UserProfileDTO userProfile) {
        userProfileService.insertUserProfile(userProfile);
    }

    @PutMapping
    public UserProfileDTO updateUserProfile(@RequestBody UserProfileDTO userProfile) {
        return userProfileService.updateUserProfile(userProfile);
    }

    @DeleteMapping("{id}")
    public void deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
    }
}
