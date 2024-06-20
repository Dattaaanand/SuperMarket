package com.supermarket.controller;

import com.supermarket.dto.ApiResponse;
import com.supermarket.dto.LoginRequest;
import com.supermarket.dto.AuthResponse;
import com.supermarket.dto.EmployeeDTO;
import com.supermarket.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    
    private final EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = employeeService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<EmployeeDTO>> register(@RequestBody EmployeeDTO dto) {
        try {
            EmployeeDTO employee = employeeService.createEmployee(dto, "EMPLOYEE");
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Employee registered successfully", employee));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @RequestParam Long employeeId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        try {
            employeeService.changePassword(employeeId, oldPassword, newPassword);
            return ResponseEntity.ok(new ApiResponse<>(true, "Password changed successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
