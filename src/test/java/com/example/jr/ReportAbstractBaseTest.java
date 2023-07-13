package com.example.jr;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public abstract class ReportAbstractBaseTest {

  abstract public ReportData data();

  public void generateReport(
    String templatePath,
    String reportName) {
    JasperReport jasperReport = jasperReport(templatePath);

    ReportData reportData = data();

    try (InputStream report = filledInReport(jasperReport, reportData)) {
      saveUnderTarget(report, reportName);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public void saveUnderTarget(InputStream reportStream, String fileName) {
    try {
      File reportPath = new File("target", fileName);
      FileUtils.copyInputStreamToFile(
        reportStream,
        reportPath);
      System.out.println("Check the content of `" + reportPath.getAbsolutePath() + "`");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  public JasperReport jasperReport(String resourcePath) {
    try (InputStream jasperStream = Thread.currentThread().getContextClassLoader()
      .getResourceAsStream(resourcePath)) {

      return JasperCompileManager.compileReport(
        Optional.ofNullable(jasperStream)
          .orElseThrow(() -> new IllegalStateException("Could not get resource as stream  from: '"
            + resourcePath + "'")));
    } catch (IOException | JRException e) {
      throw new IllegalStateException(e);
    }
  }

  public InputStream filledInReport(
    final JasperReport jasperReport,
    final ReportData reportData) {
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
