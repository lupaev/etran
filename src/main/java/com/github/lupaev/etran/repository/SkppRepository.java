package com.github.lupaev.etran.repository;

import com.github.lupaev.etran.entity.Skpp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface SkppRepository extends JpaRepository<Skpp, Long>, JpaSpecificationExecutor<Skpp> {

}
