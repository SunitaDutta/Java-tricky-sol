    package com.java.api.vol;
/**
 * A Task represents a duty to be assigned to a volunteer.
 * Tasks contain an id, a name, and a description, as well as a flag indicating
 * whether the task is people-facing.
 */
public class Task {
    private int id;
    private String name;
    private boolean peopleFacing;
    private String description;

    public Task(int id, String name, boolean peopleFacing, String description) {
        this.id = id;
        this.name = name;
        this.peopleFacing = peopleFacing;
        this.description = description;
    }

    public int getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isPeopleFacing() {
        return this.peopleFacing;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "Task " + this.id + ": " + this.name;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (peopleFacing ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (peopleFacing != other.peopleFacing)
			return false;
		return true;
	}
    
    
}
