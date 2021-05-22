package com.inovvet.app.util;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class FileUtil {
	
	public static String DIRETORIOCORRENTE = ".";
	public static String DIRETORIOTEMPORARIO = "/temp";
	
	public static File diretorioCorrente() {
		return new File(DIRETORIOCORRENTE);
	}
	
	public static File diretorioTemp() {
		File retorno = new File(FileUtil.diretorioCorrente().getAbsolutePath() + File.separator + DIRETORIOTEMPORARIO);
		if (!retorno.exists()) {
			retorno.mkdir();
		}
		return retorno;
	}
	
	public static File diretorioTempSemNomeTemp(String nomeArquivo) {
		File retorno = new File(FileUtil.diretorioCorrente().getAbsolutePath() + File.separator + "/" + nomeArquivo);
		if (!retorno.exists()) {
			retorno.mkdir();
		}
		return retorno;
	}
	
	public static void apagarArquivosPastaTempSeteDias() {
		File diretorio = new File(FileUtil.diretorioTemp().getAbsolutePath());
		LocalDate data = LocalDate.now().minusDays(7);
		for (File file : diretorio.listFiles()) {
			LocalDate dataArquivo = Instant.ofEpochMilli(file.lastModified()).atZone(ZoneId.systemDefault()).toLocalDate();
			if (data.isAfter(dataArquivo)) {
				file.delete();
			}
		}
	}

}
