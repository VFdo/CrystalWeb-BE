package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.BillRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Bill;
import com.groupp.crystalweb.entity.Client;
import com.groupp.crystalweb.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {

        this.billRepository = billRepository;
    }

    // creating new bill
    public Bill saveBill(BillRequest billRequest){
        log.info("client save request received");
        try{
            Bill newBill = new Bill();
            newBill.setDateTime(billRequest.dateTime());
            newBill.setClientRefId(billRequest.clientRefId());
            newBill.setEmployeeRefId(billRequest.employeeRefId());
            newBill.setItemsList(billRequest.itemsList());
            newBill.setServicesList(billRequest.servicesList());
            newBill.setAdditionalCharge(billRequest.additionalCharge());
            newBill.setTotalPrice(billRequest.totalPrice());
            newBill.setPaymentType(billRequest.paymentType());
            newBill.setStatus(billRequest.status());
            newBill.setNotes(billRequest.notes());
            return billRepository.save(newBill);
        } catch (Exception e){
            log.info("bill saving failed: {}, {}", e.getMessage(), billRequest.refId());
            throw new RuntimeException("Something went wrong!");
        }
    }

    public Tuple<Object, Object> getAllBills(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Bill> billPage = billRepository.findAll(pageable);
        List<Bill> bills = billPage.getContent();
        PageInfo pageInfo = new PageInfo(
                billPage.getNumber(),
                billPage.getSize(),
                billPage.getTotalElements(),
                billPage.getTotalPages());
        return new Tuple<>(bills, pageInfo);
    }

    public Bill getBill(String id) {
        Optional<Bill> bill = billRepository.findByRefId(id);
        if(bill.isPresent()){
            return bill.get();
        }
//        TODO: handle response
        return null;
    }

    public Bill updateBill(String id, BillRequest billRequest) {
        Optional<Bill> bill = billRepository.findByRefId(id);
        if(bill.isPresent()){
            Bill existingBill = bill.get();
            existingBill.setDateTime(billRequest.dateTime());
            existingBill.setClientRefId(billRequest.clientRefId());
            existingBill.setEmployeeRefId(billRequest.employeeRefId());
            existingBill.setItemsList(billRequest.itemsList());
            existingBill.setServicesList(billRequest.servicesList());
            existingBill.setAdditionalCharge(billRequest.additionalCharge());
            existingBill.setTotalPrice(billRequest.totalPrice());
            existingBill.setPaymentType(billRequest.paymentType());
            existingBill.setStatus(billRequest.status());
            existingBill.setNotes(billRequest.notes());
            return billRepository.save(existingBill);
        } else{
            log.info("bill not found for id: p{}",billRequest.refId());
            return null;
        }
    }

    public long deleteBill(String id) {
        return billRepository.deleteByRefId(id);
    }

    /*public float calculateSalary(){
        return
    }*/
}
