package com.holberton.realestate.controller;

import com.holberton.realestate.dto.HelloResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping("/api/hello")
  public HelloResponse sayHello(@RequestParam(defaultValue = "World") String name) {
    HelloResponse greeting = new HelloResponse("Hello, " + name + "!");
    return greeting;
  }
}
