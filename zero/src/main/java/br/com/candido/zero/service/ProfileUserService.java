package br.com.candido.zero.service;

import br.com.candido.zero.repository.ProfileUserRepository;
import org.springframework.stereotype.Service;


@Service
public class ProfileUserService {
    private ProfileUserRepository profileUserRepository;

    public ProfileUserService(ProfileUserRepository profileUserRepository) {
        this.profileUserRepository = profileUserRepository;
    }


}
