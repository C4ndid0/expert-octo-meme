package br.com.candido.zero.dto;

import br.com.candido.zero.entity.UserProfileEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
@Getter
@Setter
public class UserProfileDTO {
    private Long id;
    private UserDTO user;
    private ProfileDTO profile;

    public UserProfileDTO(UserProfileEntity userProfile) {
        BeanUtils.copyProperties(userProfile, this);
        if (userProfile != null && userProfile.getUser() != null) {
            this.user = new UserDTO(userProfile.getUser());
        }
        if (userProfile != null && userProfile.getProfile() != null) {
            this.profile = new ProfileDTO(userProfile.getProfile());
        }
    }
}
