package com.greatlearning.emr.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "firstName")
  private String firstName;

  @Column(name = "lastName")
  private String lastName;

  @Column(name = "email")
  private String email;

  public Employee() {

  }

  public Employee (String firstName, String lastName,String email) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

public void setFirstName(String firstName2) {
	// TODO Auto-generated method stub
	
}

public void setLastName(String lastName2) {
	// TODO Auto-generated method stub
	
}

public void setEmail(String email2) {
	// TODO Auto-generated method stub
	
}
}  