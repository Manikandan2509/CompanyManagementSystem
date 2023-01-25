//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.companymanagement.dto.ProjectsDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class ProjectDAO {
	Connection con;
	public ProjectDAO()
	{
		con = DbConnection.getCon();
	}
	String tableName = "project"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	GenerateQuery gq = new GenerateQuery();
	
	public void createProject(ProjectsDTO projectDTO) throws SQLException{
		
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO project(project_name,revenue,department_id,project_status) \n"
				+ "values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, projectDTO.getProjectName());
		ps.setInt(2, projectDTO.getRevenue());
		ps.setInt(3, CurrentData.getCurrentDepartment());
		ps.setInt(4, 1);
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		projectDTO.setProjectID(rs.getInt("project_id"));
	
	}
	public JsonObject getProject() throws SQLException{
	
		int orgId = CurrentData.getCurrentOrg();
		
		int employeeId = CurrentData.getCurrentEmployee();
		int departmentId = CurrentData.getCurrentDepartment();
		int projectId=CurrentData.getCurrentProject();
		if (projectId != -1 ) {
			getCondition.add("org_id");
			getCondition.add("department_id");
			getCondition.add("project_id");
		}
		else {
			getCondition.add("org_id");
			getCondition.add("department_id");
		}
		//System.out.println(employeeId);
		System.out.println(orgId);
		//Pagination using LIMIT and OFFSET
		String query = gq.selectquery(tableName, getColumns, getCondition);
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, orgId);
		ps.setInt(2,departmentId);
		if(projectId != -1) {
			ps.setInt(3, projectId);
			//Generator.where(employeeId.equals(id))
		}
		ResultSet rs = ps.executeQuery();
		return Utility.convertToJSON(rs);
	
	}
	public void putProject(ProjectsDTO projectDTO) throws SQLException{
	
		

	PreparedStatement ps = con.prepareStatement("INSERT INTO departments(department_name,department_state) \n"
			+ "values(?,?)",Statement.RETURN_GENERATED_KEYS);
	
	ps.setString(1, projectDTO.getProjectName());
	ps.setInt(2,projectDTO.getProjectID());
	
	ps.executeUpdate();
	ResultSet rs = ps.getGeneratedKeys();
	rs.next();
	projectDTO.setDepartmentID(rs.getInt("department_id"));
	
		
	}
	public void deleteProject (ProjectsDTO projectDTO) throws SQLException{
		
		

		PreparedStatement ps = con.prepareStatement("INSERT INTO departments(department_name,department_state) \n"
				+ "values(?,?)",Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, projectDTO.getProjectName());
		ps.setInt(2,projectDTO.getProjectID());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		projectDTO.setDepartmentID(rs.getInt("department_id"));
	
	}
}
