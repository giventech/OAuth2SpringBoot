package com.giventech.giventechoauth.controller;

import com.giventech.giventechoauth.model.Welcome;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HelloWorldController {


    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/api/hello")
    @ResponseBody
    public ResponseEntity<Welcome> getWelcome(@RequestParam String name) {
        Welcome welcomePerson = new Welcome(name);
        ResponseEntity<Welcome> responseEntity = ResponseEntity.ok(welcomePerson);
        return responseEntity;
    }
}
