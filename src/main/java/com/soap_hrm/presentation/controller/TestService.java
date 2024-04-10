package com.soap_hrm.presentation.controller;

import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService
public class TestService {

    public String getTestMethod(){
        return "hello from the other side";
    }
}
