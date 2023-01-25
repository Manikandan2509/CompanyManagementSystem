//$Id$
package com.companymanagement.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RouteURI {
	public static  HashMap<String,ArrayList<String>> getMap(){
		HashMap<String,ArrayList<String> >map = new HashMap<>();
	    // Sign in
		ArrayList<String> list = new ArrayList<>();
		list.add("DELTELE");
        list.add("GET");
        list.add("PUT");
    	ArrayList<String> list1 = new ArrayList<>();
        list1.add("GET");
        list1.add("POST");
        ArrayList<String> postList = new ArrayList<>();
        postList.add("POST");
	    map.put("/api/v1/signin", postList);
	    // Sign in
	    map.put("/api/v1/signout", postList);
	    // Sign in
	    map.put("/api/v1/signup", postList);
	    // Sign in
	    map.put("/api/v1/orgs", list1);
	    // Organisation
	    map.put("/api/v1/orgs/[0-999]+$\"", list);
	    // Employees
	    map.put("/api/v1/orgs/[0-999]+/employees+$", list1);
	    // 
	    map.put("/api/v1/orgs/[0-9]+/employees/[0-9]+$", list);
	    
	    map.put("/api/v1/orgs/[0-9]+/departments+$", list1);
	    
	    map.put("/api/v1/orgs/[0-9]+/departments/[0-9]+$", list);
	    
	    map.put("/api/v1/orgs/[0-9]+/departments/[0-9]+/projects$", list1);
	    
	    map.put("/api/v1/orgs/[0-9]+/departments/[0-9]+/projects/[0-9]+$",list);
	    
	    map.put("/api/v1/orgs/[0-9]+/departments/[0-9]+/projects/[0-9]+/tasks$", list1);
	    
	    map.put("/api/v1/orgs/[0-9]+/departments/[0-9]+/projects/[0-9]+/tasks/[0-9]+$",list);
	    
	    return map;
	}
	
    
   
}
