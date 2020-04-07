package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.UserDao;
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
       // user.setIdRole(resultSet.getLong(ROLE));
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
        params.addValue(USER_ID, Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getUserRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        final String delete = "delete " +
                "from m_user " +
                "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(USER_ID, id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User entity) {
        final String creatQuery = "INSERT INTO m_user (first_name, last_name, login, pass, created, id_role, email,  phone, city) " +
                "VALUES (:first_name, :last_name, :login, :pass, :created, :id_role, :email, :phone, :city);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        Params(entity, parameterSource);
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdUserId);
    }

    private void Params(User entity, MapSqlParameterSource parameterSource) {
        parameterSource.addValue(FIRST_NAME, entity.getFirstName());
        parameterSource.addValue(LAST_NAME, entity.getLastName());
        parameterSource.addValue(LOGIN, entity.getUserLogin());
        parameterSource.addValue(PASS, entity.getUserPass());
        parameterSource.addValue(CREATED, entity.getUserCreated());
       // parameterSource.addValue(ROLE, entity.getIdRole());
        parameterSource.addValue(EMAIL, entity.getUserEmail());
        parameterSource.addValue(PHONE, entity.getUserPhone());
        parameterSource.addValue(CITY, entity.getUserCity());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User entity) {
        final String creatQuery = "UPDATE m_user " +
                "set first_name =:first_name," +
                "last_name =:last_name," +
                "login = :login," +
                "pass = :pass," +
                "created = :created," +
                "id_role =:id_role," +
                "email = :email," +
                "phone = :phone," +
                "city = :city " +
                "WHERE id = :id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(USER_ID, entity.getUserId());
        Params(entity, parameterSource);
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getUserId());
    }

    @Override
    public List<User> findCityUser(String city) {
        final String findQuery = "SELECT * " +
                "FROM m_user " +
                "WHERE city = :city ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(CITY, city);
        return namedParameterJdbcTemplate.query(findQuery, params, this::getUserRowMapper);
    }

    @Override
    public User findByLogin(String login) {
        final String findById = "select * " +
                "from m_user " +
                "where login = :login";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public User findLoginAndPass(String first_name) {
        final String findQuery = "SELECT * " +
                "FROM m_user " +
                "WHERE first_name = :first_name ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(FIRST_NAME, first_name);
        return namedParameterJdbcTemplate.queryForObject(findQuery, params, this::getUserRowMapper);
    }

    @Override
    public List<User> search(String str, Integer limit, Integer offset) {
        final String searchQuery = "SELECT * " +
                "FROM m_user " +
                "WHERE lower (first_name) " +
                "LIKE lower(:str) or lower(last_name) LIKE lower(:str)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("str", "%" + str +"%");
        return namedParameterJdbcTemplate.query(searchQuery, params, this::getUserRowMapper);
    }
}

