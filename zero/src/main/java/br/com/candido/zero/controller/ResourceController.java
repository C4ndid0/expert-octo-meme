package br.com.candido.zero.controller;

import br.com.candido.zero.dto.ResourceDTO;
import br.com.candido.zero.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/resource")
@CrossOrigin
public class ResourceController {

    private ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public List<ResourceDTO> getAllResources(){
        return resourceService.getAllResources();
    }
    @GetMapping("{id}")
    public ResourceDTO getResourceByID(@PathVariable Long id){
        return resourceService.getByID(id);
    }

    @PostMapping
    public void insertResource(@RequestBody ResourceDTO resourceDTO){
        resourceService.insertResource(resourceDTO);
    }

    @PutMapping
    public ResourceDTO updateResource(@RequestBody ResourceDTO resource ){
        return resourceService.updateResource(resource);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable("id") Long id ) {
        resourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }
}
