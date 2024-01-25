package com.groupp.crystalweb.controller;

import com.groupp.crystalweb.common.Tuple;
import com.groupp.crystalweb.dto.request.BillRequest;
import com.groupp.crystalweb.dto.request.BillRequest;
import com.groupp.crystalweb.dto.response.ApiResponse;
import com.groupp.crystalweb.dto.response.PageInfo;
import com.groupp.crystalweb.entity.Bill;
import com.groupp.crystalweb.entity.Bill;
import com.groupp.crystalweb.service.BillService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    //    save bill
    @PostMapping("bill")
    public ResponseEntity<ApiResponse> saveBill(@Valid @RequestBody BillRequest billRequest){
        Bill savedBill =  billService.saveBill(billRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                savedBill
        );
        return ResponseEntity.ok(response);
    }

    //    update bill
    @PutMapping("bill/{id}")
    public ResponseEntity<ApiResponse> updateBill(@PathVariable String id, @RequestBody BillRequest billRequest){
        Bill updatedBill = billService.updateBill(id, billRequest);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                updatedBill
        );
        return ResponseEntity.ok(response);
    }

    //    find bill by id
    @GetMapping("/bill/{id}")
    public ResponseEntity<ApiResponse> findBill(@PathVariable String id){
        Bill existingBill = billService.getBill(id);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                existingBill
        );
        return ResponseEntity.ok(response);
    }

    //    get all bills
    @GetMapping("/bill")
    public ResponseEntity<ApiResponse> getBills(@RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "10") int pageSize){
        Tuple<Object, Object> allBills = billService.getAllBills(pageNumber, pageSize);
        ApiResponse response = new ApiResponse(
                200,
                "Success",
                allBills.first(),
                (PageInfo) allBills.second()
        );
        return ResponseEntity.ok(response);
    }


    //    delete bill
    @DeleteMapping("bill/delete/{id}")
    public ResponseEntity<ApiResponse> deleteBill(@PathVariable String id){
        long deleted = billService.deleteBill(id);
        ApiResponse response = new ApiResponse();
        response.setStatus(200);
        response.setMessage("Success");
        if(deleted !=0){
            response.setPayload("Bill deleted successfully");
        } else {
            response.setPayload("Bill not found");
        }
        return ResponseEntity.ok(response);
    }

}
