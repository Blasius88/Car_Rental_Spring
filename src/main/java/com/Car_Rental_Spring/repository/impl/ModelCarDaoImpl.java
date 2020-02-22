package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.Car_Model;
import com.Car_Rental_Spring.repository.ModelCarDao;
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
public class ModelCarDaoImpl implements ModelCarDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID_MODEL_CAR = "id";
    private static final String MODEL_CAR_NAME = "modul_name";
    private static final String MODEL_CAR_ENGINE_CAPACITY = "engine_capacity";
    private static final String MODEL_CAR_DATA = "data";
    private static final String MODEL_CAR_VIN = "vin";
    private static final String MODEL_CAR_ID_COLOR = "id_color";
    private static final String MODEL_CAR_ID_CAR = "id_car";


    private Car_Model getModelCarRowMapper(ResultSet resultSet, int i) throws SQLException {
        Car_Model car_model = new Car_Model();
        car_model.setId_model(resultSet.getLong(ID_MODEL_CAR));
        car_model.setName_model(resultSet.getString(MODEL_CAR_NAME));
        car_model.setEngine_capacity(resultSet.getInt(MODEL_CAR_ENGINE_CAPACITY));
        car_model.setDate(resultSet.getInt(MODEL_CAR_DATA));
        car_model.setVin(resultSet.getString(MODEL_CAR_VIN));
        car_model.setId_color(resultSet.getLong(MODEL_CAR_ID_COLOR));
        car_model.setId_car(resultSet.getLong(MODEL_CAR_ID_CAR));

        return car_model;
    }

    @Override
    public List findAll() {
        final String findAllQuery = "select * from modul_car";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getModelCarRowMapper);
    }

    @Override
    public Car_Model findById(Long Id) {
        final String findOneQuery = "select * " +
                "from modul_car " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID_MODEL_CAR, Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getModelCarRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long Id) {
        final String delete = "delete " +
                "from model_car " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID_MODEL_CAR, Id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car_Model save(Car_Model entity) {
        final String creatQuery = "INSERT INTO model_car (modul_name, engine_capacity, data, vin, id_color, id_car) " +
                "VALUES ( :name_model, :engine_capacity, :date, :vin, :id_color, :id_car);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Parameters(entity, parameterSource);
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdModelCarId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdModelCarId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Car_Model update(Car_Model entity) {
        final String creatQuery = "UPDATE model_car " +
                "set modul_name =:model_name, " +
                "engine_capacity =:engine_capacity, " +
                "data =:date " +
                "vin =: vin, " +
                "id_color =:id_color, " +
                "id_car =:id_car " +
                "WHERE id = : Id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ID_MODEL_CAR, entity.getId_model());
        Parameters(entity, parameterSource);
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getId_color());
    }

    private void Parameters(Car_Model entity, MapSqlParameterSource parameterSource) {
        parameterSource.addValue(MODEL_CAR_NAME, entity.getName_model());
        parameterSource.addValue(MODEL_CAR_ENGINE_CAPACITY, entity.getEngine_capacity());
        parameterSource.addValue(MODEL_CAR_DATA, entity.getDate());
        parameterSource.addValue(MODEL_CAR_VIN, entity.getVin());
        parameterSource.addValue(MODEL_CAR_ID_COLOR, entity.getId_color());
        parameterSource.addValue(MODEL_CAR_ID_CAR, entity.getId_car());
    }

    @Override
    public Car_Model findModelName(String name) {
        try {
            final String query = "SELECT * " +
                    "FROM model_car " +
                    "WHERE modul_name = :name";
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
            mapSqlParameterSource.addValue("name", name);
            return namedParameterJdbcTemplate.queryForObject(query, mapSqlParameterSource, this::getModelCarRowMapper);
        } catch (Exception ex) {
            System.out.println("Данной модели не найдено");
            return null;
        }
    }
}
