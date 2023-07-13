package com.example.jr.dto;

public class SingleFieldBean {
  private String testField;

  public String getTestField() {
    return testField;
  }

  public void setTestField(String testField) {
    this.testField = testField;
  }

  public SingleFieldBean(String testField) {
    this.testField = testField;
  }
}
