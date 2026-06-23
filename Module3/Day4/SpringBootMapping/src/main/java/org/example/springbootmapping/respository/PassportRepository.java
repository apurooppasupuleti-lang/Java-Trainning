package org.example.springbootmapping.respository;

import org.example.springbootmapping.model.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
