package com.github.lupaev.etran.repository;

import com.github.lupaev.etran.entity.XmlQueryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface XmlQueryLogRepository extends JpaRepository<XmlQueryLog, Long> {
}
