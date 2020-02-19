package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.Car_Brand;
import com.Car_Rental_Spring.repository.Car_BrandDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class Car_BrandDaoImpl implements Car_BrandDao {
    public static final String CAR_BRAND_ID = "id";
    public static final String CAR_BRAND_NAME = "name";
    public static final String CAR_BRAND_PRICE_HOUR = "price_hour";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Car_Brand getUserRowMapper(ResultSet resultSet, int i) throws SQLException {
        Car_Brand car_brand = new Car_Brand();
        car_brand.setId(resultSet.getLong(CAR_BRAND_ID));
        car_brand.setName(resultSet.getString(CAR_BRAND_NAME));
        car_brand.setPrice_hour(resultSet.getDouble(CAR_BRAND_PRICE_HOUR));
        return car_brand;
    }

    @Override
    public List<Car_Brand> findAll() {
        final String findAllQuery = "select * from car_brand";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public Car_Brand findById(Long Id) {
        final String findOneQuery = "select * " +
                "from car_brand " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getUserRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long Id) {
        final String delete = "delete " +
                "from car_brand " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", Id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car_Brand save(Car_Brand entity) {
        final String creatQuery = "INSERT INTO car_brand (name, price_hour) " +
                "VALUES (:name, :price_hour );";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", entity.getName());
        parameterSource.addValue("price_hour", entity.getPrice_hour());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdCar_brandId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdCar_brandId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car_Brand update(Car_Brand entity) {
        final String creatQuery = "UPDATE car_brand " +
                "set name =:carName," +
                "price_hour =:priceHour " +
                "WHERE id = : Id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("Id", entity.getId());
        parameterSource.addValue("carName", entity.getName());
        parameterSource.addValue("priceHour", entity.getPrice_hour());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getId());
    }

    @Override
    public List<Car_Brand> findPrice(double sum) {
        try {
            final String query = "SELECT * " +
                    "FROM car_brand " +
                    "WHERE price_hour < :sum";
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("sum", sum);
            return namedParameterJdbcTemplate.query(query, parameterSource, this::getUserRowMapper);
        } catch (Exception ex) {
            System.out.println("Машины дешевле не найдено ");
            return null;
        }
    }
}
