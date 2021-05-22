package com.inovvet.app.config.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.service.AccountsService;
import com.inovvet.core.entity.conta.dto.ContaDTO;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ContexoFilter implements Filter {

	@Autowired
	private AccountsService accountsService;

	public static final String CONTA_TOKEN = "token";
	public static final String LOJA_TOKEN = "seller-active";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletRequest httpRequest = (HttpServletRequest) req;

		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Map<String, String> headers = Collections.list(httpRequest.getHeaderNames()).stream()
				.collect(Collectors.toMap(h -> h, httpRequest::getHeader));

		String token = headers.get(CONTA_TOKEN);

		String url = req.getRequestURL().toString();

		if (token == null) {

			String lojaToken = request.getParameter(LOJA_TOKEN);

			if (lojaToken != null && !lojaToken.isEmpty()) {
				ContaDTO contaDTO = accountsService.carregarContaLoja(lojaToken);

				Contexto.setContaSchema(contaDTO.getBaseDados());

				httpResponse.setHeader(CONTA_TOKEN, contaDTO.getToken());
				httpResponse.setHeader(LOJA_TOKEN, lojaToken);

				HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
				requestWrapper.addHeader(CONTA_TOKEN, contaDTO.getToken());
				requestWrapper.addHeader(LOJA_TOKEN, lojaToken);

				chain.doFilter(requestWrapper, response);
			} else {
				chain.doFilter(request, response);
			}

		} else {

			if (!url.contains("deploy") && token != null && !token.isEmpty()) {

				ContaDTO contaDTO = accountsService.carregarConta(token);

				Contexto.setContaSchema(contaDTO.getBaseDados());
			}

			chain.doFilter(request, response);

		}

	}

	public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
		/**
		 * construct a wrapper for this request
		 * 
		 * @param request
		 */
		public HeaderMapRequestWrapper(HttpServletRequest request) {
			super(request);
		}

		private Map<String, String> headerMap = new HashMap<String, String>();

		/**
		 * add a header with given name and value
		 * 
		 * @param name
		 * @param value
		 */
		public void addHeader(String name, String value) {
			headerMap.put(name, value);
		}

		@Override
		public String getHeader(String name) {
			String headerValue = super.getHeader(name);
			if (headerMap.containsKey(name)) {
				headerValue = headerMap.get(name);
			}
			return headerValue;
		}

		/**
		 * get the Header names
		 */
		@Override
		public Enumeration<String> getHeaderNames() {
			List<String> names = Collections.list(super.getHeaderNames());
			for (String name : headerMap.keySet()) {
				names.add(name);
			}
			return Collections.enumeration(names);
		}

		@Override
		public Enumeration<String> getHeaders(String name) {
			List<String> values = Collections.list(super.getHeaders(name));
			if (headerMap.containsKey(name)) {
				values.add(headerMap.get(name));
			}
			return Collections.enumeration(values);
		}

	}

}
