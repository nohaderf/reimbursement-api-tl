package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReimbursementTests {

    Employee employee = Employee.builder()
            .id(1)
            .fullName("Freda Hon")
            .email("freda@freda.com")
            .build();

    Manager manager = Manager.builder()
            .id(1)
            .fullName("Albus Dumbledore")
            .email("dumbledore@hogwarts.com")
            .build();

    Reimbursement reimbursement = Reimbursement.builder()
            .id(1)
            .date("2022/04/22 22:16:30")
            .description("Dinner")
            .amount(50)
            .status("Pending")
            .submitDate("2022/04/28 22:16:30")
            .employee(employee)
            .manager(manager)
            .build();

    @Test
    void shouldGetId() {
        assertEquals(1, reimbursement.getId());
    }

    @Test
    void shouldGetDate() {
        assertEquals("2022/04/22 22:16:30", reimbursement.getDate());
    }

    @Test
    void shouldGetDescription() {
        assertEquals("Dinner", reimbursement.getDescription());
    }

    @Test
    void shouldGetAmount() {
        assertEquals(50, reimbursement.getAmount());
    }

    @Test
    void shouldGetStatus() {
        assertEquals("Pending", reimbursement.getStatus());
    }

    @Test
    void shouldGetSubmitDate() {
        assertEquals("2022/04/28 22:16:30", reimbursement.getSubmitDate());
    }

    @Test
    void shouldGetEmployee() {
    }

    @Test
    void getManager() {
    }

    @Test
    void shouldSetId() {
        reimbursement.setId(2);

        assertEquals(2, reimbursement.getId());
    }

    @Test
    void shouldSetDate() {
        reimbursement.setDate("2022/04/20 12:16:30");

        assertEquals("2022/04/20 12:16:30", reimbursement.getDate());
    }

    @Test
    void shouldSetDescription() {
        reimbursement.setDescription("Uber");

        assertEquals("Uber", reimbursement.getDescription());
    }

    @Test
    void shouldSetAmount() {
        reimbursement.setAmount(67);

        assertEquals(67, reimbursement.getAmount());
    }

    @Test
    void shouldSetStatus() {
        reimbursement.setStatus("Approved");

        assertEquals("Approved", reimbursement.getStatus());
    }

    @Test
    void shouldSetSubmitDate() {
        reimbursement.setSubmitDate("2022/04/29 16:16:53");

        assertEquals("2022/04/29 16:16:53", reimbursement.getSubmitDate());
    }

    @Test
    void testEquals() {
        assertEquals(false, reimbursement.equals("hello"));
    }

    @Test
    void testHashCode() {
        assertEquals(-1448402644, reimbursement.hashCode());
    }
}