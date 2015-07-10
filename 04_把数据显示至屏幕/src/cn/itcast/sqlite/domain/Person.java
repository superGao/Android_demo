package cn.itcast.sqlite.domain;

public class Person {

	int _id;
	String name;
	String phone;
	int salary;
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Person(int _id, String name, String phone, int salary) {
		super();
		this._id = _id;
		this.name = name;
		this.phone = phone;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "name=" + name + ", phone=" + phone
				+ ", salary=" + salary;
	}
	
	
}
