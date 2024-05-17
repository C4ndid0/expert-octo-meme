package br.com.candido.zero.dto;

import br.com.candido.zero.entity.ProfileEntity;
import br.com.candido.zero.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
@NoArgsConstructor
@Getter
@Setter
public class ProfileUserDTO {
    private Long id;
    private UserEntity user;
    private ProfileEntity profile;

    public ProfileUserDTO(ProfileEntity profileEntity) {
        BeanUtils.copyProperties(profileEntity, this);
    }
}
