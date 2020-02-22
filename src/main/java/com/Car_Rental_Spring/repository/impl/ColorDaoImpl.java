package com.Car_Rental_Spring.repository.impl;

import com.Car_Rental_Spring.domain.Color;
import com.Car_Rental_Spring.repository.ColorDao;
import javafx.scene.effect.ColorAdjust;
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
public class ColorDaoImpl implements ColorDao {

    public static final String COLOR_ID = "id";
    public static final String COLOR = "color";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Color getColorRowMapper(ResultSet resultSet, int i) throws SQLException {
        Color color = new Color();
        color.setId_color(resultSet.getLong(COLOR_ID));
        color.setColor(resultSet.getString(COLOR));
        return color;
    }

    @Override
    public List<Color> findAll() {
        final String findAllQuery = "select * from color";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getColorRowMapper);
    }

    @Override
    public Color findById(Long Id) {
        final String findOneQuery = "select * " +
                "from color " +
                "where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(COLOR_ID, Id);
        return namedParameterJdbcTemplate.queryForObject(findOneQuery, params, this::getColorRowMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long Id) {
        final String delete = "delete " +
                "from m_user " +
                "where id = :Id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(COLOR_ID, Id);
        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Color save(Color entity) {
        final String creatQuery = "INSERT INTO color (name, color) " +
                "VALUES ( :color );";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(COLOR, entity.getColor());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource, keyHolder);
        long createdColorId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return findById(createdColorId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Color update(Color entity) {
        final String creatQuery = "UPDATE color " +
                "set color =:color," +
                "WHERE id = : Id;";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(COLOR_ID, entity.getId_color());
        parameterSource.addValue(COLOR, entity.getColor());
        namedParameterJdbcTemplate.update(creatQuery, parameterSource);
        return findById(entity.getId_color());
    }
}
