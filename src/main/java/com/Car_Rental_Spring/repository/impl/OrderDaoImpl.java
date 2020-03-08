package com.Car_Rental_Spring.repository.impl;


import com.Car_Rental_Spring.domain.Order;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.OrderDao;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Transactional
public class OrderDaoImpl implements OrderDao {
    private static final String ORDER_ID = "id";
    private static final String ORDER_ID_USER = "id_user";
    private static final String ORDER_ID_CAR = "id_car";
    private static final String ORDER_ID_WORKER = "id_worker";
    private static final String RENTAL_START = "rental_start";
    private static final String RENTAL_END = "rental_end";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Order getOrderRowMapper(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setOrderId(resultSet.getLong(ORDER_ID));
        order.setOrderUserId(resultSet.getLong(ORDER_ID_USER));
        order.setOrderCarId(resultSet.getLong(ORDER_ID_CAR));
        order.setOrderWorkerId(resultSet.getLong(ORDER_ID_WORKER));
        order.setRentalStart(resultSet.getString(RENTAL_START));
        order.setRentalEnd(resultSet.getString(RENTAL_END));

        return order;
    }

    @Override
    public List<Order> findAll() {
        final String findAllQuery = "select * from m_order";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getOrderRowMapper);
    }

    @Override
    public Order findById(Long id) {
        final String findOneQuery = "select * " +
                "from m_order " +
                "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_ID, id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getOrderRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String delete = "delete " +
                "from m_order " +
                "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ORDER_ID, id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order save(Order entity) {
        final String creatQuery = "INSERT INTO m_order (id_user, id_car, id_worker, rental_start, rental_end) " +
                "VALUES (:id_user, :id_car, :id_worker, :rental_start, :rental_end);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Params(entity, parameterSource);
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdUserId);
    }

    @Override
    @Transactional (rollbackFor = Exception.class)
    public Order update(Order entity) {
        final String creatQuery = "UPDATE m_order " +
                "set id_user =:id_user," +
                "id_car =:id_car," +
                "id_worker = :id_worker," +
                "rental_start = :rental_start," +
                "rental_end = :rental_end," +
                "WHERE id = :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ORDER_ID, entity.getOrderId());
        Params(entity, parameterSource);
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getOrderId());
    }

    private void Params(Order entity, MapSqlParameterSource parameterSource) {
        parameterSource.addValue(ORDER_ID_USER, entity.getOrderUserId());
        parameterSource.addValue(ORDER_ID_CAR, entity.getOrderCarId());
        parameterSource.addValue(ORDER_ID_WORKER, entity.getOrderWorkerId());
        parameterSource.addValue(RENTAL_START, entity.getRentalStart());
        parameterSource.addValue(RENTAL_END, entity.getRentalEnd());
    }
}
