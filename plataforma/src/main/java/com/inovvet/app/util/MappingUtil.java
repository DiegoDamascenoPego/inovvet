package com.inovvet.app.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;

@Component
public class MappingUtil {

	private static final String[] ignoreFields = { "id", "dataCadastro", "dataAtualizacao" };

	@Autowired
	private ModelMapper mapper;

	public void map(Object source, Object destination) {
		try {
			mapper.map(source, destination);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_CONVERTER_REGISTRO);
		}
	}

	public void copy(Object source, Object target) {
		BeanUtils.copyProperties(source, target, ignoreFields);
	}

	public void copy(Object source, Object target, String ...args) {
		String[] args2 = Arrays.copyOf(args, args.length + 1);
				 args2 = Arrays.copyOf(args, args.length + ignoreFields.length + 1);
				 
		BeanUtils.copyProperties(source, target, args2);
	}

	public <T> T copy(Object source, Class<? extends T> clazz) throws Exception {
		T objeto = clazz.getConstructor().newInstance();
		BeanUtils.copyProperties(source, objeto);

		return objeto;
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
