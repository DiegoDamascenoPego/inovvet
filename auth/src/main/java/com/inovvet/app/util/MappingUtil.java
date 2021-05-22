package com.inovvet.app.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;

@Component
public class MappingUtil {

	@Autowired
	private ModelMapper mapper;

	public void map(Object source, Object destination) {
		try {
			mapper.map(source, destination);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_CONVERTER_REGISTRO);
		}
	}

	public <D> D map(Object source, Type destinationType) {
		try {
			return mapper.map(source, destinationType);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_CONVERTER_REGISTRO);
		}
	}

	public <T, U> List<U> map(final List<T> source, final Class<U> destType) {

		try {
			final List<U> dest = new ArrayList<>();

			for (T element : source) {
				dest.add(mapper.map(element, destType));
			}
			return dest;

		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_CONVERTER_REGISTRO);
		}

	}

}
