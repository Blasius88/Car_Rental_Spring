package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.entity.MRoles;
import com.Car_Rental_Spring.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
@Transactional
public class RoleDaoImpl implements RoleDao {

    private static final String ROLE_ID = "id";
    private static final String ROLE_NAME = "name";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private MRoles getMRoleRowMapper(ResultSet resultSet, int i) throws SQLException {
        MRoles mRoles = new MRoles();
        mRoles.setId_roles(resultSet.getLong(ROLE_ID));
        mRoles.setName_roles(resultSet.getString(ROLE_NAME));
        return mRoles;
    }

    @Override
    public List<MRoles> findAll() {
        final String findAllQuery = "select * from m_roles";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getMRoleRowMapper);
    }

    @Override
    public List<MRoles> getRolesByUserId(int userId) {
        final String getRolesByUserId = "select * from roles where role_user_id = ?";
        return jdbcTemplate.query(getRolesByUserId, new Object[]{userId}, this::getMRoleRowMapper);
    }

    @Override
    public MRoles findById(Long Id) {
        final String findOneQuery = "select * " +
                "from m_roles " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ROLE_ID, Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getMRoleRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long Id) {
        final String delete = "delete " +
                "from m_roles " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ROLE_ID, Id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MRoles save(MRoles entity) {
        final String creatQuery = "INSERT INTO m_roles (name) " +
                "VALUES ( :name);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ROLE_NAME, entity.getName_roles());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdBillId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdBillId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MRoles update(MRoles entity) {
        final String creatQuery = "UPDATE m_roles " +
                "set name = :name, " +
                "WHERE id = :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(ROLE_ID, entity.getId_roles());
        parameterSource.addValue(ROLE_NAME, entity.getName_roles());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(Long.valueOf(entity.getId_roles()));
    }

    @Override
    public MRoles findRole(String str) {
        final String findOneQuery = "select * " +
                "from m_roles " +
                "where name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ROLE_NAME, str);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getMRoleRowMapper);
    }
}