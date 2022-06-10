package com.example.herewegoagain.hardware.Repository;

import com.example.herewegoagain.hardware.Hardware;
import com.example.herewegoagain.hardware.Type;
import org.springframework.context.annotation.Primary;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class HardwareJDBCRepository implements HardwareRepository{
    private static final String SELECT_ALL = "SELECT * FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public HardwareJDBCRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("index");
    }


    @Override
    public Set<Hardware> findAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return  Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE index = ?", this::mapRowToHardware, code)
            );
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setIndex(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String index) {
        jdbc.update("DELETE FROM hardware WHERE index = ?", index);
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        int ex = jdbc.update("UPDATE hardware SET " +
                        "name = ?, " +
                        "price = ?, " +
                        "type = ?, " +
                        "in_stock = ?, " +
                        "WHERE index = ?",
                updatedHardware.getName(),
                updatedHardware.getPrice(),
                updatedHardware.getType(),
                updatedHardware.getInStock(),
                updatedHardware.getIndex()
        );

        if(ex > 0){
            return Optional.of(updatedHardware);
        }else {
            return Optional.empty();
        }
    }


    private Hardware mapRowToHardware(ResultSet resultSet, int i) throws SQLException {
        return new Hardware(
                resultSet.getString("index"),
                resultSet.getString("name"),
                resultSet.getDouble("price"),
                resultSet.getString("type"),
                resultSet.getInt("in_stock")
        );
    }

    private String saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();

        values.put("index", hardware.getIndex());
        values.put("name", hardware.getName());
        values.put("price", hardware.getPrice());
        values.put("type", hardware.getType());
        values.put("in_stock", hardware.getInStock());

        return inserter.executeAndReturnKey(values).toString();
    }

}
