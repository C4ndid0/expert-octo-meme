package br.com.candido.zero.service;

import br.com.candido.zero.dto.ResourceDTO;
import br.com.candido.zero.entity.ResourceEntity;
import br.com.candido.zero.repository.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceService {

    private ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceDTO> getAllResources(){
        List<ResourceEntity> resources = resourceRepository.findAll();
        return resources.stream().map(ResourceDTO::new).toList();
    }

    public ResourceDTO getByID(Long id){
        return new ResourceDTO(resourceRepository.findById(id).get());
    }


    public void insertResource(ResourceDTO resourceDTO){
        ResourceEntity resourceEntity = new ResourceEntity(resourceDTO);
        resourceRepository.save(resourceEntity);
    }

    public ResourceDTO updateResource(ResourceDTO resourceDTO){
        ResourceEntity resourceEntity = new ResourceEntity(resourceDTO);
        return new ResourceDTO(resourceRepository.save(resourceEntity));
    }

    public void deleteResource(Long id){
        ResourceEntity resource = resourceRepository.findById(id).get();
        resourceRepository.delete(resource);
    }

}
