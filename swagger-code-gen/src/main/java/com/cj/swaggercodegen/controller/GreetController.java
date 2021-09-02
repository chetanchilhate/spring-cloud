package com.cj.swaggercodegen.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/greet")
public class GreetController {

    @GetMapping("")
    public ResponseEntity<String> greet() throws InterruptedException {
        Thread.sleep(10000);
        return ResponseEntity.ok("Hello") ;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<String> greetPepole(@PathVariable("name") String name) {
        return ResponseEntity.ok("Hello ".concat(name)) ;
    }
}
