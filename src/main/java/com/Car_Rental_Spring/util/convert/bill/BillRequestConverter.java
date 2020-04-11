package com.Car_Rental_Spring.util.convert.bill;

import com.Car_Rental_Spring.entity.Order;
import com.Car_Rental_Spring.exceptions.ArgumentOfMethodNotValidException;
import com.Car_Rental_Spring.exceptions.ConversionException;
import com.Car_Rental_Spring.exceptions.EntityNotFoundException;
import com.Car_Rental_Spring.util.convert.EntityConverter;
import com.Car_Rental_Spring.controller.requests.bill.BillCreateRequest;
import com.Car_Rental_Spring.entity.Bill;

import javax.persistence.NoResultException;

public abstract class BillRequestConverter<S, T> extends EntityConverter<S, T> {

    protected Bill doConvert(Bill bill, BillCreateRequest request) {

        bill.setStatus(request.isStatus());

        return bill;
    }

    Order findOrder(Class<?> sClass, int idOrder) {
        Order order;
        try {
            order = entityManager.createQuery("select o from Order o where o.orderId =:name", Order.class)
                    .setParameter("name", idOrder)
                    .getSingleResult();
        } catch (NumberFormatException e) {
            throw new ConversionException(sClass, Bill.class, idOrder, new ArgumentOfMethodNotValidException(Order.class, idOrder));
        } catch (NoResultException e) {
            throw new ConversionException (sClass, Bill.class, idOrder,
                    new EntityNotFoundException(" name = " + idOrder, Order.class));
        }
        return order;
    }

}
