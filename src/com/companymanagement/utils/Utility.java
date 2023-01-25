//$Id$
package com.companymanagement.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.companymanagement.dto.EmployeesDTO;
import com.companymanagement.dto.OrganizationsDTO;
import com.companymanagement.service.EmployeesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.http.HttpServletRequest;


public class Utility {

	public static String regex="[0-9]+";
	
	/*public static  EmployeesDTO getEmployeeObject(JSONObject json) {
		EmployeesDTO employeePojo = new EmployeesDTO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String doj;
		String dob;
		if(json.get("dateOfJoining") != null) {
			 doj = (String) json.get("dateOfJoining");
			 Date dateOfJoining = null;
			try {
				dateOfJoining = (Date) sdf.parse(doj);
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
			 employeePojo.setDateOfBirth(dateOfJoining);
		}
		if(json.get("dateOfJoining") != null) {
			dob = (String) json.get("dateOfBirth");
			Date dateOfBirth = null;
			try {
				dateOfBirth = (Date) sdf.parse(dob);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			employeePojo.setDateOfJoining(dateOfBirth);
		}
	    if(json.get("firstName")!= null) {
			employeePojo.setFirstName(json.get("firstName").toString());
		}
		if(json.get("secondName")!= null) {
			employeePojo.setSecondName(json.get("secondName").toString());
		}
		if(json.get("address2")!= null) {
			employeePojo.setAddress1(json.get("address1").toString());
		}
		if(json.get("city")!= null){
			employeePojo.setCity(json.get("city").toString());
		}
		if(json.get("departmentID")!= null){
			employeePojo.setDepartmentID((int) json.get("departmentID"));
		}
		if(json.get("email")!= null){
			employeePojo.setEmail(json.get("email").toString());
		}
		if(json.get("passwordHash")!= null){
			employeePojo.setPasswordHash(json.get("passwordHash").toString());
		}
		if(json.get("pincode")!= null){
			employeePojo.setPincode(Integer.parseInt(json.get("pincode").toString()));
		}
		if(json.get("role")!= null){
			employeePojo.setRole(json.get("role").toString());
		}
		System.out.println(employeePojo.toString());
		
		return employeePojo;
	}*/
	
	public static OrganizationsDTO getOrganizationObject(JSONObject json) {
		OrganizationsDTO org = new OrganizationsDTO();
		if(json.get("organizationName")!= null) {
			org.setOrganizationName(json.get("organizationName").toString());
		}
		if(json.get("organizationState")!= null) {
			org.setOrganizationState(json.get("organizationState").toString());
		}
		return org;
		
	}
	
	
//public static  String[] spiltURI(String URI) {
//	 String[] spiltURI = URI.split("/");
//	 return spiltURI;
// }
 	 
 
 
 // Convert request body to JSON OBJECT
 
 
 public static Object createUserObject(String body,Object dtoObj) {
		Gson gson = new Gson();
		try {
			JsonObject jsonObject = gson.fromJson(body, JsonObject.class);
			
			
			Object object=dtoObj;
			object= gson.fromJson(jsonObject,object.getClass());
			return object;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
		 
	 }
 


public static String resultSetToString(ResultSet rs) {
	Gson gson = new Gson();
	String result = gson.toJson(rs);
	
	return result;
}

public static JsonObject objectToJSON(Object object) {
	Gson gsonBuild = new GsonBuilder().create();
	String string = gsonBuild.toJson(object);
	Gson gson = new Gson();
	JsonObject jsonObject = gson.fromJson(string, JsonObject.class);
	return jsonObject;
}

public static JsonObject convertToJSON(ResultSet resultSet) throws SQLException
         {

    JsonArray jsonArray = new JsonArray();
    while (resultSet.next()) {
        int total_columns = resultSet.getMetaData().getColumnCount();
        JsonObject obj = new JsonObject();
        for (int i = 0; i < total_columns; i++) {
            obj.addProperty(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getString(i + 1));
        }
      jsonArray.add(obj);
    }
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("jsonArray",jsonArray);
    return jsonObject;
    
}
public static String getClassName(String name) {
	
	return name.substring(0, 1).toUpperCase() + name.substring(1);

}
public static String spiltURI(String urlpath) {
	urlpath = urlpath.replaceFirst("/", "");
	String[] url = urlpath.split("/");
	
	
	int arrlength=url.length;
	
	
	
	String className = null;

	if (arrlength> 1) {
		Pattern p = Pattern.compile(regex);
		Matcher m=p.matcher(url[arrlength-1]);
	if(m.matches())
		className = Utility.getClassName(url[url.length - 2]);
	else
		className = Utility.getClassName(url[url.length - 1]);
	}

	else {
		className = Utility.getClassName(url[0]);
			
	}
	return className;
}

public static void setThreadLocals(String urlPath) {
	urlPath = urlPath.replaceFirst("/", "");
	String[] url = urlPath.split("/");
	
	int currentOrg = -1;
	int currentEmployee = -1;
	int currentDepartment =-1;
	int currentProject=-1;
	
	for(int i = 0; i < url.length-1; i++) {
		if(url[i].equals("orgs")) {
			System.out.println("Org ID: "+url[i+1]);
			currentOrg = Integer.parseInt(url[i+1]);

		}
		if(url[i].equals("employees")) {
			System.out.println("Employee ID: "+url[i+1]);
			
			currentEmployee = Integer.parseInt(url[i+1]);
			
			System.out.println(currentEmployee);
		}
		
		if(url[i].equals("departments")) {
			System.out.println("Department ID: "+url[i+1]);
			currentDepartment = Integer.parseInt(url[i+1]);
		}
		if(url[i].equals("projects")) {
			System.out.println("Project ID: "+url[i+1]);
			currentProject = Integer.parseInt(url[i+1]);
		}
	}
	
		
	CurrentData.setCurrentProject(currentProject);
	CurrentData.setCurrentOrg(currentOrg);
	CurrentData.setCurrentEmployee(currentEmployee);
	CurrentData.setCurrentDepartment(currentDepartment);
}

public static String query(HashMap<String,String> map) {
	 
     
  String condition = "";
	 for (Map.Entry<String,String> entry : map.entrySet()) {
        condition+= entry.getKey() + "=" + entry.getValue() +" and ";
        
        }
        condition= condition.substring(0,condition.length()-4);
	return condition;
}

public static String hashPassword(String password) {
	Argon2 argon2 = Argon2Factory.create();
	String hash=argon2.hash(10, 65536, 1, password);
	return hash;
}
public static boolean verifyHash(String password,String hashedPassword) {
	Argon2 argon2 = Argon2Factory.create();
	return argon2.verify(password,hashedPassword);
}
	public static void printResultSet(ResultSet rs) throws SQLException{
			ResultSetMetaData rsmd = rs.getMetaData();
		   int columnsNumber = rsmd.getColumnCount();
			  
		   
	       for (int i = 1; i <= columnsNumber; i++) {
	           if (i > 1) System.out.print(",  ");
	           String columnValue = rs.getString(i);
	           System.out.print(columnValue + " " + rsmd.getColumnName(i));							           
	       }
       System.out.println("");
	}
	public static void isBulkRequest(String urlpath) {
		urlpath = urlpath.replaceFirst("/", "");
		String[] url = urlpath.split("/");
		CurrentData.setIsBulkRequest(url[0].equals("bulk"));
	}
	
}
