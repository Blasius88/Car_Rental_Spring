package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class Bill {
    private Long id_bill;
    private Long id_order;
    private boolean status;

    public Bill (){}

    public Bill(Long id_bill, Long id_order, boolean status) {
        this.id_bill = id_bill;
        this.id_order = id_order;
        this.status = status;
    }

    public Long getId_bill() {
        return id_bill;
    }

    public void setId_bill(Long id_bill) {
        this.id_bill = id_bill;
    }

    public Long getId_order() {
        return id_order;
    }

    public void setId_order(Long id_order) {
        this.id_order = id_order;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return status == bill.status &&
                Objects.equals(id_bill, bill.id_bill) &&
                Objects.equals(id_order, bill.id_order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_bill, id_order, status);
    }
}