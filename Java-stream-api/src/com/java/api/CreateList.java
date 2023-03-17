package com.java.api;

import java.util.ArrayList;
import java.util.List;

public class CreateList {
	
	
	public static List<Employee> getEmployeeList(){
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee(1, "sunita", 32, "Female","Sales", 2017, 37000));
		list.add(new Employee(8, "harman", 38, "Male", "Recuriter",2014, 127000));
		list.add(new Employee(5, "kittu", 32, "Female", "Developer",2016, 57000));
		list.add(new Employee(9, "Rohan", 32, "Male", "Developer",2011, 67000));
		list.add(new Employee(4, "Jenny", 32, "Female", "Sales",2010, 78000));
		list.add(new Employee(3, "Arman", 32, "Male", "Account", 2022, 12000));
		list.add(new Employee(2, "jyoti", 32, "Female", "Technology", 2024, 56000));
		list.add(new Employee(6, "divya", 32, "Female", "Developer",2006, 3000));
		
		return list;
	}

}
