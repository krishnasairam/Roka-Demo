package com.techmojo.roka;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  private Long mobile;
  
  private String name;
  
  private String address;

  private String email;

  public Long getMobile() {
    return mobile;
  }

  public void setMobile(Long id) {
    this.mobile = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
	return address;
  }

  public void setAddress(String address) {
	this.address = address;
  }
  
  @Override
  public String toString() {
      return "EmployeeEntity [mobileid=" + mobile + ", Name=" + name + 
              ", Address=" + address + ", email=" + email   + "]";
  }
}