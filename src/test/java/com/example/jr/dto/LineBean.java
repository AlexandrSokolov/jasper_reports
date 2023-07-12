package com.example.jr.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LineBean {
  private String name;
  private LocalDateTime date;
  private BigDecimal money;
  private Long longField;
  private Double doubleField;

  public LineBean(String name, LocalDateTime date, BigDecimal money, Long longField, Double doubleField) {
    this.name = name;
    this.date = date;
    this.money = money;
    this.longField = longField;
    this.doubleField = doubleField;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public BigDecimal getMoney() {
    return money;
  }

  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  public Long getLongField() {
    return longField;
  }

  public void setLongField(Long longField) {
    this.longField = longField;
  }

  public Double getDoubleField() {
    return doubleField;
  }

  public void setDoubleField(Double doubleField) {
    this.doubleField = doubleField;
  }
}
