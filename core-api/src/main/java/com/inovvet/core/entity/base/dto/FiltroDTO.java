package com.inovvet.core.entity.base.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
public class FiltroDTO<T> implements Serializable {

    private static final long serialVersionUID = 2992104586182583565L;

    private T obj;

    private int page = 0;

    private int size = 7;

    private Sort sort;

}
