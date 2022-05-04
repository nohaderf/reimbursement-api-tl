package com.project1.reimbursementrequestapp.repositories;

import com.project1.reimbursementrequestapp.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {

    /**
     * Finds the employee instance using their full name
     * @param fullName
     * @return the manager instance
     */
    Manager findByFullName(String fullName);

}
