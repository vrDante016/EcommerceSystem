package com.produtos.Produtos.service.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GenericConverter {


    private final ModelMapper modelMapper;
    @Autowired
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
