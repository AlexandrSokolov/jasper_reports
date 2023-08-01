package com.example.jr;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ReportCreator {

  @Autowired
  private JasperReport jasperReport;

  public InputStream createPdfReport(final ReportData reportData) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      JasperPrint jasperPrint = JasperFillManager.fillReport(
        jasperReport,
        reportData.reportParameters(), //cannot be immutable
        reportData.mainDatasource());

      JasperExportManager.exportReportToPdfStream(
        jasperPrint,
        outputStream);

      return new ByteArrayInputStream(outputStream.toByteArray());
    } catch (JRException | IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
