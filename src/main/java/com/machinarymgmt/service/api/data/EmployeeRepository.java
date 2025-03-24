package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
