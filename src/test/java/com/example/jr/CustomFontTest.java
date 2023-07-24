package com.example.jr;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CustomFontTest extends ReportAbstractBaseTest {

  @Test
  public void masterReportWithMultipleSubreports() {
    String templatePath = "jr/Custom_fonts.jrxml";
    String reportName = "Custom.font.report.pdf";

    generateReport(templatePath, reportName);
  }

  @Override
  public ReportData data() {
    return new ReportData(new HashMap<>());
  }
}
