package com.studying.soap.entity;


public class Conference {
  private Long id;
  private String subject;
  private String readerName;

  public Conference(Long id, String subject, String readerName) {
    this.id = id;
    this.subject = subject;
    this.readerName = readerName;
  }

  public Long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getReaderName() {
    return readerName;
  }

  public void setReaderName(String readerName) {
    this.readerName = readerName;
  }

}
