package dto;

import java.io.Serializable;

public class MemberDto implements Serializable { //직렬화
	private String id;
	private String pwd;
	private String name;
	private int age;
	private String birth;
	private String address;
	private int height;
	
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}

	public MemberDto(String id, String pwd, String name, int age, String birth,String address, int height) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.age = age;
		this.birth=birth;
		this.address = address;
		this.height = height;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public void setBirth() {
		this.birth=birth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", name=" + name + ", age=" + age + ", birth=" + birth
				+ ", address=" + address + ", height=" + height + "]";
	}
	
	
}
