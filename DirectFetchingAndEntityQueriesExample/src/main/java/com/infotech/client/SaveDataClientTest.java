package com.infotech.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.infotech.entities.Department;
import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		Transaction tx =  null;
		try(Session session = HibernateUtil.getSessionFactory().openSession() ) {
			tx = session.beginTransaction();
			
			Employee employee1 = new Employee();
			employee1.setEmployeeName("Sean Murphy");
			employee1.setUsername("seanm");
			employee1.setPassword("pass#123");
			employee1.setAccressLevel(1);
			
			Employee employee2 = new Employee();
			employee2.setEmployeeName("Barry Johnson");
			employee2.setUsername("barryj");
			employee2.setPassword("barry@123");
			employee2.setAccressLevel(1);
			
			Employee employee3 = new Employee();
			employee3.setEmployeeName("Janet Warren");
			employee3.setUsername("janetw");
			employee3.setPassword("janet#123");
			employee3.setAccressLevel(1);
			
			Employee employee4 = new Employee();
			employee4.setEmployeeName("Pamela Smith");
			employee4.setUsername("seanm");
			employee4.setPassword("pamela#123");
			employee4.setAccressLevel(2);
			
			Employee employee5 = new Employee();
			employee5.setEmployeeName("Eric Miller");
			employee5.setUsername("ericm");
			employee5.setPassword("eric#123");
			employee5.setAccressLevel(2);
			
			Department department1 = new Department();
			department1.setDeptName("IT");
			
			department1.getEmployees().add(employee1);
			employee1.setDepartment(department1);
			
			department1.getEmployees().add(employee2);
			employee2.setDepartment(department1);
			
			department1.getEmployees().add(employee3);
			employee3.setDepartment(department1);
			
			Department department2 = new Department();
			department2.setDeptName("Finance");
			
			department2.getEmployees().add(employee4);
			employee4.setDepartment(department2);
			department2.getEmployees().add(employee5);
			employee5.setDepartment(department2);
			
			session.persist(department1);
			session.persist(department2);
			
			tx.commit();
		} catch (Exception e) {
			if(tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}
}