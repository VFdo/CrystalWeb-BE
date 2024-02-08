package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Bill;

import java.time.LocalDateTime;
import java.util.List;

public record BillRequest(
        LocalDateTime dateTime,
        String clientRefId,
        String employeeRefId,
        List<String> itemsList,
        List<String> servicesList,
        Float additionalCharge,
        Float totalPrice,
        Bill.PaymentType paymentType,
        Bill.Status status,
        String notes
//        List<String>billList
) {
}
