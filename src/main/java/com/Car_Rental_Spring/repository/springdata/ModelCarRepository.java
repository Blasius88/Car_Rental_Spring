package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.entity.Car_Model;
import com.Car_Rental_Spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ModelCarRepository extends JpaRepository<Car_Model, Long>, CrudRepository<Car_Model, Long> {

    @Query("select cm.id_model from Car_Model cm where cm.id_model =: id")
    Car_Model findByIdModel (Long id);

    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query("update Car_Model cm set cm.imageBytes =:image where cm.id_model = :id")
            int creteCarModelPhoto(Long id, byte[] image);

}
