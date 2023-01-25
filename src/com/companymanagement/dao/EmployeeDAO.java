//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.companymanagement.dto.EmployeesDTO;
import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.GenerateQuery;
import com.companymanagement.utils.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class EmployeeDAO {
	String tableName = "employee"; 
	ArrayList<String> getCondition = new ArrayList<String>();
	ArrayList<String> getColumns = new ArrayList<String>();
	GenerateQuery gq = new GenerateQuery();
		Connection con;
		
		public EmployeeDAO()
		{
			con = DbConnection.getCon();
		}
  		
		
		public String createEmployee(EmployeesDTO emp,HashMap<String,Object> map) {
			
			
try {
				
			
				String query = "INSERT INTO employee(org_id,employee_state,first_name,second_name,email,date_of_joining,date_of_birth,password_hash,role,address1,address2,city,state,pincode,department_id ,is_org_admin,is_dept_manager) \n"
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setInt(1, CurrentData.getCurrentOrg());
				ps.setString(2, emp.getEmpStatus());
				ps.setString(3,emp.getFirstName());
				ps.setString(4,emp.getSecondName());
				ps.setString(5,emp.getEmail());
				ps.setString(6, emp.getDateOfJoining());
				ps.setString(7, emp.getDateOfJoining());
				
				ps.setString(8, Utility.hashPassword(emp.getPasswordHash()));
				ps.setString(9, emp.getRole());
				ps.setString(10, emp.getAddress1());
				ps.setString(11, emp.getAddress2());
				ps.setString(12, emp.getCity());
				ps.setString(13, emp.getState());
				ps.setInt(14, emp.getPincode());
				ps.setInt(15, emp.getDepartmentID());
				ps.setBoolean(16, emp.isOrgAdmin());
				ps.setBoolean(17, emp.isDeptManager());
				
//				ResultSet rs= ps.executeQuery();
//				
//				   ResultSetMetaData rsmd = rs.getMetaData();
//				   int columnsNumber = rsmd.getColumnCount();
//				   while (rs.next()) {
//					   
//				       for (int i = 1; i <= columnsNumber; i++) {
//				           if (i > 1) System.out.print(",  ");
//				           String columnValue = rs.getString(i);
//				           System.out.print(columnValue + " " + rsmd.getColumnName(i));
//				           
//				       }
//				       System.out.println("");
//				       return true;
//				   }
				int flag = ps.executeUpdate();
				if(flag >0) {
					return "Account Created Successfully" ;
				}

				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return "Error Occurred" ;
			
		}
		public JsonObject getEmployee() throws SQLException {
			int orgId = CurrentData.getCurrentOrg();
			
			int employeeId = CurrentData.getCurrentEmployee();
			
			if(employeeId == -1) {
				getCondition.add("org_id");
			}
			else {
				getCondition.add("org_id");
				getCondition.add("emp_id");
			}
			//System.out.println(employeeId);
		
			//Pagination using LIMIT and OFFSET
			String query =  gq.selectquery(tableName, getColumns , getCondition);
			System.out.println(query);
			//String query = employeeId != -1 ? "SELECT * from employee where org_id=? and emp_id=?" : "SELECT * FROM employee where org_id=?";
			PreparedStatement ps =con.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, orgId);
			if(employeeId != -1) {
				ps.setInt(2, employeeId);
				//Generator.where(employeeId.equals(id))
			}
			ResultSet rs = ps.executeQuery();
			return Utility.convertToJSON(rs);
		}
		
		public int deleteEmployee(JsonArray arr) throws SQLException{
			
			
			//System.out.println(employeeId);
			
			int orgId = CurrentData.getCurrentOrg();
			System.out.println(orgId);
			String query = "update employee SET employee_state='inactive' where org_id =? and emp_id=?";
			PreparedStatement ps =con.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
		
			//Batching for Bulk Operation
			for(int i=0;i<arr.size();i++) {
				ps.setInt(1, orgId);
				int employeeID = arr.get(i).getAsInt();
				ps.setInt(2, employeeID);
				ps.addBatch();
			}
			int[] rs = ps.executeBatch();
			int t = 0;
			for(int v: rs) {
				if(v >= 0) t+=v;
//				we are skipping all failed queries
			}
			return t;
			
			
			
		}
		public JsonObject validateEmployee(EmployeesDTO empDTO) throws SQLException {
			
			int orgId = CurrentData.getCurrentOrg();
			
			int employeeId = CurrentData.getCurrentEmployee();
			//System.out.println(employeeId);
		
			//Pagination using LIMIT and OFFSET
			String query ="select * from employee where email=?";
		
				 
		
					PreparedStatement ps = con.prepareStatement(query,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
					ps.setString(1, empDTO.getEmail());
					ResultSet rs = ps.executeQuery();
					
//					
					
					if (!rs.next()) {
						 
						 throw new SQLException("No data found in the database for the specified query.");
						 
					} else {

			        do {
			        	String password = rs.getString("password_hash");
						System.out.println("userPassword " + empDTO.getPasswordHash());
						boolean flag =  Utility.verifyHash(password, empDTO.getPasswordHash());
						System.out.println("flag :" + flag);
						if(flag) {
							Utility.printResultSet(rs);
							rs.previous();
							return Utility.convertToJSON(rs);
						   }
						 return null;
			        } while (rs.next());
			       
			      }
		}
//		public void deleteQuery(int employeeID) throws SQLException{
//			int orgId = CurrentData.getCurrentOrg();
//			String query = "update employee SET employee_state='inactive' where org_id =? and emp_id=?";
//			PreparedStatement ps = con.prepareStatement(query);
//			ps.setInt(1, orgId);
//			if(employeeID != -1) {
//				ps.setInt(2, employeeID);
//				//Generator.where(employeeId.equals(id))
//			}
//			else {
//				System.out.print("Employee ID cannot be null");
//			}
//			int rs = ps.executeUpdate();
//		}
//		
	
}
