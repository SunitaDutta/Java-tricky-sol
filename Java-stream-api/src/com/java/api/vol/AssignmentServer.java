package com.java.api.vol;

import java.util.*;
import java.util.stream.Collectors;

public class AssignmentServer {

	// A map of task ids to Tasks.
	Map<Integer, Task> tasks;

	// A list of all volunteers in the server.
	ArrayList<Volunteer> volunteers;

	// A map of Volunteers to assigned Tasks.
	Map<Volunteer, Set<Task>> assignments;

	public AssignmentServer() {
		tasks = new HashMap<>();
		volunteers = new ArrayList<>();
		assignments = new HashMap<>();
	}

	public void importTasksFromCSV(String csvFileName) {
		this.tasks = Util.loadTasks(csvFileName);
	}

	public void importVolunteersFromCSV(String csvFileName) {
		this.volunteers.addAll(Util.loadVolunteers(csvFileName, this.tasks));
	}

	/**
	 * Returns a List of the volunteers who have indicated interest in the given
	 * task.
	 */
	public List<Volunteer> getInterestedVolunteers(Task task) {

		// TODO: Implement this method. See the README for more details.

		// Fetch all volunteers from CSV file
		this.importVolunteersFromCSV("volunteers.csv");

		List<Volunteer> volunteerList = new ArrayList<>();

		// Iterate over volunteers list and
		// match the interested task ID with task ID
		for (Volunteer vol : volunteers) {
			for (Task t : vol.getInterestedTasks()) {
				if (t.getID() == task.getID()) {
					volunteerList.add(vol);
					break;
				}
			}
		}

		return volunteerList;
	}

	/**
	 * Returns a List of Tasks sorted by desirability.
	 */
	public List<Task> getTasksByDesirability() {

		// TODO: Implement this method. See the README for more details.

		List<Task> taskSortedList = new ArrayList<Task>();
		HashMap<Task, Double> map = new HashMap<>();

		this.importVolunteersFromCSV("volunteers.csv");
		this.importTasksFromCSV("tasks.csv");

		for (Volunteer vol : volunteers) {
			List<Task> interestedTasks = vol.getInterestedTasks();
			for (int i = 0; i < interestedTasks.size(); i++) {
				double cal = 1 / (i + 1);
				int taskId = interestedTasks.get(i).getID();
				Task task = this.tasks.get(taskId);
				map.put(task, map.getOrDefault(task, 0.0) + cal);
			}
		}

		taskSortedList = map.entrySet().stream().sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
				.map(k -> k.getKey()).collect(Collectors.toList());

		return taskSortedList;
	}

	/**
	 * Assigns Tasks to Volunteers by inserting them into the assignment map, in
	 * order of desirability. Tasks are assigned to the first Volunteer with
	 * interest. If there are no interested Volunteers, they are assigned to the
	 * first available Volunteer.
	 */
	public void assignTasks() {
		for (Task task : getTasksByDesirability()) {
			List<Volunteer> interestedVolunteers = getInterestedVolunteers(task);
			if (interestedVolunteers.size() > 0) {
				// Assign task to the first interested volunteer
				assignTask(task, interestedVolunteers.get(0));
			} else {
				// Assign the task to the first available volunteer
				assignTask(task, volunteers.get(0));
			}
		}
	}

	/**
	 * Assigns Tasks to Volunteers based on their interest.
	 */
	public void assignTasksImproved() {

		// TODO: Implement this method. See the README for more details.

	}

	/**
	 * Adds the given task to the specified volunteer's Set of assigned Tasks.
	 */
	public void assignTask(Task task, Volunteer volunteer) {
		if (!this.assignments.containsKey(volunteer)) {
			this.assignments.put(volunteer, new HashSet<>());
		}
		this.assignments.get(volunteer).add(task);
	}

	public ArrayList<Task> getTasks() {
		return new ArrayList<Task>(this.tasks.values());
	}

	public Map<Volunteer, Set<Task>> getAssignments() {
		return this.assignments;
	}

	public ArrayList<Volunteer> getVolunteers() {
		return this.volunteers;
	}
}