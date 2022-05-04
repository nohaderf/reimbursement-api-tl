package com.project1.reimbursementrequestapp.services;

import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
import com.project1.reimbursementrequestapp.models.Email;
import com.project1.reimbursementrequestapp.models.Employee;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import com.project1.reimbursementrequestapp.repositories.EmailRepository;
import com.project1.reimbursementrequestapp.repositories.EmployeeRepository;
import com.project1.reimbursementrequestapp.repositories.ManagerRepository;
import com.project1.reimbursementrequestapp.repositories.ReimbursementRepo;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class ReimbursementService {

    final Logger logger = LoggerFactory.getLogger(ReimbursementService.class);
    private RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = "http://localhost:9091/emails/new";

    @Setter(onMethod =@__({@Autowired}) )
    private ReimbursementRepo reimbursementRepo;

    @Setter(onMethod =@__({@Autowired}) )
    private EmailRepository emailRepository;

    @Setter(onMethod =@__({@Autowired}) )
    private EmployeeRepository employeeRepository;

    @Setter(onMethod =@__({@Autowired}) )
    private ManagerRepository managerRepository;

    public ReimbursementService(ReimbursementRepo reimbursementRepo) {
        super();
        this.reimbursementRepo = reimbursementRepo;
    }

    public void saveReimbursement(Reimbursement reimbursement) {
        reimbursementRepo.save(reimbursement);
    }

    /**
     * Converts and saves the incoming dto into a reimbursement instance
     * @param dto - the reimbursement data transfer object
     * @return reimbursement instance
     */
    public void convertToEntity(ReimbursementDTO dto) {

        String employeeName = dto.getEmployee_fullName();
        String managerName = dto.getManager_fullName();

        logger.debug("Get all employee via full name: {}", employeeRepository.findByFullName(employeeName));
        logger.debug("Get all manager via full name: {}", employeeRepository.findByFullName(managerName));

        Employee employee = employeeRepository.findByFullName(employeeName);
        Manager manager = managerRepository.findByFullName(managerName);

        String date = dto.getDate();
        String description = dto.getDescription();
        double amount = dto.getAmount();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = formatter.format(now);

        Reimbursement newReimbursement = new Reimbursement();
        newReimbursement.setDate(date);
        newReimbursement.setDescription(description);
        newReimbursement.setAmount(amount);
        newReimbursement.setSubmitDate(dateTime);
        newReimbursement.setStatus("Pending");
        newReimbursement.setEmployee(employee);
        newReimbursement.setManager(manager);

        reimbursementRepo.save(newReimbursement);

        // create new email
        Email email = new Email();
        email.setDate(dateTime);
        email.setRecipient(employee.getEmail());
        email.setSender(manager.getEmail());
        email.setSubject("New Reimbursement Request");
        email.setContent(newReimbursement.toString());

        emailRepository.save(email);
    }

    /**
     * Converts and updates the incoming dto into a reimbursement instance
     * @param dto - the reimbursement data transfer object
     */
    public void updateEntity(ReimbursementDTO dto, int id) {

        String managerName = dto.getManager_fullName();
        String employeeName = dto.getEmployee_fullName();
        Manager manager = managerRepository.findByFullName(managerName);
        Employee employee = employeeRepository.findByFullName(employeeName);

        String status = dto.getStatus();

        logger.debug("Status: {}", dto.getStatus());
        logger.debug("Find manger via full name: {}", managerRepository.findByFullName(managerName));
        logger.debug("Reimbursement id: {}", id);

        reimbursementRepo.updateReimbursementById(status, manager, id);

        // create new email
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String dateTime = formatter.format(now);

        String content = "A recent change has been made on your reimbursement request, ID: " + id;

        Email email = new Email();
        email.setDate(dateTime);
        email.setRecipient(employee.getEmail());
        email.setSender(manager.getEmail());
        email.setSubject("Update on Reimbursement Request");
        email.setContent(content);

        emailRepository.save(email);
    }
}