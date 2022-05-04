package com.project1.reimbursementrequestapp;

import com.project1.reimbursementrequestapp.models.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTests {

    Email email = Email.builder()
            .id(1)
            .date("2022-05-01 21:00:41")
            .sender("freda@freda.com")
            .recipient("snape@hogwarts.com")
            .subject("New reimbursement request")
            .content("A new reimbursement request has been submitted. Please review teh reimbursement request.")
            .build();

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void shouldGetId() {
        assertEquals(1, email.getId());
    }

    @Test
    void shouldGetDate() {
        assertEquals("2022-05-01 21:00:41", email.getDate());
    }

    @Test
    void shouldGetRecipient() {
        assertEquals("snape@hogwarts.com", email.getRecipient());
    }

    @Test
    void shouldGetSender() {
        assertEquals("freda@freda.com", email.getSender());
    }

    @Test
    void shouldGetSubject() {
        assertEquals("New reimbursement request", email.getSubject());
    }

    @Test
    void shouldGetContent() {
        assertEquals("A new reimbursement request has been submitted. Please review teh reimbursement request.", email.getContent());
    }

    @Test
    void shouldSetId() {
        email.setId(2);
        assertEquals(2, email.getId());
    }

    @Test
    void shouldSetDate() {
        email.setDate("2022-03-01 17:06:31");
        assertEquals("2022-03-01 17:06:31", email.getDate());
    }

    @Test
    void shouldSetRecipient() {
        email.setRecipient("freda@hogwarts.com");
        assertEquals("freda@hogwarts.com", email.getRecipient());
    }

    @Test
    void shouldSetSender() {
        email.setSender("freda@hogwarts.com");
        assertEquals("freda@hogwarts.com", email.getSender());
    }

    @Test
    void shouldSetSubject() {
        email.setSubject("Hello, World!");
        assertEquals("Hello, World!", email.getSubject());
    }

    @Test
    void shouldSetContent() {
        email.setContent("Hi, again!");
        assertEquals("Hi, again!", email.getContent());
    }

}