package br.com.candido.zero.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.candido.zero.entity.UserProfileEntity;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {
}
