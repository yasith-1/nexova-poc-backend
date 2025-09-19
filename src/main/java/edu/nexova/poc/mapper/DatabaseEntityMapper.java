package edu.nexova.poc.mapper;

import edu.nexova.poc.entity.DatabaseEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseEntityMapper implements RowMapper<DatabaseEntity> {
    @Override
    public DatabaseEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DatabaseEntity(
                rs.getLong("id"),
                rs.getString("databaseName"),
                rs.getString("username"),
                rs.getString("host"),
                rs.getInt("port"),
                rs.getString("password")
        );
    }
}
