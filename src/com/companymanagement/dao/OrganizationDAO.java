//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.companymanagement.dto.OrganizationsDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class OrganizationDAO {
	Connection con;
	public OrganizationDAO()
	{
		con = DbConnection.getCon();
	}
	String tableName = "organization"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	GenerateQuery gq = new GenerateQuery();
	
	public void createOrganization(OrganizationsDTO orgDTO) throws SQLException {
		
		
			String query = "insert into organization(organization_name,organization_state) values(?,?)";
		
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,orgDTO.getOrganizationName());
			ps.setString(2, orgDTO.getOrganizationState());
			
			int value = ps.executeUpdate();
			System.out.print(value);
			
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
//			ResultSetMetaData rsmd = rs.getMetaData();
//			   int columnsNumber = rsmd.getColumnCount();
//			   while (rs.next()) {
//				   
//			       for (int i = 1; i <= columnsNumber; i++) {
//			           if (i > 1) System.out.print(",  ");
//			           String columnValue = rs.getString(i);
//			           System.out.print(columnValue + " " + rsmd.getColumnName(i));
//			           
//			       }
//			       System.out.println("");
//			       
//			   }
			System.out.println(rs.getInt("organization_id"));
			orgDTO.setOrganizationID(rs.getInt("organization_id"));
			
			
		
	}

	public void deleteOrganization(String queryString) throws SQLException{

		String query = "delete from organization where org_id = ?";
		
		PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			
			ps.setInt(1,Integer.parseInt(query));
		
			
			ResultSet value = ps.executeQuery();
			System.out.print(value);
		
	}
	
	public JsonObject getOrganization(String query) throws SQLException{
			getCondition.add("organization_id");
			String query1 = gq.selectquery(tableName, getColumns, getCondition);
			System.out.print(query1);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,CurrentData.getCurrentOrg());
		
			
			ResultSet rs = ps.executeQuery();
			return Utility.convertToJSON(rs);
	
	}

	
}
