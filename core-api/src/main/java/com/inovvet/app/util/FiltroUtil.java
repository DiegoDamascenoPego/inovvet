package com.inovvet.app.util;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.inovvet.core.entity.base.dto.FiltroDTO;

@Component
public class FiltroUtil<T> {

    @Autowired
    @Lazy
    private ModelMapper mapper;

    private static ModelMapper staticMapper;


    @PostConstruct
    private void init() {
        FiltroUtil.staticMapper = mapper;
    }

    public static <T> Example<T> getByExample(FiltroDTO filtro, Class<T> entityClass) {
        if (filtro == null) {
            filtro = new FiltroDTO<>();
        }

        if (filtro.getObj() == null) {
            try {
                filtro.setObj(entityClass.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues();

        T entity = staticMapper.map(filtro.getObj(), entityClass);
        return matcher == null ? Example.of(entity) : Example.of(entity, matcher);
    }

}