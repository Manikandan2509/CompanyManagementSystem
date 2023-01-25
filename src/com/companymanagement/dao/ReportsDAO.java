//$Id$
package com.companymanagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.companymanagement.utils.CurrentData;
import com.companymanagement.utils.DbConnection;
import com.companymanagement.utils.Utility;
import com.google.gson.JsonObject;

public class ReportsDAO {
	public JsonObject getReports() throws SQLException{
		Connection con = DbConnection.getCon();
		int orgId = CurrentData.getCurrentOrg();
		int employeeId = CurrentData.getCurrentEmployee();
		//System.out.println(employeeId);
		System.out.println(orgId);
		//Pagination using LIMIT and OFFSET
		String numberOfEmployeesQuery = "select departments.department_name,count(*) from employee inner join departments on departments.department_id = employee.department_id where employee.org_id=? group by 1 order by count DESC";
		String employeesWithMostProjectQuery = "select emp_id,first_name from employee ";
		String projectListWithRevenueQuery = "select project_id,project_name from project order by project_revenue desc";
		PreparedStatement ps = con.prepareStatement("");
		
		if(orgId != -1) {
			ps.setInt(1, orgId);
		}else {
			//throw()
		}
		
		ResultSet rs = ps.executeQuery();
		return Utility.convertToJSON(rs);
		
		
	}
}
