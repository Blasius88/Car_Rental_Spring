package com.Car_Rental.util.convert.bill;

import com.Car_Rental.controller.requests.bill.BillUpdateRequest;
import com.Car_Rental.exceptions.EntityNotFoundException;
import com.Car_Rental.entity.Bill;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class BillChangeRequestConverter extends BillRequestConverter<BillUpdateRequest, Bill> {

    @Override
    public Bill convert(BillUpdateRequest request) {
        Bill bill =
                ofNullable(entityManager.find(
                        Bill.class, request.getBillId()))
                        .orElseThrow(() -> new EntityNotFoundException(
                                Bill.class, request.getBillId()));
        return doConvert(bill, request);
    }
}
