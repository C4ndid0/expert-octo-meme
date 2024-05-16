package br.com.candido.zero.controller;

import br.com.candido.zero.dto.ProfileDTO;
import br.com.candido.zero.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/profile")
@CrossOrigin
public class ProfileController
{
    private ProfileService profileService;
    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    public ProfileDTO getProfileByID(@PathVariable Long id){
        return  profileService.getProfileByID(id);
    }

    @GetMapping
    public List<ProfileDTO> getAllProfiles(){
        return profileService.getAllProfiles();
    }

    @PostMapping
    public void createProfile(@RequestBody ProfileDTO profile){
         profileService.insertProfile(profile);
    }

    @PutMapping
    public ProfileDTO updateProfile(@RequestBody ProfileDTO profile){
        return profileService.updateProfile(profile);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id){
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }

}
