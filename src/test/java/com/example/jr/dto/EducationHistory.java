package com.example.jr.dto;

public class EducationHistory {
  String educationDate;
  String educationNote;

  public EducationHistory() {
  }

  public EducationHistory(String educationDate, String educationNote) {
    this.educationDate = educationDate;
    this.educationNote = educationNote;
  }

  public String getEducationDate() {
    return educationDate;
  }

  public void setEducationDate(String educationDate) {
    this.educationDate = educationDate;
  }

  public String getEducationNote() {
    return educationNote;
  }

  public void setEducationNote(String educationNote) {
    this.educationNote = educationNote;
  }
}
