//$Id$
package com.companymanagement.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;

public interface ServiceInterface {

	public String post(Object object,String Query,HashMap<String,Object> map)throws SQLException;
	
	
	public JsonObject delete(String Query)throws SQLException;
	
	
	public JsonObject get( String Query)throws SQLException;
	
	
	public JsonObject put(Object object,String Query)throws SQLException;
	
}
