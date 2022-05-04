package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import com.project1.reimbursementrequestapp.repositories.ReimbursementRepo;
import static org.junit.Assert.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ReimbursementRepositoryTests {

    @Autowired
    private ReimbursementRepo reimbursementRepo;

    private Reimbursement reimbursement;

    @Test
    public void shouldGetAllReimbursements(){

        List<Reimbursement> reimbursements = reimbursementRepo.findAll();

        Assertions.assertTrue(reimbursements.size() > 0);
    }

    @Test
    public void shouldGetReimbursementById(){

        Reimbursement reimbursement = reimbursementRepo.findById(1).get();

        Assertions.assertEquals(1, reimbursement.getId());
    }

    @Test
    public void shouldSaveReimbursement() {

        Employee employee = Employee.builder()
                .fullName("Freda Hon")
                .email("freda@freda.com")
                .build();

        Manager manager = Manager.builder()
                .fullName("Albus Dumbledore")
                .email("dumbledore@hogwarts.com")
                .build();

        Reimbursement reimbursement = Reimbursement.builder()
                .date("2022/04/22 22:16:30")
                .description("Dinner")
                .amount(50)
                .status("Pending")
                .submitDate("2022/04/28 22:16:30")
                .employee(employee)
                .manager(manager)
                .build();

        reimbursementRepo.save(reimbursement);

        assertTrue(reimbursement.getId() > 0);
    }

    @Test
    @Transactional
    public void shouldUpdateReimbursementStatus(){

        Employee employee = Employee.builder()
                .fullName("Freda Hon")
                .email("freda@freda.com")
                .build();

        Manager manager = Manager.builder()
                .fullName("Albus Dumbledore")
                .email("dumbledore@hogwarts.com")
                .build();

        Reimbursement reimbursement = Reimbursement.builder()
                .date("2022/04/22 22:16:30")
                .description("Dinner")
                .amount(50)
                .status("Denied")
                .submitDate("2022/04/28 22:16:30")
                .employee(employee)
                .manager(manager)
                .build();

        reimbursementRepo.save(reimbursement);

        reimbursementRepo.updateReimbursementById("Denied", manager, 1);

        String status = reimbursement.getStatus();
        assertEquals(status, "Denied");
    }
}
