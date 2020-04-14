package com.Car_Rental.util.convert.bill;

import com.Car_Rental.controller.requests.bill.BillCreateRequest;
import com.Car_Rental.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillCreateRequestConverter extends BillRequestConverter<BillCreateRequest, Bill> {

    @Override
    public Bill convert(BillCreateRequest request) {
        Bill bill = new Bill();
        bill.setIdOrder(findOrder(request.getClass(), Math.toIntExact(request.getIdOrder())));
        return doConvert(bill, request);
    }
}
