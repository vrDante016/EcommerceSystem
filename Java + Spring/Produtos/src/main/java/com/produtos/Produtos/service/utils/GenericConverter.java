package com.produtos.Produtos.service.utils;

import org.modelmapper.ModelMapper;

public class GenericConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public GenericConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    public <D, E> D convertToDTO(E entity, Class<D> dtoClass){
        return modelMapper.map(entity, dtoClass);
    }

    public <D, E> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }


}
