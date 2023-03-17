    package com.java.api.vol;
import java.util.*;

public class Volunteer {
    private String name;
    private ArrayList<Task> interestedTasks;

    public Volunteer(String name) {
        this.name = name;
        interestedTasks = new ArrayList<>();
    }

    public List<Task> getInterestedTasks() {
        return this.interestedTasks;
    }

    public void addInterestedTask(Task task) {
        this.interestedTasks.add(task);
    }

    public void removeInterestedTask(Task task) {
        this.interestedTasks.remove(task);
    }

    public boolean isInterested(Task task) {
        return interestedTasks.contains(task);
    }

    /**
     * Returns a Double representing how desirable the given task is to this
     * volunteer.
     */
    public Double getTaskDesirabilityScore(Task task) {

        // TODO: Implement this method. See the README for more details.
        
        return -1.0;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

}