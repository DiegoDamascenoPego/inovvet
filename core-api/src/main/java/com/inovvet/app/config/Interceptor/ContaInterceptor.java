package com.inovvet.app.config.Interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.service.cadastro.loja.ContaService;
import com.inovvet.core.service.cadastro.loja.LojaService;

public class ContaInterceptor extends HandlerInterceptorAdapter {

	public static final String CONTA_TOKEN = "token";
	public static final String LOJA_TOKEN = "seller-active";

	@Autowired
	private ContaService service;

	@Autowired
	private LojaService lojaService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = request.getHeader(CONTA_TOKEN);

		String lojaToken = request.getHeader(LOJA_TOKEN);

		if (Contexto.getConta() != null && Contexto.getContaSchema() != null) {
			return true;
		}

		if (token == null && lojaToken == null) {

			Map variables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

			token = (String) variables.get(CONTA_TOKEN);

			lojaToken = (String) variables.get(LOJA_TOKEN);
		}

		try {
			if (FunctionUtil.isEmpty(token)) {
				throw new ServiceException(EnumError.CONTA_INVALIDA);
			} else {
				Contexto.SetConta(service.carregar(token));
				Contexto.setContaSchema(Contexto.getContaSchema());

				if (!FunctionUtil.isEmpty(lojaToken)) {
					Contexto.SetLoja(lojaService.carregarLoja(lojaToken));
				}
				return true;
			}

		} catch (Exception e) {
			response.reset();
			response.sendError(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
	}

}
