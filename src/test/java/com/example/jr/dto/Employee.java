package com.example.jr.dto;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

public class Employee {

  String name;

  Integer age;

  JRDataSource educationHistory = new JREmptyDataSource();

  public Employee() {
  }

  public Employee(String name, Integer age, JRDataSource educationHistory) {
    this.name = name;
    this.age = age;
    this.educationHistory = educationHistory;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public JRDataSource getEducationHistory() {
    return educationHistory;
  }

  public void setEducationHistory(JRDataSource educationHistory) {
    this.educationHistory = educationHistory;
  }
}
