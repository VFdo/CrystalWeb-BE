package com.groupp.crystalweb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.groupp.crystalweb.common.DateFormats;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class Bill extends SerializableObject {

    @NotNull(message = "Bill Created Date is required")
    @JsonFormat(pattern = DateFormats.LOCAL_DATE_TIME)
    private LocalDateTime dateTime;

    @NotNull(message = "Client ID is required")
    @ManyToOne
    @JsonIgnore
    private Client clientRefId;

    @JsonProperty("clientId")
    public String getClientId() {
        return (clientRefId != null) ? clientRefId.getRefId() : null;
    }

    @NotNull(message = "Employee ID is required")
    @ManyToOne
    @JsonIgnore
    private Employee employeeRefId;

    @JsonProperty("employeeId")
    public String getEmployeeId() {
        return (employeeRefId != null) ? employeeRefId.getRefId() : null;
    }

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "bill_item_list", joinColumns = @JoinColumn(name = "bill_id"))
    private List<String> itemsList;

    @Size(min = 1)
    @ElementCollection
    @CollectionTable(name = "Bill_service_list", joinColumns = @JoinColumn(name = "bill_id"))
    private List<String> servicesList;

    private Float additionalCharge;

    @NotNull(message = "Total Price is required")
    private Float totalPrice;

    public enum PaymentType{
        CASH,
        CARD,
        OTHER;
    }

    @NotNull(message = "Payment Type is required")
    private PaymentType paymentType;

    public enum Status{
        PENDING,
        COMPLETE,
        DELETE;
    }

    @NotNull(message = "Status is required")
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

}
