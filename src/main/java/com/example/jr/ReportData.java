package com.example.jr;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;
import java.util.Map;

public record ReportData (
  /* Cannot be immutable! */ Map<String, Object> reportParameters,
                             JRDataSource mainDatasource) {
  public ReportData(Map<String, Object> reportParameters) {
    this(reportParameters, new JREmptyDataSource());
  }

  public static JRDataSource asJrDatasource(List<Object> items) {
    return items.isEmpty() ? new JREmptyDataSource()
      : new JRBeanCollectionDataSource(items);
  }
}
