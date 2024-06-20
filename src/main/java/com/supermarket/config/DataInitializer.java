package com.supermarket.config;

import com.supermarket.entity.Employee;
import com.supermarket.entity.Section;
import com.supermarket.entity.Product;
import com.supermarket.repository.EmployeeRepository;
import com.supermarket.repository.SectionRepository;
import com.supermarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final SectionRepository sectionRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize default data only if empty
        if (employeeRepository.count() == 0) {
            initializeEmployees();
            initializeSections();
            initializeProducts();
            System.out.println("✅ Default data initialized successfully!");
        }
    }

    private void initializeEmployees() {
        // Admin
        Employee admin = Employee.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .firstName("System")
                .lastName("Admin")
                .email("admin@supermarket.com")
                .phoneNumber("1234567890")
                .address("123 Main St")
                .salary(5000.0)
                .role("ADMIN")
                .isActive(true)
                .build();
        employeeRepository.save(admin);

        // Cashier
        Employee cashier = Employee.builder()
                .username("cashier1")
                .password(passwordEncoder.encode("cashier123"))
                .firstName("John")
                .lastName("Cashier")
                .email("cashier@supermarket.com")
                .phoneNumber("9876543210")
                .address("456 Oak St")
                .salary(2500.0)
                .role("CASHIER")
                .isActive(true)
                .build();
        employeeRepository.save(cashier);

        // Storekeeper
        Employee storekeeper = Employee.builder()
                .username("storekeeper1")
                .password(passwordEncoder.encode("store123"))
                .firstName("Jane")
                .lastName("Storekeeper")
                .email("storekeeper@supermarket.com")
                .phoneNumber("5551234567")
                .address("789 Pine St")
                .salary(2000.0)
                .role("STOREKEEPER")
                .isActive(true)
                .build();
        employeeRepository.save(storekeeper);
    }

    private void initializeSections() {
        Section[] sections = {
            Section.builder().name("Fruits & Vegetables").description("Fresh produce").build(),
            Section.builder().name("Dairy & Eggs").description("Milk, cheese, eggs").build(),
            Section.builder().name("Meat & Seafood").description("Fresh meat and fish").build(),
            Section.builder().name("Bakery").description("Bread, cakes, pastries").build(),
            Section.builder().name("Beverages").description("Drinks and juices").build(),
            Section.builder().name("Snacks").description("Chips, cookies, snacks").build(),
            Section.builder().name("Frozen Foods").description("Frozen items").build(),
            Section.builder().name("Personal Care").description("Hygiene products").build()
        };

        for (Section section : sections) {
            sectionRepository.save(section);
        }
    }

    private void initializeProducts() {
        Section fruitSection = sectionRepository.findByNameIgnoreCase("Fruits & Vegetables").orElse(null);
        Section dairySection = sectionRepository.findByNameIgnoreCase("Dairy & Eggs").orElse(null);
        Section bakeSection = sectionRepository.findByNameIgnoreCase("Bakery").orElse(null);
        Section snackSection = sectionRepository.findByNameIgnoreCase("Snacks").orElse(null);

        if (fruitSection != null) {
            Product[] products = {
                Product.builder()
                        .name("Fresh Apples")
                        .description("Crisp red apples")
                        .price(1.99)
                        .quantity(100)
                        .barcode("1001")
                        .reorderLevel(20)
                        .section(fruitSection)
                        .isActive(true)
                        .build(),
                Product.builder()
                        .name("Bananas")
                        .description("Yellow bananas")
                        .price(0.59)
                        .quantity(150)
                        .barcode("1002")
                        .reorderLevel(30)
                        .section(fruitSection)
                        .isActive(true)
                        .build(),
                Product.builder()
                        .name("Carrots")
                        .description("Fresh orange carrots")
                        .price(0.79)
                        .quantity(80)
                        .barcode("1003")
                        .reorderLevel(15)
                        .section(fruitSection)
                        .isActive(true)
                        .build()
            };

            for (Product product : products) {
                productRepository.save(product);
            }
        }

        if (dairySection != null) {
            Product milk = Product.builder()
                    .name("Whole Milk 1L")
                    .description("Fresh whole milk")
                    .price(2.99)
                    .quantity(50)
                    .barcode("2001")
                    .reorderLevel(20)
                    .section(dairySection)
                    .isActive(true)
                    .build();
            productRepository.save(milk);
        }

        if (bakeSection != null) {
            Product bread = Product.builder()
                    .name("Whole Wheat Bread")
                    .description("Freshly baked bread")
                    .price(2.49)
                    .quantity(30)
                    .barcode("3001")
                    .reorderLevel(10)
                    .section(bakeSection)
                    .isActive(true)
                    .build();
            productRepository.save(bread);
        }

        if (snackSection != null) {
            Product chips = Product.builder()
                    .name("Potato Chips")
                    .description("Crispy chips")
                    .price(1.49)
                    .quantity(100)
                    .barcode("4001")
                    .reorderLevel(25)
                    .section(snackSection)
                    .isActive(true)
                    .build();
            productRepository.save(chips);
        }
    }
}
