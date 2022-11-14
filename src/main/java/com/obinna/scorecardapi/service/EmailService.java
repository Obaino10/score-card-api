package com.obinna.scorecardapi.service;



public interface EmailService {


    boolean sendEmail(String body, String subject, String email);
}

