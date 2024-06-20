package com.supermarket.service.impl;

import com.supermarket.dto.ReceiptDTO;
import com.supermarket.entity.Receipt;
import com.supermarket.entity.Employee;
import com.supermarket.exception.ResourceNotFoundException;
import com.supermarket.repository.ReceiptRepository;
import com.supermarket.repository.EmployeeRepository;
import com.supermarket.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {
    
    private final ReceiptRepository receiptRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ReceiptDTO createReceipt(ReceiptDTO dto) {
        Employee cashier = employeeRepository.findById(dto.getCashierId())
                .orElseThrow(() -> new ResourceNotFoundException("Cashier not found with id: " + dto.getCashierId()));

        String receiptNumber = "RCP-" + System.currentTimeMillis();

        Receipt receipt = Receipt.builder()
                .receiptNumber(receiptNumber)
                .cashier(cashier)
                .totalAmount(dto.getTotalAmount())
                .taxAmount(dto.getTaxAmount())
                .discountAmount(dto.getDiscountAmount() != null ? dto.getDiscountAmount() : 0.0)
                .itemsCount(dto.getItemsCount())
                .paymentMethod(dto.getPaymentMethod())
                .items(dto.getItems())
                .notes(dto.getNotes())
                .build();

        Receipt saved = receiptRepository.save(receipt);
        return mapToDTO(saved);
    }

    @Override
    public ReceiptDTO getReceiptById(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));
        return mapToDTO(receipt);
    }

    @Override
    public List<ReceiptDTO> getAllReceipts() {
        return receiptRepository.findAllOrderByDateDesc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDTO> getReceiptsByDateRange(Long startDate, Long endDate) {
        return receiptRepository.findByCreatedAtBetween(startDate, endDate)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReceipt(Long id) {
        Receipt receipt = receiptRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receipt not found with id: " + id));
        receiptRepository.delete(receipt);
    }

    @Override
    public Double getTotalSalesByDateRange(Long startDate, Long endDate) {
        Double total = receiptRepository.sumTotalAmountBetweenDates(startDate, endDate);
        return total != null ? total : 0.0;
    }

    @Override
    public Map<String, Object> getSalesAnalytics() {
        List<Receipt> receipts = receiptRepository.findAll();
        
        double totalSales = receipts.stream()
                .mapToDouble(Receipt::getTotalAmount)
                .sum();
        
        double totalTax = receipts.stream()
                .mapToDouble(Receipt::getTaxAmount)
                .sum();
        
        double totalDiscount = receipts.stream()
                .mapToDouble(Receipt::getDiscountAmount)
                .sum();

        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalSales", totalSales);
        analytics.put("totalTax", totalTax);
        analytics.put("totalDiscount", totalDiscount);
        analytics.put("totalReceipts", receipts.size());
        analytics.put("averageTransaction", receipts.isEmpty() ? 0 : totalSales / receipts.size());

        return analytics;
    }

    private ReceiptDTO mapToDTO(Receipt receipt) {
        return ReceiptDTO.builder()
                .id(receipt.getId())
                .receiptNumber(receipt.getReceiptNumber())
                .cashierId(receipt.getCashier().getId())
                .cashierName(receipt.getCashier().getFirstName() + " " + receipt.getCashier().getLastName())
                .totalAmount(receipt.getTotalAmount())
                .taxAmount(receipt.getTaxAmount())
                .discountAmount(receipt.getDiscountAmount())
                .itemsCount(receipt.getItemsCount())
                .paymentMethod(receipt.getPaymentMethod())
                .items(receipt.getItems())
                .notes(receipt.getNotes())
                .createdAt(receipt.getCreatedAt())
                .build();
    }
}
