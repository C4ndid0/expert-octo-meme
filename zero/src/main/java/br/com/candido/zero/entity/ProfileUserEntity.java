package br.com.candido.zero.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ZERO_PROFILE_USER_")
@Getter
@Setter
@NoArgsConstructor
public class ProfileUserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ID_USER")
  private UserEntity user;
  @ManyToOne
  @JoinColumn(name = "ID_PROFILE")
  private ProfileEntity profile;

}
