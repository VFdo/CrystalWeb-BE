package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.dto.request.BillRequest;
import com.groupp.crystalweb.entity.Bill;
import com.groupp.crystalweb.service.BillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {

        this.billService = billService;
    }

    //    save bill
    @PostMapping("/bill")
    public Bill saveBill(@RequestBody BillRequest billRequest){
        Bill bill = billService.saveBill(billRequest);
        return (bill);
    }

    //    update bill
    @PutMapping("bill/{id}")
    public Bill updateBill(@PathVariable String id, @RequestBody BillRequest billRequest){
        Bill bill = billService.update(id, billRequest);
        return bill;
    }

    //    find bill by id
    @GetMapping("/bill/{id}")
    public Bill getBill(@PathVariable String id){
        Bill bill = billService.getBill(id);
        return bill;
    }

    //    get all bills
    @GetMapping("/bill")
    public List<Bill> getBills(){
        List<Bill> bills = billService.getAllBills();
        return bills;
    }


    //    delete bill
    @DeleteMapping("bill/delete/{id}")
    public String deleteBill(@PathVariable String id){
        long deleted = billService.deleteBill(id);
        if(deleted != 0){
            return ("Bill deleted successfully");
        }
        return "Bill not found!";
    }

}
