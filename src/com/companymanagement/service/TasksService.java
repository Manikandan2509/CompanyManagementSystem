//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.companymanagement.dao.TaskDAO;
import com.companymanagement.dto.TasksDTO;
import com.google.gson.JsonObject;

public class TasksService implements ServiceInterface{


	public String post(Object object, String Query,HashMap<String,Object> map) throws SQLException {
		TaskDAO taskDAO = new TaskDAO();
		TasksDTO taskDTO = (TasksDTO) object;
		taskDAO.createTask(taskDTO);
		return null;
	}

	@Override
	public JsonObject delete(String Query) throws SQLException {
		TaskDAO taskDAO = new TaskDAO();
		
		taskDAO.deleteTask();
		return null;
		
	}

	@Override
	public JsonObject get(String Query) throws SQLException {
		TaskDAO taskDAO = new TaskDAO();
		
		taskDAO.getTask();
		return null;
		
	}

	@Override
	public JsonObject put(Object object, String Query) throws SQLException {

		TaskDAO taskDAO = new TaskDAO();
		TasksDTO taskDTO = (TasksDTO) object;
		taskDAO.updateTask(taskDTO);
		return null;
	}

}
