package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.machinarymgmt.service.api.utils.Constants.EQUIPMENT_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(EQUIPMENT_URL)
public class EquipmentController {

    private final EquipmentService equipmentService;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<Equipment>> getAllEquipment(Pageable pageable) {
        Page<Equipment> equipment = equipmentService.findAll(pageable);
        return ResponseEntity.ok(responseBuilder.buildPagedResponse(equipment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Equipment>> getEquipmentById(@PathVariable Long id) {
        return equipmentService.findById(id)
                .map(equipment -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(equipment)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Equipment not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @GetMapping("/asset-code/{assetCode}")
    public ResponseEntity<BaseApiResponse<Equipment>> getEquipmentByAssetCode(@PathVariable String assetCode) {
        return equipmentService.findByAssetCode(assetCode)
                .map(equipment -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(equipment)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Equipment not found with asset code: " + assetCode,
                        ErrorType.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<Equipment>> createEquipment(@RequestBody Equipment equipment) {
        if (equipmentService.existsByAssetCode(equipment.getAssetCode())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment already exists with asset code: " + equipment.getAssetCode(),
                    ErrorType.DUPLICATE));
        }
        Equipment savedEquipment = equipmentService.save(equipment);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(savedEquipment, "Equipment created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Equipment>> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        if (!equipmentService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        equipment.setId(id);
        Equipment updatedEquipment = equipmentService.save(equipment);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(updatedEquipment, "Equipment updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteEquipment(@PathVariable Long id) {
        if (!equipmentService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        equipmentService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Equipment deleted successfully"));
    }
}

