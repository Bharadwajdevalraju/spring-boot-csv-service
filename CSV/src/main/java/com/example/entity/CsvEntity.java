package com.example.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CsvEntity {
	
@Id
private String userName;
private String password;
private String col1;
private String col2;
private String col3;
private String col4;
private String col5;
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCol1() {
	return col1;
}
public void setCol1(String col1) {
	this.col1 = col1;
}
public String getCol2() {
	return col2;
}
public void setCol2(String col2) {
	this.col2 = col2;
}
public String getCol3() {
	return col3;
}
public void setCol3(String col3) {
	this.col3 = col3;
}
public String getCol4() {
	return col4;
}
public void setCol4(String col4) {
	this.col4 = col4;
}
public String getCol5() {
	return col5;
}
public void setCol5(String col5) {
	this.col5 = col5;
}

}
