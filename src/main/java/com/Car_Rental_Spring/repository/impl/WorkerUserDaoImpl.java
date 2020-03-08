package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.WorkerUser;
import com.Car_Rental_Spring.repository.WorkerUserDao;
import lombok.RequiredArgsConstructor;
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
public class WorkerUserDaoImpl implements WorkerUserDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String WORKER_ID="id";
    private static final String WORKER_ID_USER = "id_user";
    private static final String WORKER_PERCENTAGE_OF_SALARY = "percentage_of_salary";
    private static final String WORKER_SALARY = "salary";

    private WorkerUser getWorkerUserRowMapper (ResultSet resultSet, int i) throws SQLException{
        WorkerUser workerUser = new WorkerUser();
        workerUser.setId_worker(resultSet.getLong(WORKER_ID));
        workerUser.setId_user(resultSet.getLong(WORKER_ID_USER));
        workerUser.setPercentage_of_salary(resultSet.getDouble(WORKER_PERCENTAGE_OF_SALARY));
        workerUser.setSalary(resultSet.getDouble(WORKER_SALARY));
        return workerUser;
    }

    @Override
    public List<WorkerUser> findAll() {
        final String findAllQuery = "select * from worker";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getWorkerUserRowMapper);
    }

    @Override
    public WorkerUser findById(Long Id) {
        final String findOneQuery = "select * " +
                "from worker " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(WORKER_ID, Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getWorkerUserRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long Id) {
        final String delete = "delete " +
                "from worker " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(WORKER_ID, Id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkerUser save(WorkerUser entity) {
        final String creatQuery = "INSERT INTO worker (id_user, percentage_of_salary, salary) " +
                "VALUES ( :id_user, :precentage_of_salary, salary);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(WORKER_ID_USER, entity.getId_user());
        parameterSource.addValue(WORKER_PERCENTAGE_OF_SALARY, entity.getPercentage_of_salary());
        parameterSource.addValue(WORKER_SALARY, entity.getSalary());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdBillId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdBillId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WorkerUser update(WorkerUser entity) {
        final String creatQuery = "UPDATE worker " +
                "set id_user =:id_user, " +
                "percentage_of_salary = :percentage_of_salary, " +
                "salary = :salary " +
                "WHERE id = : Id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(WORKER_ID_USER, entity.getId_user());
        parameterSource.addValue(WORKER_PERCENTAGE_OF_SALARY, entity.getPercentage_of_salary());
        parameterSource.addValue(WORKER_SALARY, entity.getSalary());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getId_worker());
    }
}
