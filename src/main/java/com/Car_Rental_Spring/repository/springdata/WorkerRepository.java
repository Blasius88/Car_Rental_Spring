package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.entity.WorkerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerUser, Long>, CrudRepository<WorkerUser, Long> {

    @Query("select w.id_worker from WorkerUser w where w.id_worker = :id")
    WorkerUser findByIdWorker(Long id);

}
