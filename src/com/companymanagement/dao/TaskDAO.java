//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.companymanagement.dto.TasksDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class TaskDAO {
	Connection con;
	public TaskDAO()
	{
		con = DbConnection.getCon();
	}
	String tableName = "tasks"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	GenerateQuery gq = new GenerateQuery();
	
public void createTask(TasksDTO taskDTO) throws SQLException {
	
	PreparedStatement ps = con.prepareStatement("INSERT INTO tasks(project_id,task_name,task_status) \n"
			+ "values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
	ps.setInt(1,CurrentData.getCurrentProject());
	ps.setString(2, taskDTO.getTaskName());
	ps.setString(3,taskDTO.getTaskStatus());
	
	ps.executeUpdate();
	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
//	projectDTO.setDepartmentID(rs.getInt("department_id"));

}
public JsonObject getTask() throws SQLException {

	
int projectId = CurrentData.getCurrentProject();
int taskId = CurrentData.getCurrentTask();
//System.out.println(employeeId);

System.out.println("Project id" + projectId);
//Pagination using LIMIT and OFFSET
if(taskId ==-1) {
	getCondition.add("project_id");

}else {
	getCondition.add("project_id");
	getCondition.add("task_id");
}
String query = gq.selectquery(tableName, getColumns, getCondition);
PreparedStatement ps = con.prepareStatement(query);
ps.setInt(1, projectId);
if(taskId != -1) {
	ps.setInt(2, taskId);
	//Generator.where(employeeId.equals(id))
}
ResultSet rs = ps.executeQuery();
return Utility.convertToJSON(rs);
}
public void updateTask(TasksDTO taskDTO) throws SQLException{

	
	PreparedStatement ps = con.prepareStatement("INSERT INTO tasks(task_name,task_state) \n"
			+ "values(?,?)",Statement.RETURN_GENERATED_KEYS);
	
	ps.setString(1, taskDTO.getTaskName());
	ps.setInt(2,taskDTO.getProjectID());
	
	ps.executeUpdate();
	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
}
public void deleteTask() throws SQLException{

	PreparedStatement ps = con.prepareStatement("delete * from tasks where project_id=? and task_id=?");
	
	ps.setInt(1, CurrentData.getCurrentProject());
	ps.setInt(2, CurrentData.getCurrentTask());
	
	ps.executeUpdate();
	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
}
}
