package entities;

public class Employee {
	private Integer id;
	private String name;
	private Double salary;
	
	public Employee(Integer id, String name, Double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Double getSalary() {
		return salary;
	}
	
	public void increaseSalaryValue(double value) {
		salary += value;
	}
	
	public void increaseSalaryPercentage(double percentage) {
		salary += (salary * percentage/100);
	}
	
	public void decreaseSalaryValue(double value) {
		salary -= value;
	}
	
	public void decreaseSalaryPercentage(double percentage) {
		salary -= (salary * percentage/100);
	}
	
	public String toString() {
		return String.format("%03d, %s, U$%.2f ", id, name, salary);
	}
}
