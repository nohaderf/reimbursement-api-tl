package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReimbursementDTOTests {

    private ReimbursementDTO dto = ReimbursementDTO.builder()
            .date("2022/04/22 22:16:30")
            .description("Dinner")
            .amount(50)
            .status("Pending")
            .submitDate("2022/04/28 22:16:30")
            .employee_fullName("Harry Potter")
            .manager_fullName("Albus Dumbledore")
            .build();

    @Test
    void shouldGetDate() {
        assertEquals("2022/04/22 22:16:30", dto.getDate());
    }

    @Test
    void shouldGetDescription() {
        assertEquals("Dinner", dto.getDescription());
    }

    @Test
    void shouldGetAmount() {
        assertEquals(50, dto.getAmount());
    }

    @Test
    void shouldGetStatus() {
        assertEquals("Pending", dto.getStatus());
    }

    @Test
    void shouldGetSubmitDate() {
        assertEquals("2022/04/28 22:16:30", dto.getSubmitDate());
    }

    @Test
    void shouldGetEmployeeFullName() {
        assertEquals("Harry Potter", dto.getEmployee_fullName());
    }

    @Test
    void shouldGetManagerFullName() {
        assertEquals("Albus Dumbledore", dto.getManager_fullName());
    }

    @Test
    void shouldSetDate() {
        dto.setDate("2022/04/20 12:16:30");

        assertEquals("2022/04/20 12:16:30", dto.getDate());
    }

    @Test
    void shouldSetDescription() {
       dto.setDescription("Uber");

        assertEquals("Uber", dto.getDescription());
    }

    @Test
    void shouldSetAmount() {
        dto.setAmount(67);

        assertEquals(67, dto.getAmount());
    }

    @Test
    void shouldSetStatus() {
        dto.setStatus("Approved");

        assertEquals("Approved", dto.getStatus());
    }

    @Test
    void shouldSetSubmitDate() {
        dto.setSubmitDate("2022/04/29 16:16:53");

        assertEquals("2022/04/29 16:16:53", dto.getSubmitDate());
    }

    @Test
    void testEquals() {
        assertEquals(false, dto.equals("hello"));
    }

    @Test
    void testHashCode() {
        assertEquals(1826155672, dto.hashCode());
    }
}