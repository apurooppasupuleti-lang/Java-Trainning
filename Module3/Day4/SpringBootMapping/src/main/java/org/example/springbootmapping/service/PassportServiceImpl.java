package org.example.springbootmapping.service;

import org.example.springbootmapping.model.Passport;
import org.example.springbootmapping.model.Person;
import org.example.springbootmapping.respository.PassportRepository;
import org.example.springbootmapping.respository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportServiceImpl {
    private final PassportRepository passportRepository;
    private final PersonRepository personRepository;

    public PassportServiceImpl(PassportRepository passportRepository, PersonRepository personRepository) {
        this.passportRepository = passportRepository;
        this.personRepository = personRepository;
    }

    public Passport addPassport(Passport passport){
        Person person=passport.getPerson();
        personRepository.save(person);
        return passportRepository.save(passport);
    }
    public List<Passport> getAll(){
        return passportRepository.findAll();
    }
}