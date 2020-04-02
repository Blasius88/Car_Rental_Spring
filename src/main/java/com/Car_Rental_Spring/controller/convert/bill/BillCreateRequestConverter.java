package com.Car_Rental_Spring.controller.convert.bill;

import com.Car_Rental_Spring.controller.requests.bill.BillCreateRequest;
import com.Car_Rental_Spring.domain.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillCreateRequestConverter extends BillRequestConverter<BillCreateRequest, Bill> {

    @Override
    public Bill convert(BillCreateRequest request) {
        Bill bill = new Bill();
        return doConvert(bill, request);
    }
}
