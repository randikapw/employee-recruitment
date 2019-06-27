package com.poc.employeerecruitment.entity;

import java.util.Optional;

public class Employee {
	
	private int id;
	private String name;	
	private int age;
	private String address;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		Optional<String> optAddress = Optional.ofNullable(address);
		this.address = optAddress.map(s->s.trim()).orElse(null);
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
