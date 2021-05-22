package com.inovvet.app.service;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.entity.Log;
import com.inovvet.app.entity.enumerator.TipoLog;
import com.inovvet.app.entity.enumerator.TipoObjeto;
import com.inovvet.app.repository.LogRepository;
import com.inovvet.app.util.FunctionUtil;

@Service
public class LogService {
	@Autowired
	private LogRepository repository;

	private LoggerContext context = (LoggerContext) LogManager.getContext(false);

	private static Logger logger;

	private DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
			.withLocale(new Locale("pt", "br"));

	public LogService() {
		logger = context.getLogger("Log");
	}

	public void log(Log log) {
		if (!validar(log)) {
			return;
		}

		try {
			repository.save(log);
		} catch (Exception e) {
			e.printStackTrace();
			log.setMensagem(log.getMensagem() + " EXCEPTION DB LOG: " + e.getMessage());
		}

		logArquivo(log);
	}

	public void log(TipoLog tipo, TipoObjeto objeto, String mensagem) {
		if (tipo != null && objeto != null && !FunctionUtil.isEmpty(mensagem)) {
			Log log = new Log(tipo, objeto, mensagem);

			log(log);
		}
	}

	public void logInfo(TipoObjeto objeto, String mensagem) {
		Log log = new Log(TipoLog.INFO, objeto, mensagem);
		log(log);
	}

	public void logAlerta(TipoObjeto objeto, String mensagem) {
		Log log = new Log(TipoLog.ALERTA, objeto, mensagem);
		log(log);
	}

	public void logErro(TipoObjeto objeto, String mensagem) {
		Log log = new Log(TipoLog.ERRO, objeto, mensagem);
		log(log);
	}

	public void logSucesso(TipoObjeto objeto, String mensagem) {
		Log log = new Log(TipoLog.SUCESSO, objeto, mensagem);
		log(log);
	}

//	public List<Log> buscarPorData(LocalDateTime dataInicial, LocalDateTime dataFinal) throws ServiceException {
//		if (dataInicial == null || dataFinal == null) {
//			return new ArrayList<>();
//		}
//
//		try {
//			return repository.findByDataBetweenOrderByData(dataInicial, dataFinal);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(EnumErrorException.FALHA_LOCALIZAR_LOG);
//		}
//	}

//	public List<Log> buscarPorTipo(TipoLog tipo) {
//		if (tipo == null) {
//			return new ArrayList<>();
//		}
//
//		try {
//			return repository.findByTipoOrderByData(tipo);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(EnumErrorException.FALHA_LOCALIZAR_LOG);
//		}
//	}

//	public List<Log> buscarPorObjeto(ObjetoLog objeto) {
//		if (objeto == null) {
//			return new ArrayList<>();
//		}
//
//		try {
//			return repository.findByObjetoOrderByData(objeto);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ServiceException(EnumErrorException.FALHA_LOCALIZAR_LOG);
//		}
//	}

	private void logArquivo(Log log) {
		try {
			final String DELIMITADOR = " | ";

			StringBuilder text = new StringBuilder();
			// data
			text.append(log.getData().format(formatter)).append(DELIMITADOR);

			// tipo
			text.append(log.getTipo()).append(DELIMITADOR);

			// objeto
			text.append(log.getObjeto()).append(DELIMITADOR);

			// mensagem
			text.append(log.getMensagem());

			logger.info(text.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean validar(Log log) {
		if (log == null) {
			return false;
		}

		if (log.getTipo() == null) {
			return false;
		}

		if (log.getObjeto() == null) {
			return false;
		}

		if (log.getData() == null) {
			return false;
		}

		if (FunctionUtil.isEmpty(log.getMensagem())) {
			return false;
		}

		return true;
	}
}
