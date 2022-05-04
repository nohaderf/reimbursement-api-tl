package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ReimbursementServiceTests {

    private ReimbursementDTO dto = ReimbursementDTO.builder()
            .date("2022/04/22 22:16:30")
            .description("Dinner")
            .amount(50)
            .status("Pending")
            .submitDate("2022/04/28 22:16:30")
            .employee_fullName("Harry Potter")
            .manager_fullName("Albus Dumbledore")
            .build();

    Manager manager = Manager.builder()
            .id(1)
            .fullName("Albus Dumbledore")
            .email("dumbledore@hogwarts.com")
            .build();

    Employee employee = Employee.builder()
            .id(1)
            .fullName("Freda Hon")
            .email("freda@freda.com")
            .build();

    private Reimbursement reimbursement;


    @Test
    void convertToEntity() {
        String date = dto.getDate();
        String description = dto.getDescription();
        double amount = dto.getAmount();

        Reimbursement newReimbursement = new Reimbursement();
        newReimbursement.setDate(date);
        newReimbursement.setDescription(description);
        newReimbursement.setAmount(amount);
        newReimbursement.setSubmitDate("2022/04/28 22:16:30");
        newReimbursement.setStatus("Pending");
        newReimbursement.setEmployee(employee);
        newReimbursement.setManager(manager);

        assertEquals(newReimbursement.getDate(), date);
        assertEquals(newReimbursement.getDescription(), description);
        Assertions.assertEquals(newReimbursement.getAmount(), amount);
        assertEquals(newReimbursement.getStatus(), "Pending");
    }

    @Test
    void updateEntity() {
        dto.setStatus("Approved");
        dto.setManager_fullName("Severus Snape");

        assertEquals(dto.getStatus(), "Approved");
        assertEquals(dto.getManager_fullName(), "Severus Snape");
    }

}