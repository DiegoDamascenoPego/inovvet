package com.inovvet.app.reports;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.util.FileType;
import com.inovvet.app.util.FileUtil;

@Component
public class RelatorioImp implements Relatorio, RelatorioType, RelatorioConfig, RelatorioSource, RelatorioFinalize {

	@Autowired
	private RelatorioFactory factory;

	private String nameFile = UUID.randomUUID().toString();;

	private File file;

	private Map<String, Object> parameters;

	private String extension;

	private String source;

	private String getNameFile() {
		return this.nameFile.toString().concat(this.extension);
	}

	private File getFile() {
		return new File(FileUtil.diretorioTemp().getAbsolutePath().concat(File.separator).concat(this.getNameFile()));
	}

	@Override
	public RelatorioConfig name(String value) {
		this.nameFile = value;
		return this;
	}

	@Override
	public RelatorioConfig source(String value) {
		this.source = value;
		return this;
	}

	@Override
	public RelatorioFinalize build() {

		FileUtil.apagarArquivosPastaTempSeteDias();
		this.file = getFile();

		factory.toPDF(this.getClass().getResourceAsStream(source), this.parameters, this.file);
		return this;
	}

	@Override
	public RelatorioSource pdf() {
		this.extension = FileType.PDF;
		return this;
	}

	@Override
	public RelatorioSource excel() {
		this.extension = FileType.EXCEL;
		return this;
	}

	@Override
	public RelatorioType configure() {
		this.parameters = new HashMap<String, Object>();
		this.parameters.put("DATAIMPRESSAO",
				LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)).concat(" " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
		this.parameters.put("LOJA", Contexto.getLoja().getNomeFantasia());
		return this;
	}

	@Override
	public RelatorioConfig parameters(Map<String, Object> values) {
		if (values != null) {
			this.parameters.putAll(values);
		}
		return this;
	}

	public String get() {
		return this.file.getAbsolutePath();
	}

}
