package br.com.candido.zero.repository;

import br.com.candido.zero.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

}
