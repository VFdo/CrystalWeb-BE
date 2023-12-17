package com.groupp.crystalweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class Bill extends SerializableObject {

    @NotBlank(message = "Bill Created Date is required")
    private Date dateTime;

    @NotBlank(message = "Client ID is required")
    private String clientRefId;

    @NotBlank(message = "Employee ID is required")
    private String employeeRefId;

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "item-list", joinColumns = @JoinColumn(name = "bill_id"))
    private List<String> itemsList;

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "service_list", joinColumns = @JoinColumn(name = "bill_id"))
    private List<String> servicesList;

    private Float additionalCharge;

    @NotBlank(message = "Total Price is required")
    private Float totalPrice;

    public enum PaymentType{
        CASH,
        CARD,
        OTHER;
    }

    @NotBlank(message = "Payment Type is required")
    private PaymentType paymentType;

    public enum Status{
        PENDING,
        COMPLETE,
        DELETE;
    }

    @NotBlank(message = "Status is required")
    private Status status;

    private String notes;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getClientRefId() {
        return clientRefId;
    }

    public void setClientRefId(String clientRefId) {
        this.clientRefId = clientRefId;
    }

    public String getEmployeeRefId() {
        return employeeRefId;
    }

    public void setEmployeeRefId(String employeeRefId) {
        this.employeeRefId = employeeRefId;
    }

    public List<String> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<String> itemsList) {
        this.itemsList = itemsList;
    }

    public List<String> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<String> servicesList) {
        this.servicesList = servicesList;
    }

    public Float getAdditionalCharge() {
        return additionalCharge;
    }

    public void setAdditionalCharge(Float additionalCharge) {
        this.additionalCharge = additionalCharge;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    //    private List<String> billList;
}
