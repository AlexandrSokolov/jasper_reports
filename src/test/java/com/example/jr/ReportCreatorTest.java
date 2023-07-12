package com.example.jr;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = { ReportConfiguration.class })
public class ReportCreatorTest {

  public static final String REPORT_PATH_4_COMPARISON = "reports/original.pdf";

  @Autowired
  private ReportCreator reportCreator;

  @Value("${jr.file.name}")
  private String pdfReportName;

  @Test
  public void testCreatePdfReport() throws IOException {
    ReportData reportData = new ReportData(new HashMap<>());

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
