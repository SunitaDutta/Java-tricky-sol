    package com.java.api.vol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Util {
    /**
     * Returns an int representing the total satisfaction of the volunteers with the
     * current task assignments.
     *
     * Each task assigned that the volunteer listed as an interested task earns a
     * minimum of 1 point, with tasks listed as a volunteer's first, second, or third
     * choice earning 4, 3, and 2 points, respectively.  Each task assigned that the
     * volunteer expressed no interest in loses 1 point. Additionally, volunteers lose
     * satisfaction points when they are assigned more tasks than they would be if
     * tasks were evenly distributed.
     */
    public static Integer getVolunteerSatisfactionScore(
        Map<Volunteer, Set<Task>> assignments, List<Volunteer> volunteers, Integer numTasks) {
        int totalSatisfactionScore = 0;

        if (assignments.size() == 0) {
            System.out.println("No assignments have been made yet.");
            return totalSatisfactionScore;
        }
    
        for (Volunteer volunteer : volunteers) {
            if (!assignments.containsKey(volunteer)) {
                continue;
            }
    
            // Calculate a Volunteer's individual satisfaction score
            int volunteerScore = 0;
            for (Task task : assignments.get(volunteer)) {
                if (!volunteer.isInterested(task)) {
                    volunteerScore -= 1;
                } else {
                    int interestRanking = volunteer.getInterestedTasks().indexOf(task);
                    if (interestRanking < 3) {
                        volunteerScore += 4 - interestRanking;
                    } else {
                        volunteerScore += 1;
                    }
                }
            }
    
            // For every Task that a Volunteer has above the number they would
            // have if tasks were evenly distributed, we lose a point. No one
            // wants to feel like they've been asked to do all of the work!
            int totalTasksPerVolunteer = (int) Math.ceil((double) numTasks / volunteers.size());
            int numberOfTasksAssigned = assignments.get(volunteer).size();
            if (numberOfTasksAssigned > totalTasksPerVolunteer) {
                volunteerScore -= numberOfTasksAssigned - totalTasksPerVolunteer;
            }
    
            totalSatisfactionScore += volunteerScore;
        }
    
        return totalSatisfactionScore;
    }

    /**
     * Loads tasks from a CSV file.
     */
    public static Map<Integer, Task> loadTasks(String csvFileName) {
        // task id to task map
        Map<Integer, Task> tasks = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(csvFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parsedLine = line.split(",");
                int taskId = Integer.parseInt(parsedLine[0]);
                String name = parsedLine[1];
                boolean peopleFacing = parsedLine[2].toLowerCase().equals("true");
                String description = parsedLine[3];

                tasks.put(taskId, new Task(taskId, name, peopleFacing, description));
            }

            bufferedReader.close();
        } catch(IOException e) {
            System.out.println("Error importing task from " + csvFileName + "\n" + e);
        }

        return tasks;
    }

    /**
     * Loads volunteers from a CSV file.
     */
    public static List<Volunteer> loadVolunteers(String csvFileName, Map<Integer, Task> tasks) {
        List<Volunteer> volunteers = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(csvFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parsedLine = line.split(",");
                String name = parsedLine[0];
                String interestedTaskIds = parsedLine[1];

                Volunteer volunteer = new Volunteer(name);

                // Load in the interested tasks
                for (String taskIDString : interestedTaskIds.split(" ")){
                    volunteer.addInterestedTask(tasks.get(Integer.parseInt(taskIDString)));
                }

                volunteers.add(volunteer);
            }

            bufferedReader.close();
        } catch(IOException e) {
            System.out.println("Error importing volunteer from " + csvFileName + "\n" + e);
        }

        return volunteers;
    }
}