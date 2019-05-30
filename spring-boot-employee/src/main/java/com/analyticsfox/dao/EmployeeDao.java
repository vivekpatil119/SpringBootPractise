package com.analyticsfox.dao;

import org.springframework.data.repository.CrudRepository;

import com.analyticsfox.model.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
