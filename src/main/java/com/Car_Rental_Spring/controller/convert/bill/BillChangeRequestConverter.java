package com.Car_Rental_Spring.controller.convert.bill;

import com.Car_Rental_Spring.controller.requests.bill.BillUpdateRequest;
import com.Car_Rental_Spring.domain.Bill;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
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
