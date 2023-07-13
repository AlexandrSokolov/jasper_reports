package com.example.jr;

import com.example.jr.dto.LineBean;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TableElementReportTest extends ReportAbstractBaseTest {

  @Test
  public void tableElementReport() {
    String templatePath = "jr/Table_element.jrxml";
    String reportName = "table.element.pdf";

    generateReport(templatePath, reportName);
  }

  @Override
  public ReportData data() {
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

    map.put(JRParameter.REPORT_LOCALE, new Locale("de", "DE") );

    return new ReportData(map);
  }
}

