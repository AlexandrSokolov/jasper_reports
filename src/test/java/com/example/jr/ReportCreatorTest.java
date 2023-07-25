package com.example.jr;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = { ReportConfiguration.class })
public class ReportCreatorTest {

  @Autowired
  private ReportCreator reportCreator;

  @Value("${jr.file.name}")
  private String pdfReportName;

  @Test
  public void testCreatePdfReport() {
    Map<String, Object> map = new HashMap<>();
    map.put(JRParameter.REPORT_LOCALE, new Locale("de", "DE") );

    ReportData reportData = new ReportData(
      map,
      new JRMapCollectionDataSource(List.of(
        Map.of(
          "field1", "aaa value, item 1, field 1",
          "field2", "bbb value, item 1, field 2"),
        Map.of(
          "field1", "ccc value, item 2, field 1",
          "field2", "ddd value, item 2, field 2")
      )));

    try(InputStream pdfReport = reportCreator.createPdfReport(reportData)) {
      File newReportPath = new File("target", pdfReportName);
      FileUtils.copyInputStreamToFile(
        pdfReport,
        newReportPath);
      System.out.println("Check the content of `" + newReportPath.getAbsolutePath() + "");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
}
