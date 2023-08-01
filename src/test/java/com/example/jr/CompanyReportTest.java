package com.example.jr;

import com.example.jr.dto.CompanyHistory;
import com.example.jr.dto.EducationHistory;
import com.example.jr.dto.Employee;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyReportTest extends ReportAbstractBaseTest {

  @Test
  public void tableElementReport() {
    String templatePath = "jr/Company_report.jrxml";
    String reportName = "Company.Report.pdf";

    generateReport(templatePath, reportName);
  }

  @Override
  public ReportData data() {
    Map<String, Object> map = new HashMap<>();
    map.put(
      "companyHistory",
      ReportData.asJrDatasource(List.of(
        new CompanyHistory("2001", "Created in Germany"),
        new CompanyHistory("2010", "Extended to covere all Europe"),
        new CompanyHistory("2015", "Covered Asia"))));
    return new ReportData(
      map,
      ReportData.asJrDatasource(
        List.of(
          new Employee("Alex", 25, null),
          new Employee("John", 30, ReportData.asJrDatasource(
            List.of(
              new EducationHistory(
                "2011", "School"),
              new EducationHistory("2015", "Univercity")))))));
  }
}

