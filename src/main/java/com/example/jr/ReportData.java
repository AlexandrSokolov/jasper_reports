package com.example.jr;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

import java.util.Map;

public record ReportData (
  /* Cannot be immutable! */ Map<String, Object> reportParameters,
                             JRDataSource mainDatasource) {
  public ReportData(Map<String, Object> reportParameters) {
    this(reportParameters, new JREmptyDataSource());
  }
}
