//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.companymanagement.dao.ProjectDAO;
import com.companymanagement.dto.ProjectsDTO;
import com.google.gson.JsonObject;

public class ProjectsService implements ServiceInterface{

	
	public String post(Object object, String Query,HashMap<String,Object> map) throws SQLException {
		ProjectDAO projectDAO= new ProjectDAO();
		ProjectsDTO projectDTO = (ProjectsDTO) object;
		projectDAO.createProject(projectDTO);
		return null;
	}

	
	public JsonObject delete(String Query) throws SQLException {
		ProjectDAO projectDAO= new ProjectDAO();
		//ProjectDTO projectDTO = (ProjectDTO) object;
		//projectDAO.createProject(projectDTO);
		return null;
	
	
	}

	
	public JsonObject get(String Query) throws SQLException {
		ProjectDAO projectDAO= new ProjectDAO();
		return projectDAO.getProject();
	}

	
	public JsonObject put(Object object, String Query) throws SQLException {
	
		ProjectDAO projectDAO= new ProjectDAO();
		ProjectsDTO projectDTO = (ProjectsDTO) object;
		projectDAO.createProject(projectDTO);

		return null;
	}

}
