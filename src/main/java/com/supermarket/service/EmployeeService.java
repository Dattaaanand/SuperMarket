package com.supermarket.service;

import com.supermarket.dto.EmployeeDTO;
import com.supermarket.dto.AuthResponse;
import com.supermarket.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO dto, String role);
    EmployeeDTO updateEmployee(Long id, EmployeeDTO dto);
    EmployeeDTO getEmployeeById(Long id);
    EmployeeDTO getEmployeeByUsername(String username);
    List<EmployeeDTO> getAllEmployees();
    List<EmployeeDTO> getEmployeesByRole(String role);
    void deleteEmployee(Long id);
    void activateEmployee(Long id);
    void deactivateEmployee(Long id);
    AuthResponse authenticate(String username, String password);
    void changePassword(Long employeeId, String oldPassword, String newPassword);
}
