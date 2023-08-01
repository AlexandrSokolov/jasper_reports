package com.example.jr.dto;

public class CompanyHistory {
  String companyHistoryDate;
  String companyHistoryComment;

  public CompanyHistory() {
  }

  public CompanyHistory(String companyHistoryDate, String companyHistoryComment) {
    this.companyHistoryDate = companyHistoryDate;
    this.companyHistoryComment = companyHistoryComment;
  }

  public String getCompanyHistoryDate() {
    return companyHistoryDate;
  }

  public void setCompanyHistoryDate(String companyHistoryDate) {
    this.companyHistoryDate = companyHistoryDate;
  }

  public String getCompanyHistoryComment() {
    return companyHistoryComment;
  }

  public void setCompanyHistoryComment(String companyHistoryComment) {
    this.companyHistoryComment = companyHistoryComment;
  }
}
