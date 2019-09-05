package model;

import java.io.Serializable;

public class Member implements Serializable{
	private int id;
	private String name;
	private String kana;
	private String email;
	private int age;
	public Member() {}
	public Member(String name, String kana, String email, int age) {
		super();
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.age = age;
	}
	public Member(int id, String name, String kana, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.kana = kana;
		this.email = email;
		this.age = age;
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
	public String getKana() {
		return kana;
	}
	public void setKana(String kana) {
		this.kana = kana;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
