package com.supermarket.service;

import com.supermarket.dto.ReceiptDTO;
import java.util.List;
import java.util.Map;

public interface ReceiptService {
    ReceiptDTO createReceipt(ReceiptDTO dto);
    ReceiptDTO getReceiptById(Long id);
    List<ReceiptDTO> getAllReceipts();
    List<ReceiptDTO> getReceiptsByDateRange(Long startDate, Long endDate);
    void deleteReceipt(Long id);
    Double getTotalSalesByDateRange(Long startDate, Long endDate);
    Map<String, Object> getSalesAnalytics();
}
