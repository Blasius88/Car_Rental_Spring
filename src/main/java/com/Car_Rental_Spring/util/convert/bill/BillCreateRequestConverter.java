package com.Car_Rental_Spring.util.convert.bill;

import com.Car_Rental_Spring.controller.requests.bill.BillCreateRequest;
import com.Car_Rental_Spring.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillCreateRequestConverter extends BillRequestConverter<BillCreateRequest, Bill> {

    @Override
    public Bill convert(BillCreateRequest request) {
        Bill bill = new Bill();
        bill.setId_order(findOrder(request.getClass(), Math.toIntExact(request.getId_order())));
        return doConvert(bill, request);
    }
}
