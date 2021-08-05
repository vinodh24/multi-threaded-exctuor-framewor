package com.vinodh.model;


public class Student implements Comparable<Student> {
	private Integer id;
	private String name;
	private Integer age;
	private GenderEnum gender;

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + "]";
	}

	
	public int compare(Student student1, Student student2) {
		String v1 = student1.name;
        String v2 = student2.name;

        if ((v1.compareTo(v2)) >0) return 1;
        else return 0;
	}

	@Override
	public int compareTo(Student student) {
		if(age==student.age)  
			return 0;  
			else if(age>student.age)  
			return 1;  
			else  
			return -1;  
	}
	
	/*@Override
	public int compareTo(Student student) {
		if(name.equalsIgnoreCase(student.getName()))  
			return 0;  
			else  
			return 1;  
	}*/
}
