package com.project1.reimbursementrequestapp.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReimbursementDTO {
    private String date;
    private String description;
    private double amount;
    private String status;
    private String submitDate;
    private String employee_fullName;
    private String manager_fullName;
}
