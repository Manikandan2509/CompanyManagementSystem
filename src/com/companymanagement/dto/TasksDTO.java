//$Id$
package com.companymanagement.dto;

import com.google.gson.annotations.SerializedName;

public class TasksDTO {
	//@SerializedName ("task_id")
	int taskID;
	//@SerializedName ("project_id")
	int projectID;
	//@SerializedName ("task_name")
	String taskName;
	//@SerializedName ("task_status")
	String taskStatus;
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
}
