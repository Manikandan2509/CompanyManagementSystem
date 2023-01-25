package com.companymanagement.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;

//import org.json.simple.JsonObject;

import com.companymanagement.utils.Utility;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CompanyManagementServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(CompanyManagementServlet.class.getName());
	 
	private static final long serialVersionUID = 1L;
	public static final Map<String, String> DTOMap;
	static {
        HashMap<String, String> aMap = new HashMap<String, String>();
        aMap.put("Signin", "Employee");
        aMap.put("Signup", "Employee");
        aMap.put("Org", "Organization");
        DTOMap = Collections.unmodifiableMap(aMap);
    }
       
	
    public CompanyManagementServlet() throws IOException {
    	super();
//    	logger.addHandler(new FileHandler());
    	logger.log(Level.SEVERE, getServletInfo());
    }
    JsonObject json;
  
	 
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//response.getWriter().append("Served at: ").append(request.getContextPath());
    	System.out.println("Thread ID: "+Thread.currentThread().getId());
    	requestHandler(request,response);
    }
	
	
	void requestHandler(HttpServletRequest request,HttpServletResponse response)  {
		
		Object object = null;
		String className =  Utility.spiltURI(request.getPathInfo());
			System.out.println(request.getPathInfo());
				System.out.println("classname: " + className);
				
				
		Utility.setThreadLocals(request.getPathInfo());
		
		Utility.isBulkRequest(request.getPathInfo());
		System.out.println(CurrentData.getIsBulkRequest());
					
		//Set EmpID, OrgID in threadlocal
//		
//		<XML>
//		<API path="/org/v1/employee" method="PUT" role="admin,orgadmin>
//		</XML>
		//Get empDTO
				
//		checkAccessPerms(request, empDTO);
		
		Class<?> cl;
		try {
			cl = Class.forName("com.companymanagement.service." + className +"Service");
			Object obj = Class.forName("com.companymanagement.service." + className +"Service").newInstance(); 
			
			
			
			String dataClass = DTOMap.getOrDefault(className, className);
			System.out.println("dataclass: " + dataClass);
			
			Object dtoObj = Class.forName("com.companymanagement.dto."+ dataClass +"DTO").newInstance();
			
			String method = request.getMethod().toLowerCase();
	
			
			
			String body = request.getReader().lines().collect(Collectors.joining());
			//String body = request.getParameter("payload");
			
			object = Utility.createUserObject(body,dtoObj);
//			Class<?> pojo = Class.forName("com.companymanagement.dto."+dataClass+"DTO");
//					Field[] fields=pojo.getDeclaredFields();
//				
				HashMap<String,Object> hm= new HashMap<>();
//					ArrayList<String> columns = new ArrayList<>();
//					for(Field f:fields) {				
//						f.setAccessible(true);
//					
//						if(f.get(object)!=null) {
//							if(f.get(object) instanceof Integer) {
//								if(((Integer) f.get(object)).intValue()==0)
//									continue;
//							}
//							if(f.get(object) instanceof Float) {
//								if(((Float) f.get(object)).floatValue()==0)
//									continue;
//							}
//							if(f.get(object) instanceof Long) {
//								if(((Long) f.get(object)).longValue()==0)
//									continue;
//							}
//						String fieldname=f.getName();
//						Object valueobj=f.get(object);
//						columns.add(fieldname);
//						hm.put(fieldname, valueobj);
//						
//						}
//						
//					}
//					System.out.println(hm.toString());
			String query = request.getQueryString();
			response.setContentType("application/json");
			
			
			switch (method) {
			case "get": {
				
				
				Method methods = cl.getDeclaredMethod("get", String.class);
				JsonObject j = (JsonObject) methods.invoke(obj,query);
				response.getWriter().append(j.toString());
				
				break;
			}
			

			case "post": {
				Method methods = cl.getDeclaredMethod("post", Object.class, String.class ,HashMap.class);
				JsonObject j = (JsonObject) methods.invoke(obj,object,body,hm);
				response.getWriter().append(j.toString());
		
				break;
			}
			case "put": {
				
				Method methods = cl.getDeclaredMethod("put", Object.class,String.class);
				JsonObject j = (JsonObject) methods.invoke(obj,object,query);
				response.getWriter().append(j.toString());
		
				break;
			}
			case "delete": {
				
				Method methods = cl.getDeclaredMethod("delete",String.class);
				JsonArray arr = new JsonArray();
				if(CurrentData.getIsBulkRequest()) {
					query = (String) request.getAttribute("");
				}
				JsonObject j = (JsonObject) methods.invoke(obj,query);
				response.getWriter().append(j.toString());
		
				break;
				}
			default :
				response.getWriter().append("Class Not Found");
			

		}
			
					}
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
			response.setStatus(500);
		}
		finally {
			
			DbConnection.closeCon();
		}
		
		}
}

	

