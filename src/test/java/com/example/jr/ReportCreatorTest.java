package com.example.jr;

import net.sf.jasperreports.engine.JRParameter;
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
import java.util.Locale;
import java.util.Map;

@SpringBootTest
@ContextConfiguration(classes = { ReportConfiguration.class })
public class ReportCreatorTest {

  public static final String REPORT_PATH_4_COMPARISON = "reports/original.pdf";

  @Autowired
  private ReportCreator reportCreator;

  @Value("${jr.file.name}")
  private String pdfReportName;

  @Test
  public void testCreatePdfReport() {
    Map<String, Object> map = new HashMap<>();
    map.put(JRParameter.REPORT_LOCALE, new Locale("de", "DE") );

    ReportData reportData = new ReportData(map);

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
