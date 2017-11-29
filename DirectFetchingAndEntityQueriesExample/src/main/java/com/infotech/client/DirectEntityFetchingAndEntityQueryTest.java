package com.infotech.client;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.infotech.entities.Department;
import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class DirectEntityFetchingAndEntityQueryTest {

	public static void main(String[] args) {
		directEntityFetching();
		//entityQueryFetching();
		
	}

	private static void entityQueryFetching() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			//Direct Entity fetching exmaple
			Long employeeId = 1L;
			 Query<?> query = session.createQuery("SELECT e FROM Employee e WHERE e.id =:empId");
			 query.setParameter("empId", employeeId);
			 Object object = query.getSingleResult();
			 Employee employee =(Employee)object;
			if(employee != null){
				System.out.println("Employee details::::::");
				System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+employee.getUsername()+"\t"+employee.getPassword()+"\t"+employee.getAccressLevel());
				System.out.println("Employee's department details:");
				Department department = employee.getDepartment();
				if(department != null)
					System.out.println(department.getId()+"\t"+department.getDeptName());
				else
					System.out.println("Department details not found for employee whose ID is:"+employeeId);
				
			}else{
				System.out.println("Employee not found with ID:"+employeeId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	private static void directEntityFetching() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			//Direct Entity fetching exmaple
			Long employeeId = 1L;
			Employee employee = session.get(Employee.class, employeeId);
			if(employee != null){
				System.out.println("Employee details::::::");
				System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+employee.getUsername()+"\t"+employee.getPassword()+"\t"+employee.getAccressLevel());
				System.out.println("Employee's department details:");
				Department department = employee.getDepartment();
				if(department != null)
					System.out.println(department.getId()+"\t"+department.getDeptName());
				else
					System.out.println("Department details not found for employee whose ID is:"+employeeId);
				
			}else{
				System.out.println("Employee not found with ID:"+employeeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
