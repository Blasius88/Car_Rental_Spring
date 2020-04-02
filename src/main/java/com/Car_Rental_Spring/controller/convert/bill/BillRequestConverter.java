package com.Car_Rental_Spring.controller.convert.bill;

import com.Car_Rental_Spring.controller.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.bill.BillCreateRequest;
import com.Car_Rental_Spring.domain.Bill;

public abstract class BillRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Bill doConvert (Bill bill, BillCreateRequest request){

        bill.setId_order(request.getId_order());
        bill.setStatus(request.isStatus());

        return bill;
    }
}
