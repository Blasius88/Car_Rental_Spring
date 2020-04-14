package com.Car_Rental.controller;

import com.Car_Rental.controller.requests.bill.BillCreateRequest;
import com.Car_Rental.controller.requests.bill.BillUpdateRequest;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.entity.Bill;
import com.Car_Rental.repository.springdata.BillRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rest/Bill")
public class BillController {

    private final BillRepository billRepository;

    @Autowired
    @Qualifier(value = "mvcConversionService")
    private ConversionService conversionService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Bill>> getBills() {
        return new ResponseEntity<>(billRepository.findAll(), HttpStatus.OK);
    }
//-------------------------------------------------------------------
    @ApiOperation(value = "Get Bill from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Bill"),
            @ApiResponse(code = 400, message = "Invalid Bill ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Bill was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Bill> getBillById(
            @ApiParam("Bill Path Id") @PathVariable Long id) {
        Bill bill = billRepository
                .findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException(Bill.class, id));
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
//---------------------------------------------------------
    @ApiOperation(value = "Create bill")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful creating"),
            @ApiResponse(code = 400, message = "Invalid bill properties supplied"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Something wrong"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Bill> createBill(@RequestBody @Valid BillCreateRequest request) {
        Bill converterBill = conversionService.convert(request, Bill.class);
        return new ResponseEntity<>(billRepository.saveAndFlush(converterBill), HttpStatus.CREATED);
    }
//---------------------------------------------------------------
    @ApiOperation(value = "Delete Bill from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful deleting bill"),
            @ApiResponse(code = 400, message = "Invalid bill ID supplied"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Bill was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @DeleteMapping("delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteBill(@ApiParam("User Path Id") @PathVariable("id") Long id) {
        billRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
//------------------------------------------------------------
    @ApiOperation(value = "Update bill from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful updating bill"),
            @ApiResponse(code = 400, message = "Invalid bill ID supplied"),
            @ApiResponse(code = 401, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping("update/{id}")
    @Transactional
    public ResponseEntity<Long> updateBillById (@ModelAttribute @Valid BillUpdateRequest request) {
        Bill convertedUser = conversionService.convert(request, Bill.class);
        return new ResponseEntity(billRepository.save(convertedUser), HttpStatus.OK);
    }
}