package com.soap_hrm.business.mappers;
import org.modelmapper.ModelMapper;

public class GenericMapping<Entity, DTO> {

    public DTO mapEntityToDto(Entity entity, Class<DTO> dtoClass){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, dtoClass);
    }

    public Entity mapDtoToEntity(DTO dto, Class<Entity> entityClass){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entityClass);
        
    }


}
