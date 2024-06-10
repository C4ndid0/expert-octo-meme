package br.com.candido.zero.service;

import br.com.candido.zero.dto.UserProfileDTO;

import br.com.candido.zero.entity.UserProfileEntity;
import br.com.candido.zero.repository.UserProfileRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDTO getUserProfileByID(Long id) {
        return new UserProfileDTO(userProfileRepository.findById(id).get());
    }

    public List<UserProfileDTO> getAllProfiles() {
        List<UserProfileEntity> userProfileEntities = userProfileRepository.findAll();
        return userProfileEntities.stream().map(UserProfileDTO::new).toList();
    }

    public void insertUserProfile(UserProfileDTO userProfileDTO) {
        UserProfileEntity userProfileEntity = new UserProfileEntity(userProfileDTO);
        userProfileRepository.save(userProfileEntity);
    }

    public UserProfileDTO updateUserProfile(UserProfileDTO userProfile) {
        UserProfileEntity userProfileEntity = new UserProfileEntity(userProfile);
        return new UserProfileDTO(userProfileRepository.save(userProfileEntity));
    }

    public void deleteUserProfile(Long id) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(id).get();
        userProfileRepository.delete(userProfileEntity);
    }

}
