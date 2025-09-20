package edu.nexova.poc.controller;

import edu.nexova.poc.dto.DatabaseDTO;
import edu.nexova.poc.exception.response.Response;
import edu.nexova.poc.service.DatabaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/database/setting")
public class DatabaseController {

    private final DatabaseService service;

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody DatabaseDTO databaseDTO) {
        if (service.saveDatabaseSetting(databaseDTO)) {
            return ResponseEntity.ok(new Response("Database setting added successfully"));
        }
        return ResponseEntity.status(500).body(new Response("Failed to add database setting"));
    }

    @PutMapping("/update")
    public ResponseEntity update(@Valid @RequestBody DatabaseDTO databaseDTO) {
        if (service.updateDatabaseSetting(databaseDTO)) {
            return ResponseEntity.ok(new Response("Database setting updated successfully !"));
        }
        return ResponseEntity.status(500).body(new Response("Failed to update database setting !"));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Response> remove(@PathVariable Long id) {
        if (service.deleteDatabaseSetting(id)){
            return ResponseEntity.ok(new Response("Setting Deleted Successfully !"));
        }
        return ResponseEntity.status(500).body(new Response("Failed to Delete !"));
    }

    @GetMapping("/get/{id}")
    public List<DatabaseDTO> getOne(@PathVariable Long id) {
        return service.getDatabaseSetting(id);
    }

    @GetMapping("/get-all")
    public List<DatabaseDTO> getAll() {
        return service.getAllDatabaseSettings();
    }
}
