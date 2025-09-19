package edu.nexova.poc.repository;
import edu.nexova.poc.entity.DatabaseEntity;

import java.util.List;


public interface DatabaseRepository {
    Boolean add(DatabaseEntity databaseEntity);

    Boolean update(DatabaseEntity databaseEntity);

    Boolean delete(Long id);

    DatabaseEntity getOne(Long id);

    List<DatabaseEntity> getAll();

    Boolean findById(Long id);
}
