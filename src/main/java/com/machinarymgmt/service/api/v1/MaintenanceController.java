package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.MachineryMaintenanceLog;
import com.machinarymgmt.service.api.service.EquipmentService;
import com.machinarymgmt.service.api.service.MachineryMaintenanceLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.machinarymgmt.service.api.utils.Constants.MAINTENANCE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(MAINTENANCE_URL)
public class MaintenanceController {

    private final MachineryMaintenanceLogService maintenanceLogService;
    private final EquipmentService equipmentService;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<MachineryMaintenanceLog>> getAllMaintenanceLogs(Pageable pageable) {
        Page<MachineryMaintenanceLog> logs = maintenanceLogService.findAll(pageable);
        return ResponseEntity.ok(responseBuilder.buildPagedResponse(logs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<MachineryMaintenanceLog>> getMaintenanceLogById(@PathVariable Long id) {
        return maintenanceLogService.findById(id)
                .map(log -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(log)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Maintenance log not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<BaseApiResponse<List<MachineryMaintenanceLog>>> getMaintenanceLogsByEquipment(@PathVariable Long equipmentId) {
        Optional<Equipment> equipment = equipmentService.findById(equipmentId);
        if (equipment.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + equipmentId,
                    ErrorType.NOT_FOUND));
        }
        List<MachineryMaintenanceLog> logs = maintenanceLogService.findByEquipment(equipment.get());
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(logs));
    }

    @GetMapping("/date-range")
    public ResponseEntity<BaseApiResponse<List<MachineryMaintenanceLog>>> getMaintenanceLogsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<MachineryMaintenanceLog> logs = maintenanceLogService.findByDateBetween(startDate, endDate);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(logs));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<MachineryMaintenanceLog>> createMaintenanceLog(@RequestBody MachineryMaintenanceLog log) {
        MachineryMaintenanceLog savedLog = maintenanceLogService.save(log);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(savedLog, "Maintenance log created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<MachineryMaintenanceLog>> updateMaintenanceLog(@PathVariable Long id, @RequestBody MachineryMaintenanceLog log) {
        if (!maintenanceLogService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Maintenance log not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        log.setId(id);
        MachineryMaintenanceLog updatedLog = maintenanceLogService.save(log);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(updatedLog, "Maintenance log updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteMaintenanceLog(@PathVariable Long id) {
        if (!maintenanceLogService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Maintenance log not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        maintenanceLogService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Maintenance log deleted successfully"));
    }
}

