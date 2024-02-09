package com.groupp.crystalweb.service;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.BillRequest;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.*;
import com.groupp.crystalweb.repository.BillRepository;
import com.groupp.crystalweb.repository.ClientRepository;
import com.groupp.crystalweb.repository.EmployeeRepository;
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
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public BillService(BillRepository billRepository, ClientRepository clientRepository, EmployeeRepository employeeRepository) {

        this.billRepository = billRepository;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    // creating new bill
    public Bill saveBill(BillRequest billRequest){
        log.info("client save request received");
        try{
            Optional<Client> client = clientRepository.findByRefId(billRequest.clientRefId());
            Optional<Employee> employee = employeeRepository.findByRefId(billRequest.employeeRefId());
            Bill newBill = new Bill();
            newBill.setDateTime(billRequest.dateTime());
            if(client.isPresent()){
                newBill.setClientRefId(client.get());
            }
            if(employee.isPresent()){
                newBill.setEmployeeRefId(employee.get());
            }
            newBill.setItemsList(billRequest.itemsList());
            newBill.setServicesList(billRequest.servicesList());
            newBill.setAdditionalCharge(billRequest.additionalCharge());
            newBill.setTotalPrice(billRequest.totalPrice());
            newBill.setPaymentType(billRequest.paymentType());
            newBill.setStatus(billRequest.status());
            newBill.setNotes(billRequest.notes());
            return billRepository.save(newBill);
        } catch (Exception e){
            log.info("bill saving failed: {}", e.getMessage());
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
            Optional<Client> client = clientRepository.findByRefId(billRequest.clientRefId());
            Optional<Employee> employee = employeeRepository.findByRefId(billRequest.employeeRefId());
            Bill existingBill = bill.get();
            existingBill.setDateTime(billRequest.dateTime());
            if(client.isPresent()){
                existingBill.setClientRefId(client.get());
            }
            if(employee.isPresent()){
                existingBill.setEmployeeRefId(employee.get());
            }
            existingBill.setItemsList(billRequest.itemsList());
            existingBill.setServicesList(billRequest.servicesList());
            existingBill.setAdditionalCharge(billRequest.additionalCharge());
            existingBill.setTotalPrice(billRequest.totalPrice());
            existingBill.setPaymentType(billRequest.paymentType());
            existingBill.setStatus(billRequest.status());
            existingBill.setNotes(billRequest.notes());
            return billRepository.save(existingBill);
        } else{
            log.info("bill not found for id: {}",id);
            return null;
        }
    }

    public long deleteBill(String id) {
        return billRepository.deleteByRefId(id);
    }

    public Float[] calculateTotalServiceCharges(List<VetService> vetServices, int totalDuration) {
        Float totalServiceCharges = 0.0f;
        Float additionalCharges = 0.0f;

        for (VetService service : vetServices) {
            Float serviceCharge = 0.0f;

            if (service.getChargeByTime()) {
                int includedDuration = Math.round(totalDuration);
                if (totalDuration > includedDuration) {
                    // Calculate additional charge
                    int additionalDuration = totalDuration - includedDuration;
                    Float additionalChargePerUnit = service.getAmount();
                    additionalCharges += additionalDuration * additionalChargePerUnit;

                    // Calculate charge for included duration
                    serviceCharge = service.getAmount() * includedDuration;
                } else {
                    // Calculate charge for full duration
                    serviceCharge = service.getAmount() * totalDuration;
                }
            } else {
                // If service is not charged by time, add fixed amount
                serviceCharge = service.getAmount();
            }

            totalServiceCharges += serviceCharge;
        }

        return new Float[]{totalServiceCharges, additionalCharges};
    }

    public Float calculateTotalItemCost(List<String> itemsList, List<PetStore> petStoreItems) {
        float totalCost = 0.0f;

        if (itemsList != null && petStoreItems != null) {
            for (String itemId : itemsList) {
                for (PetStore petStoreItem : petStoreItems) {
                    if (petStoreItem.getRefId().equals(itemId)) {
                        totalCost += petStoreItem.getUnitprice();
                        break; // Once found, break the inner loop
                    }
                }
            }
        }

        return totalCost;
    }

    public Float calculateTotalBill(List<String> itemsList, List<PetStore> petStoreItems, List<VetService> vetServices, int totalDuration)
    {
        Float[] totalServiceCharges = calculateTotalServiceCharges(vetServices, totalDuration);
        Float totalItemCost = calculateTotalItemCost(itemsList, petStoreItems);

        Float totalBill = totalServiceCharges[0] + totalServiceCharges[1] + totalItemCost;
        return totalBill;
    }
}
