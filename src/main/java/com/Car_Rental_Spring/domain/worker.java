package com.Car_Rental_Spring.domain;

import java.util.Objects;

public class worker {
    private Long id_worker;
    private Long id_user;
    private double percentage_of_salary;
    private double salary;

    public worker(){
    }

    public worker(Long id_worker, Long id_user, double percentage_of_salary, double salary) {
        this.id_worker = id_worker;
        this.id_user = id_user;
        this.percentage_of_salary = percentage_of_salary;
        this.salary = salary;
    }

    public Long getId_worker() {
        return id_worker;
    }

    public void setId_worker(Long id_worker) {
        this.id_worker = id_worker;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public double getPercentage_of_salary() {
        return percentage_of_salary;
    }

    public void setPercentage_of_salary(double percentage_of_salary) {
        this.percentage_of_salary = percentage_of_salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        worker worker = (worker) o;
        return Double.compare(worker.percentage_of_salary, percentage_of_salary) == 0 &&
                Double.compare(worker.salary, salary) == 0 &&
                Objects.equals(id_worker, worker.id_worker) &&
                Objects.equals(id_user, worker.id_user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_worker, id_user, percentage_of_salary, salary);
    }
}

