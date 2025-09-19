package edu.nexova.poc.service.impl;

import edu.nexova.poc.dto.DatabaseDTO;
import edu.nexova.poc.entity.DatabaseEntity;
import edu.nexova.poc.exception.response.Response;
import edu.nexova.poc.exception.settingException.InvalidSettingException;
import edu.nexova.poc.exception.settingException.SettingNotFoundException;
import edu.nexova.poc.repository.DatabaseRepository;
import edu.nexova.poc.service.DatabaseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DatabaseServiceImpl implements DatabaseService {

    private final DatabaseRepository repository;
    private final ModelMapper mapper;

    @Override
    public Boolean saveDatabaseSetting(DatabaseDTO databaseDTO) throws InvalidSettingException {

        Boolean isAdded = repository.add(mapper.map(databaseDTO, DatabaseEntity.class));
        if (isAdded) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean updateDatabaseSetting(DatabaseDTO databaseDTO) throws SettingNotFoundException {

        Boolean isExistSetting = repository.findById(databaseDTO.getId());
        if (isExistSetting) {
            Boolean isUpdated = repository.update(mapper.map(databaseDTO, DatabaseEntity.class));
            return isUpdated;
//            if (isUpdated) {
//                return ResponseEntity.ok(new Response("Setting updated successfully"));
//            } else {
//                return ResponseEntity.ok(new Response("Setting updated failed"));
//            }
        } else {
            throw new SettingNotFoundException("setting not found");
        }
    }

    private Boolean isExistSetting(Long id) {
        // Implement existence check logic here
        return repository.findById(id);
    }

    @Override
    public Boolean deleteDatabaseSetting(Long id) {
        if (isExistSetting(id)) {
            Boolean isDeleted = repository.delete(id);
            return isDeleted;
        } else {
            throw new SettingNotFoundException("setting not found");
        }

    }

    @Override
    public List<DatabaseDTO> getDatabaseSetting(Long id) {
        if (isExistSetting(id)) {
            DatabaseDTO databaseDTO = mapper.map(repository.getOne(id), DatabaseDTO.class);
            return List.of(databaseDTO);
        } else {
            throw new SettingNotFoundException("setting not exist with id: " + id);
        }
    }

    @Override
    public List<DatabaseDTO> getAllDatabaseSettings() {
        List<DatabaseEntity> entityList = repository.getAll();
        return entityList.stream().map(entity -> mapper.map(entity, DatabaseDTO.class)).toList();
    }
}
