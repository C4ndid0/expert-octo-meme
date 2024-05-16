package br.com.candido.zero.dto;

import br.com.candido.zero.entity.ProfileEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDTO {
    private Long id;
    private String description;

    public ProfileDTO(ProfileEntity profileEntity) {
        BeanUtils.copyProperties(profileEntity, this);
    }
}
