package com.example.tasca_avaluacio.controller;

import com.example.tasca_avaluacio.dto.ConflictDTO;
import com.example.tasca_avaluacio.dto.CreateConflictDTO;
import com.example.tasca_avaluacio.service.ConflictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conflicts")
public class ConflictController {

    private final ConflictService conflictService;

    public ConflictController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    // Da todos los conflictos
    @GetMapping
    public List<ConflictDTO> getAll(@RequestParam(required = false) String status) {
        List<ConflictDTO> conflicts = conflictService.getAllConflicts();

        if (conflicts == null){
            return List.of();
        }

        if (status != null) {
            return conflicts.stream()
                    .filter(c -> c.getStatus() != null &&
                            c.getStatus().equalsIgnoreCase(status))
                    .toList();
        }
        return conflicts;
    }

    // Regresa un conflicto específico por su ID
    @GetMapping("/{id}")
    public ConflictDTO getById(@PathVariable Long id) {
        return conflictService.getConflictById(id);
    }

    // Crea un nuevo conflicto usando DTO de creación
    @PostMapping
    public ConflictDTO create(@RequestBody CreateConflictDTO dto) {
        return conflictService.createConflict(dto);
    }

    // Actualiza un conflicto existente por ID usando DTO de creación
    @PutMapping("/{id}")
    public ConflictDTO update(@PathVariable Long id, @RequestBody CreateConflictDTO dto) {
        return conflictService.updateConflict(id, dto);
    }

    // Elimina un conflicto por su ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        conflictService.deleteConflict(id);
    }
}
