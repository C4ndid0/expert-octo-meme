package br.com.candido.zero.service;

import br.com.candido.zero.entity.ResourceEntity;
import br.com.candido.zero.repository.ResourceRepository;

import java.util.List;

public class ResourceService {

    private ResourceRepository resourceRepository;
    public ResourceService(ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;
    }

    public List<ResourceEntity> listAllResources(){
        List<ResourceEntity> resources = resourceRepository.findAll();
        return resources.stream().toList();
    }


}
