package com.groupp.crystalweb.service;

import com.groupp.crystalweb.dto.request.BillRequest;
import com.groupp.crystalweb.entity.Bill;
import com.groupp.crystalweb.repository.BillRepository;
import lombok.extern.slf4j.Slf4j;
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
        Bill newBill = new Bill(
                billRequest.refId(),
                billRequest.dateTime(),
                billRequest.clientRefId(),
                billRequest.employeeRefId(),
                billRequest.itemsList(),
                billRequest.servicesList(),
                billRequest.additionalCharge(),
                billRequest.totalPrice(),
                billRequest.paymentType(),
                billRequest.status(),
                billRequest.notes()

//                billRequest.billList()
        );
        return billRepository.save(newBill);
    }

    public List<Bill> getAllBills() {

        return (List<Bill>) billRepository.findAll();
    }

    public Bill getBill(String id) {
        Optional<Bill> bill = billRepository.findByRefId(id);
        if(bill.isPresent()){
            return bill.get();
        }
//        TODO: handle response
        return null;
    }

    public Bill update(String id, BillRequest billRequest) {
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

//            existingBill.setBillList(billRequest.billList());
            return billRepository.save(existingBill);
        }
            return null;
    }

    public long deleteBill(String id) {
        return billRepository.deleteByRefId(id);
    }

    /*public float calculateSalary(){
        return
    }*/
}
