package br.com.candido.zero.service;

import br.com.candido.zero.dto.ProfileDTO;
import br.com.candido.zero.entity.ProfileEntity;
import br.com.candido.zero.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileDTO getProfileByID(Long id) {
        return new ProfileDTO(profileRepository.findById(id).get());
    }

    public List<ProfileDTO> getAllProfiles() {
        List<ProfileEntity> profileEntities = profileRepository.findAll();
        return profileEntities.stream().map(ProfileDTO::new).toList();
    }

    public void insertProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity(profileDTO);
        profileRepository.save(profileEntity);
    }

    public ProfileDTO updateProfile(ProfileDTO profile) {
        ProfileEntity profileEntity = new ProfileEntity(profile);
        return new ProfileDTO(profileRepository.save(profileEntity));
    }

    public void deleteProfile(Long id) {
        ProfileEntity profileEntity = profileRepository.findById(id).get();
        profileRepository.delete(profileEntity);
    }
}
