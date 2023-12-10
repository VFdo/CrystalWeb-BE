package com.groupp.crystalweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bill {
    @Id
    private String refId;
    private Date dateTime;
    private String clientRefId;
    private String employeeRefId;

    @Transient
    private List<String> itemsList;

    @Transient
    private List<String> servicesList;
    private Float additionalCharge;
    private Float totalPrice;

    public enum PaymentType{
        CASH,
        CARD,
        OTHER;
    }

    private PaymentType paymentType;

    public enum Status{
        PENDING,
        COMPLETE,
        DELETE;
    }

    private Status status;

    private String notes;

    public String getRefId() {
        return refId;
    }

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

    public void setRefId(String refId) {
        this.refId = refId;
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
