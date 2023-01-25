//$Id$
package com.companymanagement.controller;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.companymanagement.utils.RouteURI;

public class RouteFilter implements Filter {
	
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
       System.out.println("DO FILTER");
       Boolean flag = false;
    	HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        HashMap<String,ArrayList<String>> map = RouteURI.getMap();
        System.out.println("path" + path);
        
        Set<String> keys = map.keySet();
        for (String key : keys) {
        	
           if(path.matches(key)) {
        	   if(map.get(key).contains(req.getMethod())) {
            	   chain.doFilter(request, response);
               }
           }
           
        }
       
    }

    public void init(FilterConfig config) throws ServletException {
    	
    }

    public void destroy() {
     
    }
    
}

