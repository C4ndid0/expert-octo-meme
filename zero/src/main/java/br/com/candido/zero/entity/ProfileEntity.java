package br.com.candido.zero.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import br.com.candido.zero.dto.ProfileDTO;

@Entity
@Table(name = "ZERO_PROFILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String description;

    public ProfileEntity(ProfileDTO profileDTO) {
        BeanUtils.copyProperties(profileDTO, this);
    }
}
