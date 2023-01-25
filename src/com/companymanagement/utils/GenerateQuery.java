//$Id$
package com.companymanagement.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GenerateQuery {
	
	String SELECT_QUERY = " select ";
	String INSERT_QUERY = " insert into ";
	String WHERE_CLAUSE = " where ";
	String DELETE_QUERY = " delete ";
	String UPDATE_QUERY = " update ";
		
		public String selectquery(String tablename, ArrayList<String> columns, ArrayList<String> values) {
			String conditions = "";
			String tablecolumns = "*";
			if(values.isEmpty())
				return SELECT_QUERY+" *"+" from "+tablename;
			int i = 0;
			if (columns.size() > 0) {
				tablecolumns = String.join(",", columns);
			}
			for (i = 0; i < values.size() - 1; i++) {
				if(values.get(i).equalsIgnoreCase("count(*)")) {
					conditions+=values.get(i);
					break;
				}
				conditions += values.get(i) + "=? and ";
			}
			conditions += values.get(i) + "=?";
			String query = SELECT_QUERY + tablecolumns + " from " + tablename + WHERE_CLAUSE+ conditions;
			System.out.println(query);
			return query;
		}
		
		public String insertquery(String tablename, HashMap<String,Object> map) {
			int i = 0;
			ArrayList<String> columns = new ArrayList<>();
			HashMap<String,String> mapping = new HashMap<>();
		
			String placeholders = "";
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
			    columns.add(entry.getKey());
			    placeholders += entry.getValue()+",";
			}
			String tablecolumns = String.join(",", columns);
			placeholders = placeholders.substring(0, placeholders.length() - 1);
	
			String query = INSERT_QUERY + tablename + "(" + tablecolumns + ") values(" + placeholders + ")";
			System.out.print("query: " + query);
			return query;
		}
	
	public String UpdateQuery() {
		
		
		return null;
	}
	public String deletequery(String tablename, ArrayList<String> columns, ArrayList<String> values) {
		String conditions = "";
		String tablecolumns = "*";
		if(values.isEmpty())
			return SELECT_QUERY+" *"+" from "+tablename;
		int i = 0;
		if (columns.size() > 0) {
			tablecolumns = String.join(",", columns);
		}
		for (i = 0; i < values.size() - 1; i++) {
			if(values.get(i).equalsIgnoreCase("count(*)")) {
				conditions+=values.get(i);
				break;
			}
			conditions += values.get(i) + "=? and ";
		}
		conditions += values.get(i) + "=?";
		String query = SELECT_QUERY + tablecolumns + " from " + tablename + WHERE_CLAUSE+ conditions;
		System.out.println(query);
		return query;
	}
}
