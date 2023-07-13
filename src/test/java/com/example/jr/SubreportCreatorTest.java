package com.example.jr;

import com.example.jr.dto.SingleFieldBean;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SubreportCreatorTest extends ReportAbstractBaseTest {
  @Test
  public void tableElementReport() {
    String templatePath = "jr/complex_subreports/Subreport_with_table_element_via_parameters.jrxml";
    String reportName = "Subreport.pdf";

    generateReport(templatePath, reportName);
  }

  @Override
  public ReportData data() {
    Map<String, Object> map = new HashMap<>();
    map.put(
      "TEST_PARAMETER", "Custom test value");
    map.put(JRParameter.REPORT_LOCALE, new Locale("de", "DE") );


    return new ReportData(
      map,
      new JRBeanCollectionDataSource(List.of(
        new SingleFieldBean("field 1 value 1"),
        new SingleFieldBean("field 2 value 20"),
        new SingleFieldBean("field 3 value 300"),
        new SingleFieldBean("field 4 value 4000"))));
  }
}
