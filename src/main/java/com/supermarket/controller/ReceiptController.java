package com.supermarket.controller;

import com.supermarket.dto.ApiResponse;
import com.supermarket.dto.ReceiptDTO;
import com.supermarket.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReceiptController {
    
    private final ReceiptService receiptService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReceiptDTO>> createReceipt(@RequestBody ReceiptDTO dto) {
        try {
            ReceiptDTO receipt = receiptService.createReceipt(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Receipt created successfully", receipt));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReceiptDTO>> getReceipt(@PathVariable Long id) {
        try {
            ReceiptDTO receipt = receiptService.getReceiptById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Receipt retrieved successfully", receipt));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReceiptDTO>>> getAllReceipts() {
        try {
            List<ReceiptDTO> receipts = receiptService.getAllReceipts();
            return ResponseEntity.ok(new ApiResponse<>(true, "Receipts retrieved successfully", receipts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<ReceiptDTO>>> getReceiptsByDateRange(
            @RequestParam Long startDate,
            @RequestParam Long endDate) {
        try {
            List<ReceiptDTO> receipts = receiptService.getReceiptsByDateRange(startDate, endDate);
            return ResponseEntity.ok(new ApiResponse<>(true, "Receipts retrieved successfully", receipts));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/analytics/total-sales")
    public ResponseEntity<ApiResponse<Double>> getTotalSales(
            @RequestParam Long startDate,
            @RequestParam Long endDate) {
        try {
            Double total = receiptService.getTotalSalesByDateRange(startDate, endDate);
            return ResponseEntity.ok(new ApiResponse<>(true, "Total sales retrieved successfully", total));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/analytics")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getSalesAnalytics() {
        try {
            Map<String, Object> analytics = receiptService.getSalesAnalytics();
            return ResponseEntity.ok(new ApiResponse<>(true, "Sales analytics retrieved successfully", analytics));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteReceipt(@PathVariable Long id) {
        try {
            receiptService.deleteReceipt(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Receipt deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
