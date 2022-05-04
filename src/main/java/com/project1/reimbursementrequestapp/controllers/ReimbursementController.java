package com.project1.reimbursementrequestapp.controllers;

import com.project1.reimbursementrequestapp.dtos.ReimbursementDTO;
import com.project1.reimbursementrequestapp.models.Manager;
import com.project1.reimbursementrequestapp.models.Reimbursement;
import com.project1.reimbursementrequestapp.repositories.ManagerRepository;
import com.project1.reimbursementrequestapp.repositories.ReimbursementRepo;
import com.project1.reimbursementrequestapp.services.ReimbursementService;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("reimbursements")
@Controller
public class ReimbursementController {

    final Logger logger = LoggerFactory.getLogger(ReimbursementController.class);

    @Setter(onMethod =@__({@Autowired}) )
    private ReimbursementRepo reimbursementRepo;

    @Autowired
    private ReimbursementService reimbursementService;

    @Autowired
    private ManagerRepository managerRepository;

    /**
     * Get all reimbursements
     * @return all reimbursement instances
     */
    @GetMapping
    public String getAllReimbursements(Model model) {
        List<Reimbursement> reimbursements = reimbursementRepo.findAll();
        model.addAttribute("something", "this is coming from the controller");
        model.addAttribute("reimbursement", reimbursements);
        logger.debug("Get all reimbursements: {}", reimbursementRepo.findAll());
        return "reimbursements";
    }

    /**
     * Get reimbursement by id
     * @param id - reimbursement id
     * @return reimbursement instance
     */
    @GetMapping(path="{id}") //reimbursements/{id}
    public ResponseEntity getReimbursementWithId(@PathVariable Integer id) {
        logger.debug("Get reimbursement with ID: {}", reimbursementRepo.findById(id));
        return ResponseEntity.ok(reimbursementRepo.findById(id));
    }

    /**
     * Get all reimbursement requests associated with manager id
     * @param id - manager id
     * @return All reimbursement requests for manager with given id
     */
    @GetMapping(path="requests/managers/{id}")
    public String getAllReimbursementsWithManagerId(Model model, @PathVariable Integer id) {
        logger.debug("Get reimbursements associated with manager ID: {}", reimbursementRepo.findAllByManagerId(id));
        List<Reimbursement> reimbursements = reimbursementRepo.findAllByManagerId(id);
        model.addAttribute("reimbursement", reimbursements);
        return "manager";
    }

    /**
     * Get all reimbursement requests associated with employee id
     * @param id - employee id
     * @param model - model map (abstracts view technology)
     * @return All reimbursement requests from employee with given id
     */
    @GetMapping(path="requests/employees/{id}")
    public String getAllReimbursementsWithEmployeeId(Model model, @PathVariable Integer id) {
        List<Reimbursement> reimbursements = reimbursementRepo.findAllByEmployeeId(id);
        model.addAttribute("reimbursement", reimbursements);
        logger.debug("Get all reimbursements associated with Employee ID: {}", reimbursementRepo.findAllByEmployeeId(id));
        return "employee";
    }

    /**
     * Get the form for creating a new reimbursement
     * @param model - model map (abstracts view technology)
     * @return form - returns the form.html page
     */
    @GetMapping("/requests/employees/form")
    public String newReimbursement(Model model) {
        ReimbursementDTO dto = new ReimbursementDTO();
        List<Manager> managers =  managerRepository.findAll();
        model.addAttribute("dto", dto);
        model.addAttribute("managers", managers);
        return "form";
    }

    /**
     * Creates a new reimbursement
     * @param dto - the reimbursement data transfer object
     * @return new - returns the new.html page
     */
    @PostMapping("/new")
    public String newReimbursement(ReimbursementDTO dto) {
        reimbursementService.convertToEntity(dto);
        return "new";
    }

    /**
     * Gets the form to update an existing reimbursement
     * Pre-populates the fields with the existing reimbursement
     * @param id - reimbursement id
     * @param model - model map (abstracts view technology)
     * @return edit - returns the edit.hmtl page
     */
    @GetMapping("requests/managers/reimbursements/edit/{id}")
    public String editReimbursement(@PathVariable int id, Model model) {
        List<Manager> managers =  managerRepository.findAll();
        Reimbursement reimbursement = reimbursementRepo.findById(id).orElse(null);
        model.addAttribute("reimbursement", reimbursement);
        model.addAttribute("managers", managers);
        return "edit";
    }

    /**
     * Updates an existing reimbursement
     * @param reimbursement - the reimbursement object
     * @return update - returns the update.html page
     */
    @PostMapping("/update")
    public String editReimbursement(@ModelAttribute("reimbursement") Reimbursement reimbursement) {

        int id = reimbursement.getId();
        String description = reimbursement.getDescription();
        String date = reimbursement.getDate();
        double amount = reimbursement.getAmount();
        String status = reimbursement.getStatus();
        String submitDate = reimbursement.getSubmitDate();
        String employee_fullName = reimbursement.getEmployee().getFullName();
        String manager_fullName = reimbursement.getManager().getFullName();

        ReimbursementDTO dto = new ReimbursementDTO(date, description, amount, status, submitDate, employee_fullName, manager_fullName);

        reimbursementService.updateEntity(dto, id);
        return "update";
    }

}
