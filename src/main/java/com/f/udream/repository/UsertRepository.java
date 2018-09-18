package com.f.udream.repository;

import com.f.udream.entity.Usert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsertRepository extends JpaRepository<Usert, Long>, JpaSpecificationExecutor<Usert> {
}

