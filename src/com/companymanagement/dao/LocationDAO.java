//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.companymanagement.dto.LocationsDTO;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;

public class LocationDAO {
	Connection con;
	public LocationDAO()
	{
		con = DbConnection.getCon();
	}
	String tableName = "location"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	GenerateQuery gq = new GenerateQuery();
	public void addLocation(LocationsDTO locationDTO) throws SQLException{
		Connection con=DbConnection.getCon();
		String query = "INSERT INTO location (department_name,department_state) \n"
				+ "values(?,?)";
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, locationDTO.getLocationID());
		ps.setString(2, locationDTO.getCity());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		locationDTO.setLocationID(rs.getInt("department_id"));
	
	}
	public void deleteLocation(LocationsDTO locationDTO) throws SQLException{
Connection con=DbConnection.getCon();
		
String query = "INSERT INTO location (department_name,department_state) \n"
		+ "values(?,?)";
PreparedStatement ps = con.prepareStatement(query,
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);

		ps.setInt(1, locationDTO.getLocationID());
		ps.setString(2, locationDTO.getCity());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		locationDTO.setLocationID(rs.getInt("department_id"));
	
		
	}
	public void updateLocation(LocationsDTO locationDTO) throws SQLException {
		Connection con=DbConnection.getCon();
		String query = "INSERT INTO location (department_name,department_state) \n"
				+ "values(?,?)";
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, locationDTO.getLocationID());
		ps.setString(2, locationDTO.getCity());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		locationDTO.setLocationID(rs.getInt("department_id"));
	
	
	}
	public void getLocation(LocationsDTO locationDTO) throws SQLException {
		Connection con=DbConnection.getCon();
		getCondition.add("location_id");
		String query = gq.selectquery(tableName, getColumns, getCondition);
		PreparedStatement ps = con.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY,Statement.RETURN_GENERATED_KEYS);
		
		
		ps.setInt(1, locationDTO.getLocationID());
		
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		locationDTO.setLocationID(rs.getInt("department_id"));
	
}
	
}
