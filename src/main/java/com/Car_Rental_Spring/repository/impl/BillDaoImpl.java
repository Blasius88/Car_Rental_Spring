package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.Bill;
import com.Car_Rental_Spring.repository.BillDao;
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
public class BillDaoImpl implements BillDao {
  private static final String BILL_ID = "id";
  private static final String BILL_ID_ORDER ="id_order";
  private static final String BILL_STATUS ="status";

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private Bill getBillRowMapper (ResultSet resultSet, int i) throws SQLException {
      Bill bill = new Bill();
      bill.setId_bill(resultSet.getLong(BILL_ID));
      bill.setId_order(resultSet.getLong(BILL_ID_ORDER));
      bill.setStatus(resultSet.getBoolean(BILL_STATUS));
      return bill;
  }


    @Override
    public List<Bill> findAll() {
        final String findAllQuery = "select * from bill";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getBillRowMapper);
    }

    @Override
    public Bill findById(Long Id) {
        final String findOneQuery = "select * " +
                "from bill " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(BILL_ID, Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getBillRowMapper);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long Id) {
        final String delete = "delete " +
                "from bill " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(BILL_ID, Id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional (rollbackFor = Exception.class)
    public Bill save(Bill entity) {
        final String creatQuery = "INSERT INTO bill (id_order, status) " +
                "VALUES ( :id_order, :status);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BILL_ID_ORDER, entity.getId_order());
        parameterSource.addValue(BILL_STATUS, entity.isStatus());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdBillId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdBillId);
  }

    @Override
    @Transactional (rollbackFor = Exception.class)
    public Bill update(Bill entity) {
        final String creatQuery = "UPDATE bill " +
                "set id_order=:id_order, " +
                "status = :status" +
                "WHERE id = :Id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(BILL_ID, entity.getId_bill());
        parameterSource.addValue(BILL_ID_ORDER, entity.getId_order());
        parameterSource.addValue(BILL_STATUS, entity.isStatus());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getId_bill());
    }
}
