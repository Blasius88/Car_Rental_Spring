package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ModelCarRepository extends JpaRepository<CarModel, Long>, CrudRepository<CarModel, Long> {

    @Query("select cm.idModel from CarModel cm where cm.idModel =: id")
    CarModel findByIdModel (Long id);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update CarModel cm set cm.imageBytes =:image where cm.idModel = :id")
            int creteCarModelPhoto(Long id, byte[] image);

}
