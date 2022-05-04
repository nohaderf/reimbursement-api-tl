package com.project1.reimbursementrequestapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Emails")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Email {

    @Id
    @Column(name = "id", columnDefinition = "AUTO_INCREMENT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String date;
    private String recipient; //
    private String sender;   //
    private String subject;
    private String content;

    // Many email address to many emails
    // Many to many
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//        name="email_address",
//        joinColumns = @JoinColumn(name="address_id"),
//        inverseJoinColumns = @JoinColumn(name="email_id"))
//    private Set<Address> emailAddresses;
}
