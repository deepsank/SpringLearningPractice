package com.dkupadhy.hospitalManagement.repository;

import com.dkupadhy.hospitalManagement.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}