package com.project1.reimbursementrequestapp.repositories;

import com.project1.reimbursementrequestapp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Finds the employee instance using their full name
     * @param fullName
     * @return the employee instance
     */
    Employee findByFullName(String fullName);

}
