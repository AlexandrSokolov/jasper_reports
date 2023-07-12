package com.example.jr;

import com.example.jr.dto.LineBean;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TableElementReportTest {

  @Test
  public void tableElementReport() {
    String templatePath = "jr/Table_element.jrxml";
    String reportName = "table.element.pdf";

    JasperReport jasperReport = jasperReport(templatePath);

    ReportData reportData = data();

    try (InputStream report = filledInReport(jasperReport, reportData)) {
      saveUnderTarget(report, reportName);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

  private ReportData data() {
    String DATASOURCE_PARAMETER = "CUSTOM_TABLE_PARAMETER";


    Map<String, Object> map = new HashMap<>();
    map.put(
      DATASOURCE_PARAMETER,
      new JRBeanCollectionDataSource(
      List.of(
        new LineBean("Test 1", LocalDateTime.now(), new BigDecimal(25.99), 199L, 21.99),
        new LineBean("Test 2", LocalDateTime.now(), new BigDecimal(35.99), 399L, 121.99),
        new LineBean("Test 3", LocalDateTime.now(), new BigDecimal(35.9995), 4399L, 121.999)
      )));
    return new ReportData(map);
  }

  private void saveUnderTarget(InputStream reportStream, String fileName) {
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

  private JasperReport jasperReport(String resourcePath) {
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

  private InputStream filledInReport(
    final JasperReport jasperReport,
    final ReportData reportData) {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

      JasperPrint jasperPrint = JasperFillManager.fillReport(
        jasperReport,
        reportData.reportParameters(), //cannot be immutable
        new JREmptyDataSource());

      JasperExportManager.exportReportToPdfStream(
        jasperPrint,
        outputStream);

      return new ByteArrayInputStream(outputStream.toByteArray());
    } catch (JRException | IOException e) {
      throw new IllegalStateException(e);
    }
  }
}

