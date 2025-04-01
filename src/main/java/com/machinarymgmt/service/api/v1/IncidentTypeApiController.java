package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.api.dto.IncidentTypeDto;
import com.machinarymgmt.service.api.mapper.IncidentTypeMapper;
import com.machinarymgmt.service.api.service.IncidentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static com.machinarymgmt.service.api.utils.Constants.INCIDENT_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(INCIDENT_URL + "/types")
public class IncidentTypeApiController {

    private final IncidentTypeService incidentTypeService;
    private final IncidentTypeMapper incidentTypeMapper;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<List<IncidentTypeDto>>> getAllIncidentTypes() {
        List<IncidentTypeDto> types = incidentTypeService.findAllDto();
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(types));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<IncidentTypeDto>> getIncidentTypeById(@PathVariable Long id) {
        return incidentTypeService.findDtoById(id)
                .map(type -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(type)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Incident type not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<IncidentTypeDto>> createIncidentType(@Valid @RequestBody IncidentTypeDto typeDto) {
        if (incidentTypeService.existsByName(typeDto.getTypeName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident type already exists with name: " + typeDto.getTypeName(),
                    ErrorType.DUPLICATE));
        }

        IncidentType type = incidentTypeMapper.toEntity(typeDto);
        IncidentType savedType = incidentTypeService.save(type);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                incidentTypeMapper.toDto(savedType),
                "Incident type created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<IncidentTypeDto>> updateIncidentType(
            @PathVariable Long id,
            @Valid @RequestBody IncidentTypeDto typeDto) {
        if (!incidentTypeService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident type not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }

        IncidentType existingType = incidentTypeService.findById(id).get();
        if (!existingType.getName().equals(typeDto.getTypeName()) &&
                incidentTypeService.existsByName(typeDto.getTypeName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident type already exists with name: " + typeDto.getTypeName(),
                    ErrorType.DUPLICATE));
        }

        incidentTypeMapper.updateEntityFromDto(typeDto, existingType);
        IncidentType updatedType = incidentTypeService.save(existingType);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                incidentTypeMapper.toDto(updatedType),
                "Incident type updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteIncidentType(@PathVariable Long id) {
        if (!incidentTypeService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident type not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        incidentTypeService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Incident type deleted successfully"));
    }
}

