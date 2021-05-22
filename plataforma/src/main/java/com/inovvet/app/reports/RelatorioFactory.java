package com.inovvet.app.reports;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioFactory {

	@Autowired
	private DataSource dataSource;

	public void toPDF(InputStream jasper, Map<String, Object> parametros, File arquivoPDF) throws ServiceException {
		Connection conn = null;
		try {
			try {
				conn = dataSource.getConnection();
				JasperPrint jasperPrintPDF = JasperFillManager.fillReport(jasper, parametros, conn);
				JasperExportManager.exportReportToPdfFile(jasperPrintPDF, arquivoPDF.getAbsolutePath());

			} finally {
				if (conn != null) {
					conn.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Falha ao Gerar Relatório");
		}

	}

}
