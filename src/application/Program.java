package application;

import java.util.Locale;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

import entities.Employee;


public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		List<Employee> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.printf("===== EMPLOYEE MANAGEMENT =====\n");
			System.out.println("1 - SET A NEW EMPLOYEE");
			System.out.println("2 - REMOVE AN EMPLOYEE");
			System.out.println("3 - LIST ALL THE EMPLOYEES");
			System.out.println("4 - INCREASE or DECREASE AN EMPLOYEE SALARY");
			System.out.println("5 - GET AN EMPLOYEE DATA");
			System.out.println("0 - EXIT");
			int opt = 0;
			while(true) {
				opt = sc.nextInt();
				if(opt > 5 || opt < 0) {
					System.out.print("* Choose a valid option!");
				}else {
					break;
				}	
			}
			
			int position = 0;
			switch(opt) {
				case 1:
					list.add(registerNewEmployee(list, sc));
					break;
				case 2:
					if(list.size() > 0) {
						clearScreen();
						System.out.printf("\n===== REMOVING AN EMPLOYEE =====\n");
						position = searchEmployee(list, sc);
						if(position != -1) {
							list.remove(position);
							clearScreen();
							System.out.printf("SUCCESSFUL REMOVING!\n\n");
						}else
							System.out.printf("\n\nThere's no employee with this ID registered!\n");
					}else {
						clearScreen();
						System.out.println("There is no employee registered!\n");
					}
					break;
				case 3:
					if(list.size() > 0) {
						getEmployeesList(list);
					}else {
						clearScreen();
						System.out.println("There is no employee registered!\n");
					}
					break;
				case 4:
					if(list.size() > 0) {
						list = changingEmployeeSalary(list, sc);
					}else {
						clearScreen();
						System.out.println("There is no employee registered!\n");
					}
					break;
				case 5:
					if(list.size() > 0) {
						position = searchEmployee(list, sc);
						if(position != -1) {
							System.out.println(list.get(position));
						}else
							System.out.printf("\n\nThere's no employee with this ID registered!\n");
					}else {
						clearScreen();
						System.out.println("There is no employee registered!\n");
					}
					break;
				case 0:
					sc.close();
					System.exit(0);
			}
		}
	}
	
	public static List<Employee> changingEmployeeSalary(List<Employee> list, Scanner sc){
		clearScreen();
		
		System.out.println("1 - INCREASE AN EMPLOYEE SALARY");
		System.out.println("2 - DECREASE AN EMPLOYEE SALARY");
		System.out.println("What do you want to do? ");
		int opt = 0;
		while(true) {
			opt = sc.nextInt();
			if(opt == 1 || opt == 2) {
				break;
			}else {
				System.out.println("Please, choose a valid option!");
			}
		}
		
		int position = searchEmployee(list, sc);
		if(position == -1) {
			clearScreen();
			System.out.printf("There's no employee with this ID!\n");
			return list;	
		}else {
			clearScreen();
			System.out.printf("====================================\n");
			System.out.println("== EMPLOYEE DATA FOUND SUCCESSFUL ==");
			System.out.println(list.get(position));
			System.out.printf("\n====================================\n\n");
		}
		
		int optSalary = 0;
		double adjustEmployeeSalary = 0.0;
		switch(opt) {
			case 1:
				System.out.println("1 - INCREASE BY VALUE");
				System.out.println("2 - INCREASE BY PERCENTAGE");
				while(true) {
					optSalary = sc.nextInt();
					if(optSalary == 1) {
						System.out.println("Enter the value to increase: ");
						adjustEmployeeSalary = sc.nextDouble();
						list.get(position).increaseSalaryValue(adjustEmployeeSalary);
						break;
					}else if(optSalary == 2) {
						System.out.println("Enter the percentage to increase: ");
						adjustEmployeeSalary = sc.nextDouble();
						list.get(position).increaseSalaryPercentage(adjustEmployeeSalary);
						break;
					}else {
						System.out.println("Please, choose a valid option!");
					}
				}
				break;
			
			case 2:
				System.out.println("1 - DECREASE BY VALUE");
				System.out.println("2 - DECREASE BY PERCENTAGE");
				while(true) {
					optSalary = sc.nextInt();
					if(optSalary == 1) {
						System.out.println("Enter the value to decrease: ");
						adjustEmployeeSalary = sc.nextDouble();
						list.get(position).decreaseSalaryValue(adjustEmployeeSalary);
						break;
					}else if(optSalary == 2) {
						System.out.println("Enter the percentage to decrease: ");
						adjustEmployeeSalary = sc.nextDouble();
						list.get(position).decreaseSalaryPercentage(adjustEmployeeSalary);
						break;
					}else {
						System.out.println("Please, choose a valid option!");
					}
				}
				break;
				
		}
		
		return list;
	}
	
	public static Employee registerNewEmployee(List<Employee> list, Scanner sc) {
		Locale.setDefault(Locale.US);
		
		clearScreen();
		sc.nextLine();
		System.out.printf("\n===== SETTING A NEW EMPLOYEE =====\n");
		System.out.println("ID: ");
		Integer id = 0;
		int idValid = 0;
		while(idValid != 1) {
			id = sc.nextInt();
			if(list.size() > 0) {
				for(int i=0; i<list.size(); i++) {
					if(id == list.get(i).getId()) {
						System.out.println("Already an employee with this ID! ");
						System.out.println("Enter another one: ");
						break;
					}else if(i == list.size()-1) {
						idValid = 1;
					}
				}
			}else {
				idValid = 1;
			}
		}
		sc.nextLine();
		System.out.println("Name: ");
		String name = sc.nextLine();
		
		System.out.println("Salary: ");
		Double salary = sc.nextDouble();
		
		Employee employee = new Employee(id, name, salary);
		
		clearScreen();
		System.out.printf("SUCCESSFUL REGISTERED!\n\n");
		return employee;
	}
	
	public static void getEmployeesList(List<Employee> list) {
		clearScreen();
		System.out.printf("\n\n===== EMPLOYEES LIST =====\n");
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println();
		
	}
	
	public static int searchEmployee(List<Employee> list, Scanner sc) {
		System.out.println("Enter the Employee's ID: ");
		int id = sc.nextInt();
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getId() == id) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static void clearScreen() {
		for(int i=0; i<20; i++) {
			System.out.println(" ");
		}
	}
	
}
