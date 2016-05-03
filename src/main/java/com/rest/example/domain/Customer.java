package com.rest.example.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Customer")
public class Customer {
	
	private int id;
	private String name;
	private String address;
	private int age;
	
	public Customer() {
		super();
	}
	
	public Customer(String name, String address, int age) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}

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
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	

}
