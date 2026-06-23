package org.example.springbootmapping.controller;

import org.example.springbootmapping.model.Passport;
import org.example.springbootmapping.service.PassportServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passports")
public class PassportController {
    private final PassportServiceImpl passportService;

    public PassportController(PassportServiceImpl passportService) {
        this.passportService = passportService;
    }

    @PostMapping
    public Passport addPassport(@RequestBody Passport passport){
        return passportService.addPassport(passport);
    }
    @GetMapping
    public List<Passport> getAll(){
        return passportService.getAll();
    }
}