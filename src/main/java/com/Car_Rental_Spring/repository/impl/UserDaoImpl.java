package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.UserDao;
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
public class UserDaoImpl implements UserDao {

    public static final String USER_ID = "id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String LOGIN = "login";
    public static final String PASS = "pass";
    public static final String CREATED = "created";
    public static final String ROLE = "id_role";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String CITY = "city";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private User getUserRowMapper(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong(USER_ID));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setUserLogin(resultSet.getString(LOGIN));
        user.setUserPass(resultSet.getString(PASS));
        user.setUserCreated(resultSet.getDate(CREATED));
        user.setIdRole(resultSet.getLong(ROLE));
        user.setUserEmail(resultSet.getString(EMAIL));
        user.setUserPhone(resultSet.getString(PHONE));
        user.setUserCity(resultSet.getString(CITY));

        return user;
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_user";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User findById(Long Id) {
        final String findOneQuery = "select * " +
                "from m_user " +
                "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getUserRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id_user) {
        final String delete = "delete " +
                "from m_user " +
                "where id = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id_user);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User entity) {
        final String creatQuery = "INSERT INTO m_user (first_name, last_name, login, pass, created, id_role, email,  phone, city) " +
                "VALUES (:first_name, :last_name, :login, :pass, :created, :id_role, :email, :phone, :city);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("first_name", entity.getFirstName());
        parameterSource.addValue("last_name", entity.getLastName());
        parameterSource.addValue("login", entity.getUserLogin());
        parameterSource.addValue("pass", entity.getUserPass());
        parameterSource.addValue("created", entity.getUserCreated());
        parameterSource.addValue("id_role", entity.getIdRole());
        parameterSource.addValue("email", entity.getUserEmail());
        parameterSource.addValue("phone", entity.getUserPhone());
        parameterSource.addValue("city", entity.getUserCity());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdUserId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User entity) {
        final String creatQuery = "UPDATE m_user " +
                "set first_name =:firstName," +
                "last_name =:lastName," +
                "login = :userLogin," +
                "pass = :userPass," +
                "created = :userCreated," +
                "id_role =:idRole," +
                "email = :userEmail," +
                "phone = :userPhone," +
                "city = :userCity " +
                "WHERE id = :userId;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", entity.getUserId());
        parameterSource.addValue("firstName", entity.getFirstName());
        parameterSource.addValue("lastName", entity.getLastName());
        parameterSource.addValue("userLogin", entity.getUserLogin());
        parameterSource.addValue("userPass", entity.getUserPass());
        parameterSource.addValue("userCreated", entity.getUserCreated());
        parameterSource.addValue("idRole", entity.getIdRole());
        parameterSource.addValue("userEmail", entity.getUserEmail());
        parameterSource.addValue("userPhone", entity.getUserPhone());
        parameterSource.addValue("userCity", entity.getUserCity());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getUserId());
    }
}
