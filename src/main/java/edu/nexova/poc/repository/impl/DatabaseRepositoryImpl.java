package edu.nexova.poc.repository.impl;

import edu.nexova.poc.entity.DatabaseEntity;
import edu.nexova.poc.mapper.DatabaseEntityMapper;
import edu.nexova.poc.repository.DatabaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class DatabaseRepositoryImpl implements DatabaseRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Boolean add(DatabaseEntity entity) {
        String query = "INSERT INTO `database_setting` (`databaseName`,`username`,`host`,`port`,`password`) VALUES (?,?,?,?,?)";
        return 0 < jdbcTemplate.update(query,
                entity.getDatabaseName(),
                entity.getUsername(),
                entity.getHost(),
                entity.getPort(),
                entity.getPassword());
    }

    @Override
    public Boolean update(DatabaseEntity entity) {
        String query = "UPDATE `database_setting` SET `databaseName`=?,`username` = ?, `host` = ?, `port` = ?, `password` = ? WHERE `id` = ?";
        return 0 < jdbcTemplate.update(query,
                entity.getDatabaseName(),
                entity.getUsername(),
                entity.getHost(),
                entity.getPort(),
                entity.getPassword(),
                entity.getId());
    }

    @Override
    public Boolean delete(Long id) {
        String query = "DELETE FROM `database_setting` WHERE id = ?";
        return 0 < jdbcTemplate.update(query, id);
    }

    @Override
    public DatabaseEntity getOne(Long id) {
        String sql = "SELECT * FROM `database_setting` WHERE id = ?";
        DatabaseEntity databaseEntity = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                new BeanPropertyRowMapper<>(DatabaseEntity.class));

        return databaseEntity;
    }

    @Override
    public List<DatabaseEntity> getAll() {
        String sql = "SELECT * FROM `database_setting`";
        return jdbcTemplate.query(sql, new DatabaseEntityMapper());
    }

    @Override
    public Boolean findById(Long id) {
        String sql = "SELECT * FROM `database_setting` WHERE id = ?";

//        check if record exists with the given id , if exists return true else false
        try {
            DatabaseEntity databaseEntity = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    new BeanPropertyRowMapper<>(DatabaseEntity.class));
            return databaseEntity != null;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
