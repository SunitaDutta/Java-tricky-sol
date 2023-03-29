package com.java.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {

	public static List<Employee> list = CreateList.getEmployeeList();

	public static void main(String[] args) {

		// Q1. How many male and female employees are there in the organization?
		findNoOfCountByGender();

		System.out.println();
		// Q2. Print the name of all departments in the organization?
		distinctDepartmentName();

		System.out.println();

		// Q3. What is the average age of male and female employee?
		averageAge();

		System.out.println();

		// Q4. Get the details of highest paid employee in the organization?
		maxSalary();

		System.out.println();

		// Q4. Get the details of highest paid employee By Gender in the organization?
		maxSalaryByGender();
		
		//Q5 How To Merge Two Maps With Same Keys
		mergeTwoMap();
		
		//Q6  Find occurrance of each word and print the occurance in reverse order max- min
		 printWordsByMaxOccurance();
		 
		 
		 //Q7. Sort the employee by salary first and then sort it by name;
		 sortBySalaryByName();

	}

	public static void findNoOfCountByGender() {
		System.out.println("Q1. How many male and female employees are there in the organization?");

		Map<String, Long> map = list.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

		System.out.println(map);
	}

	public static void distinctDepartmentName() {

		System.out.println("Q2. Print the name of all departments in the organization?");

		List<String> res = list.stream().map(Employee::getDepartment).distinct().sorted().collect(Collectors.toList());

		res.forEach(p -> System.out.print(p + ","));
		System.out.println();
	}

	public static void averageAge() {
		System.out.println("Q3. What is the average age of male and female employee?");

		Map<String, Double> map = list.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));

		System.out.println(map);
	}

	public static void maxSalary() {
		System.out.println("Q4. Get the details of highest paid employee in the organization?");

		int maxSalary = list.stream().map(emp -> emp.getSalary()).max(Integer::compare).get();

		System.out.println("max Salary : " + maxSalary);

		Optional<Employee> maxSalaryEmp = list.stream()
				.collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)));
		
		String maxSalaryEmpName = list.stream().collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)))
									.map(e -> e.getName()).get();

		System.out.println("max Salary Emp : " + maxSalaryEmp.get());
		System.out.println("max Salary EmpName : " + maxSalaryEmpName);
	}

	public static void maxSalaryByGender() {
		System.out.println("Q4. Get the details of highest paid employee By Gender in the organization?");

		Map<String, Optional<Employee>> map = list.stream().collect(Collectors.groupingBy(Employee::getGender,
				Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));

		// System.out.println(map);

		Map<String, String> map2 = list.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)), e -> e.get().getName())

				));

		System.out.println(map2);

	}
	
	public static void mergeTwoMap() {
		System.out.println("How To Merge Two Maps With Same Keys");
		
		  //Map-1
	       
	       HashMap<String, Integer> map1 = new HashMap<>();
	        
	       map1.put("Maths", 45);
	       map1.put("Physics", 57);
	       map1.put("Chemistry", 52);
	       map1.put("History", 41);
	        
	       //Map-2
	        
	       HashMap<String, Integer> map2 = new HashMap<>();
	        
	       map2.put("Economics", 49);
	       map2.put("Maths", 42);
	       map2.put("Biology", 41);
	       map2.put("History", 55);
	       
	       
			HashMap<String, Integer> map3 = Stream.of(map1, map2).flatMap(mapr -> mapr.entrySet().stream())
					.collect(Collectors.toMap(Entry::getKey, Entry::getValue, Integer::sum, HashMap::new)); 
	       
	       Map<Object, Object> map4 = Stream.of(map1, map2).flatMap(mapr -> mapr.entrySet().stream())
	    		   							.collect(Collectors.groupingBy(m -> m.getKey(), 
	    		   														Collectors.collectingAndThen(Collectors.summarizingInt(m -> m.getValue()), e -> e.getSum())));
	       
	       Map<Object, Integer> map5 = Stream.of(map1, map2).flatMap(mapr -> mapr.entrySet().stream())
						.collect(Collectors.groupingBy(m -> m.getKey(),Collectors.summingInt(m -> m.getValue())));
	       
	       System.out.println("Map 3 : "+map3);
	       System.out.println("Map 4 : "+map4);
	       System.out.println("Map 5 : "+map5);
	       
	}
	
	public static void printWordsByMaxOccurance() {
		System.out.println("Find occurrance of each word and print the occurance in reverse order max- min");
		
		String s = "You already know that inflation is taking a bigger and bigger bite out of your wallet. Now, it’s going to affect the size of your paycheck in 2023. Even if you get a sizable raise next year, you won’t necessarily take home more money. Many ingredients are baked into the recipe that produces your take-home pay, like deductions for taxes and health care benefits, and your contributions to retirement accounts. Whether you’ll see more money in your paycheck, less or about the same will depend on your circumstances. Here’s a preview of what is changing next year.";
		
		String[] words = s.split(" ");
		
		List<String> result = new ArrayList<>();
		
		
		HashMap<String,Integer> map = new HashMap<String, Integer>();
		
		for(String st : words) {
			map.put(st, map.getOrDefault(st, 0) + 1);
		}
		
		
		
		result = map.entrySet().stream().sorted((m1,m2) ->  m2.getValue().compareTo(m1.getValue()))
				.map(m -> m.getKey()).collect(Collectors.toList());
		
		result.forEach(t -> System.out.println(t));
	}
	
	public static void sortedMapByValue() {
		
		System.out.println("Sort the Map by its value and store the sorted form into another Map");
		
		Map<Integer, String> map = new HashMap<>();

		map.put(11, "ram");
		map.put(23, "arjun");
		map.put(3, "kamal");
		map.put(4, "priya");

		Stream<Entry<Integer, String>> sortedMap = map.entrySet().stream()
				.sorted((m1, m2) -> m1.getValue().compareTo(m2.getValue()));

		LinkedHashMap<Integer, String> res = sortedMap
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (Map.Entry<Integer, String> m : res.entrySet()) {
			System.out.println(m.getKey() + " == " + m.getValue());
		}
	}
	
	public static void sortBySalaryByName() {
		
		System.out.println("Q: Sort the employee by salary first and then sort it by name");
		
		List<Employee> sortedlist = list.stream().sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName)).collect(Collectors.toList());
		
		sortedlist.forEach(System.out::println);
		
	}
	
	public static void CreateListOf_N_Thread(){
		
		 List<Thread> workers =  Stream
					      .generate(() -> new Thread(new MyRunnable(threadId, countDownLatch)))
					      .limit(5)
					      .collect(toList());
           workers.forEach(Thread::start);
	}
}
