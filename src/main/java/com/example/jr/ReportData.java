package com.example.jr;

import java.util.Map;

public record ReportData (
  /* Cannot be immutable! */ Map<String, Object> reportParameters) {
}
