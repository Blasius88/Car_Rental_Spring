package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.WorkerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerUser, Long>, CrudRepository<WorkerUser, Long> {

    @Query("select w.idWorker from WorkerUser w where w.idWorker = :id")
    WorkerUser findByIdWorker(Long id);

}
