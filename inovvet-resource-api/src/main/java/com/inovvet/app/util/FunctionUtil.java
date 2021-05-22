package com.inovvet.app.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

public class FunctionUtil {
	public static boolean isEmpty(String texto) {
		return (texto == null || texto.trim().equals(""));
	}

	public static boolean isEmpty(Collection<?> list) {
		return (list == null || list.isEmpty() || list.size() == 0);
	}

	public static boolean isEmpty(Number numero) {
		if (numero == null) {
			return true;
		}

		if (numero instanceof Integer && numero.intValue() <= 0) {
			return true;
		}

		if (numero instanceof Long && numero.longValue() <= 0) {
			return true;
		}

		if (numero instanceof BigDecimal && ((BigDecimal) numero).equals(BigDecimal.ZERO)) {
			return true;
		}

		if (numero instanceof BigInteger && ((BigInteger) numero).equals(BigInteger.ZERO)) {
			return true;
		}

		return false;
	}

	public static boolean validarCPF(String cpf) {
		if (isEmpty(cpf)) {
			return false;
		}

		try {
			CPFValidator validator = new CPFValidator();
			validator.assertValid(cpf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean validarCNPJ(String cnpj) {
		if (isEmpty(cnpj)) {
			return false;
		}

		try {
			CNPJValidator validator = new CNPJValidator();
			validator.assertValid(cnpj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean validarCEP(String cep) {
		if (isEmpty(cep)) {
			return false;
		}

		String CEP_PATTERN = "[0-9]{5}[0-9]{3}";

		Pattern pattern = Pattern.compile(CEP_PATTERN, Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(cep);
		return matcher.matches();
	}

	public static boolean isCnpj(String value) {
		return FunctionUtil.validarCNPJ(value);
	}

	public static boolean isCpf(String value) {
		return FunctionUtil.validarCPF(value);
	}

	public static boolean validarEmail(String email) {
		if (isEmpty(email)) {
			return false;
		}

		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static String removerAcentuacoes(String texto) {
		texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
		texto = texto.replaceAll("[^\\p{ASCII}]", "");
		return texto;
	}

	public static String removerMascaraTexto(String texto) {
		if (isEmpty(texto)) {
			return texto;
		}
		return texto.replaceAll("[^0-9a-zA-Z]", "");
	}

	public static boolean isNumber(String s) {
		char[] c = s.toCharArray();
		boolean d = true;
		for (int i = 0; i < c.length; i++) {
			if (!Character.isDigit(c[i])) {
				d = false;
				break;
			}
		}
		return d;
	}

	public static final LocalDate parseLocaldate(String data) {
		if (data == "" || data.isEmpty() || !data.contains("/")) {
			return null;
		}

		LocalDate novaData = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return novaData;
	}

	public static final String parseLocaldate(LocalDate data) {
		if (data == null) {
			return null;
		}

		String novaData = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		return novaData;
	}

	public static final String getToken() {

		return UUID.randomUUID().toString().replace("-", "");

	}

	public static final String converter(List<String> elements) {
		return String.join(",", elements);
	}
	
	public static final String uUid() {

		return UUID.randomUUID().toString();
	}
	
}
