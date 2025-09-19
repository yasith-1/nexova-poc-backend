package edu.nexova.poc.service;

import edu.nexova.poc.dto.DatabaseDTO;
import edu.nexova.poc.exception.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DatabaseService {
    Boolean saveDatabaseSetting(DatabaseDTO databaseDTO);

    Boolean updateDatabaseSetting(DatabaseDTO databaseDTO);

    Boolean deleteDatabaseSetting(Long id);

    List<DatabaseDTO> getDatabaseSetting(Long id);

    List<DatabaseDTO> getAllDatabaseSettings();
}
