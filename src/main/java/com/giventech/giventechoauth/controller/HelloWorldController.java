package com.giventech.giventechoauth.controller;

import com.giventech.giventechoauth.model.Welcome;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {

    @GetMapping("hello")
    @ResponseBody
    public ResponseEntity<Welcome> getWelcome(@RequestParam String name) {
        Welcome welcomePerson = new Welcome(name);
        ResponseEntity<Welcome> responseEntity = ResponseEntity.ok(welcomePerson);
        return responseEntity;
    }
}
