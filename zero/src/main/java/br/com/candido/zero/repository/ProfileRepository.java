package br.com.candido.zero.repository;

import br.com.candido.zero.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Long> {
}
