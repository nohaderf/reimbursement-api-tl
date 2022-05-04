package com.project1.reimbursementrequestapp.repositories;

import com.project1.reimbursementrequestapp.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}
