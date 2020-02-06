package com.Car_Rental_Spring.repositiry.impl;

import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repositiry.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    public static final String USER_ID = "id" ;
    public static final String FIRST_NAME = "firs_name";
    public static final String LAST_NAME ="last_name";
    public static final String LOGIN = "login";
    public static final String PASS = "pass";
    public static final String CREATED = "created";
    public static final String ROLE = "id_role";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String CITY = "city";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private User getUserRowMapper (ResultSet resultSet, int i) throws SQLException{
        User user = new User();
        user.setId(resultSet.getLong(USER_ID));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPass(resultSet.getString(PASS));
        user.setCreated(resultSet.getDate(CREATED));
        user.setId_role(resultSet.getLong(ROLE));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPhone(resultSet.getString(PHONE));
        user.setCity(resultSet.getString(CITY));

        return user;
    }


    @Override
    public List<Long> findOne(List<User> users) {
        return null;
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from m_user";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User findById(Long id) {
        final String findById = "select * from m_user where id = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(findById,params,this::getUserRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from m_user where id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public User save(User entity) {
        final String creatQuery = "INSERT INTO m_user (firstName =:userFirstName, lastName =:userLastName," +
                " login = :userLogin, pass = :userPass, created = :userCreated, id_role =:userId_role, " +
                "email = :userEmail,phone = :userPhone,city = :userCity)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userFirsName", entity.getFirstName());
        parameterSource.addValue("userLastName", entity.getLastName());
        parameterSource.addValue("userLogin", entity.getLogin());
        parameterSource.addValue("userPass", entity.getPass());
        parameterSource.addValue("userCreated", entity.getCreated());
        parameterSource.addValue("userId_role", entity.getId_role());
        parameterSource.addValue("userEmail", entity.getEmail());
        parameterSource.addValue("userPhone", entity.getPhone());
        parameterSource.addValue("userCity", entity.getCity());

        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public User update(User entity) {
        final String creatQuery = "UPDATE user set firstName =:userFirstName, lastName =:userLastName," +
                " login = :userLogin, pass = :userPass, created = :userCreated, id_role =:userId_role, " +
                "email = :userEmail,phone = :userPhone,city = :userCity where id =: userId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("userId", entity.getId());
        parameterSource.addValue("userFirsName", entity.getFirstName());
        parameterSource.addValue("userLastName", entity.getLastName());
        parameterSource.addValue("userLogin", entity.getLogin());
        parameterSource.addValue("userPass", entity.getPass());
        parameterSource.addValue("userCreated", entity.getCreated());
        parameterSource.addValue("userId_role", entity.getId_role());
        parameterSource.addValue("userEmail", entity.getEmail());
        parameterSource.addValue("userPhone", entity.getPhone());
        parameterSource.addValue("userCity", entity.getCity());

        namedParameterJdbcTemplate.update(creatQuery, parameterSource);

        return findById(entity.getId());
    }
}
