package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.controller.requests.bill.BillCreateRequest;
import com.Car_Rental_Spring.domain.Bill;
import com.Car_Rental_Spring.repository.BillDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/Bill")
@RequiredArgsConstructor
public class BillController {

    private final BillDao billDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Bill>> getBills() {
        return new ResponseEntity<>(billDao.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Bill from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Bill"),
            @ApiResponse(code = 400, message = "Invalid Bill ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Bill was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bill> getBillById(@ApiParam("Bill Path Id") @PathVariable Long id) {
        Bill bill = billDao.findById(id);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }


    @ApiOperation(value = "Get Bill from server by Bill")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Bill"),
            @ApiResponse(code = 400, message = "Invalid Bill supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Bill was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/searchName", method = RequestMethod.GET)
    public ResponseEntity<Bill> getBillName(String str) {
        Bill bill = billDao.findBill(str);
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Bill> createBill(@RequestBody @Valid BillCreateRequest request) {
        Bill bill = new Bill();
        data(request, bill);

        Bill savedBill = billDao.save(bill);

        return new ResponseEntity<>(savedBill, HttpStatus.OK);
    }

    private void data(@RequestBody BillCreateRequest request, Bill bill) {
        bill.setId_order(request.getId_order());
        bill.setStatus(request.isStatus());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteBill(@PathVariable("id") Long BillId) {
        billDao.delete(BillId);
        return new ResponseEntity<>(BillId, HttpStatus.OK);
    }
}