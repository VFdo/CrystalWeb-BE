package com.groupp.crystalweb.dto.request;

import com.groupp.crystalweb.entity.Bill;
import jakarta.persistence.Transient;

import java.util.Date;
import java.util.List;

public record BillRequest(
        String refId,
        Date dateTime,
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
