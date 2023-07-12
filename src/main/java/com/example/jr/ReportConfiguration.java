package com.example.jr;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Configuration
@ComponentScan("com.example.jr")
public class ReportConfiguration {

  @Value("${jr.template.name}")
  private String templateName;

  @Bean
  public JasperReport jasperReport() {
    String resourcePath = "jr/" + templateName;
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
}
