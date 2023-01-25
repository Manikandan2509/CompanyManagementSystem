//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.companymanagement.dto.DepartmentsDTO;
import com.companymanagement.dto.DependantsDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class DependantDAO {
	Connection con;
	public DependantDAO()
	{
		con = DbConnection.getCon();
	}
	String tableName = "dependants"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	GenerateQuery gq = new GenerateQuery();
	public void addDependant(DependantsDTO dependantDTO) throws SQLException{
	
		
	    PreparedStatement ps1 =  con.prepareStatement("select * from employee where emp_id = ? and can_add_dependants = 'true' ");
	    ps1.setInt(1, CurrentData.getCurrentEmployee());
	    ResultSet rs1 = ps1.executeQuery();
	    System.out.print(rs1);
	    
	    if(rs1.next()) {
	    	String query = "INSERT INTO dependants(name,relationship,gender,emp_id,dependant_status) \n"
					+ "values(?,?,?,?,?)";
	    	PreparedStatement ps = con.prepareStatement(query,
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, dependantDTO.getDependantName());
			ps.setString(2,dependantDTO.getRelationship());
			ps.setString(3, dependantDTO.getGender());
			ps.setInt(4, CurrentData.getCurrentEmployee());
			ps.setString(5, "active");
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			dependantDTO.setDependantID(rs.getInt("dependant_id"));
		
	    }
	
		
	}
	public JsonObject getDependant() throws SQLException{
		
		getCondition.add("emp_id");
		String query = gq.selectquery(tableName, getColumns, getCondition);
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1,CurrentData.getCurrentEmployee());
		ResultSet rs = ps.executeQuery();
		
		
		return Utility.convertToJSON(rs);
		
	}
	public void deleteDependant(DependantsDTO dependantDTO) throws SQLException{

		String query = "update dependant SET dependant_status='inactive' where org_id =? and emp_id=?";
	
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, CurrentData.getCurrentOrg());
		ps.setInt(2, CurrentData.getCurrentEmployee());
		ps.executeUpdate();
		System.out.print("Deleted dependant : " + CurrentData.getCurrentEmployee());
	
		
	}
	public void putDependant(DependantsDTO dependantDTO) throws SQLException{
	
		String query = "INSERT INTO dependants(name,relationship,gender,emp_id,dependant_status) \n"
				+ "values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(query,
		                ResultSet.TYPE_SCROLL_INSENSITIVE,
		                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, dependantDTO.getDependantName());
		ps.setString(2,dependantDTO.getRelationship());
		ps.setString(3, dependantDTO.getGender());
		ps.setInt(4, CurrentData.getCurrentEmployee());
		ps.setString(5, "active");
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		dependantDTO.setDependantID(rs.getInt("dependant_id"));
	
		
	}
}
