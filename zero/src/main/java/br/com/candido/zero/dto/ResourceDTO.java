package br.com.candido.zero.dto;


import br.com.candido.zero.entity.ResourceEntity;
import org.springframework.beans.BeanUtils;

public class ResourceDTO {

    private Long id;

    private String name;

    private String key;

    public ResourceDTO(){}
    public ResourceDTO(ResourceEntity resource){

        BeanUtils.copyProperties(resource, this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
