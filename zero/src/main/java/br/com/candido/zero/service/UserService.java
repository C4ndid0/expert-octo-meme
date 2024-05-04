package br.com.candido.zero.service;

import br.com.candido.zero.dto.UserDTO;
import br.com.candido.zero.entity.UserEntity;
import br.com.candido.zero.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDTO findById(Long id){
        return new UserDTO(userRepository.findById(id).get());
    }
    public List<UserDTO> listAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public void creatUser(UserDTO user){
        UserEntity userEntity = new UserEntity(user);
        userRepository.save(userEntity);
    }

    public UserDTO update(UserDTO user) {
        UserEntity userEntity = new UserEntity(user);
        return new UserDTO(userRepository.save(userEntity));
    }

    public void delete(Long id){
        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
    }



}
