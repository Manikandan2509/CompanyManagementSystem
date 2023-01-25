//$Id$
package com.companymanagement.utils;

public class CurrentData {

	private static final ThreadLocal<Integer> CURRENT_ORG = new ThreadLocal<Integer>();
	private static final ThreadLocal<Integer> CURRENT_EMPLOYEE = new ThreadLocal<Integer>();
	private static final ThreadLocal<Integer> CURRENT_DEPARTMENT = new ThreadLocal<Integer>();
	private static final ThreadLocal<Integer> CURRENT_PROJECT = new ThreadLocal<Integer>();// private static final ThreadLocal<Integer> CURRENT_DEPARTMENT = new ThreadLocal<Integer>();//Need to check if we really need EmpID in threadlocal
	private static final ThreadLocal<Integer> CURRENT_TASK = new ThreadLocal<Integer>();
	private static final ThreadLocal<Boolean> IS_BULK_REQUEST = new ThreadLocal<Boolean>();
    
	public static int getCurrentDepartment() {
		return CURRENT_DEPARTMENT.get();
	}
    public static void  setCurrentDepartment(int departmentID) {
		CURRENT_DEPARTMENT.set(departmentID);
	}

	public static int getCurrentOrg() {
        return CURRENT_ORG.get();
    }

    public static void setCurrentOrg(int orgId) {
        CURRENT_ORG.set(orgId);
    }
    public static void setCurrentEmployee(int employeeID) {
    	CURRENT_EMPLOYEE.set(employeeID);
    }
    
    public static int getCurrentEmployee() {
        return CURRENT_EMPLOYEE.get();
    }
	public static int getCurrentProject() {
		return CURRENT_PROJECT.get();
	}
	 public static void setCurrentProject(int projectID) {
	    	CURRENT_PROJECT.set(projectID);
	    }
	public static int getCurrentTask() {
			return CURRENT_TASK.get();
		}
	public static void setCurrentTask(int taskID) {
		    	CURRENT_TASK.set(taskID);
	}
		 
	public static boolean getIsBulkRequest() {
				return IS_BULK_REQUEST.get();
	}
	public static void setIsBulkRequest(Boolean flag) {
				 IS_BULK_REQUEST.set(flag);
	}
		 
		 

	
}
