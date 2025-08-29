package com.dkupadhy.hospitalManagement.repository;

import com.dkupadhy.hospitalManagement.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}