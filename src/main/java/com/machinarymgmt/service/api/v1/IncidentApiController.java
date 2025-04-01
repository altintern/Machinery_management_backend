package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.IncidentReport;
import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.data.model.StatusEntity;
import com.machinarymgmt.service.api.dto.IncidentReportDto;
import com.machinarymgmt.service.api.mapper.IncidentReportMapper;
import com.machinarymgmt.service.api.service.EquipmentService;
import com.machinarymgmt.service.api.service.IncidentReportService;
import com.machinarymgmt.service.api.service.IncidentTypeService;
import com.machinarymgmt.service.api.service.ProjectService;
import com.machinarymgmt.service.api.service.StatusEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.machinarymgmt.service.api.utils.Constants.INCIDENT_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(INCIDENT_URL)
public class IncidentApiController {

    private final IncidentReportService incidentReportService;
    private final EquipmentService equipmentService;
    private final ProjectService projectService;
    private final IncidentTypeService incidentTypeService;
    private final StatusEntityService statusEntityService;
    private final IncidentReportMapper incidentReportMapper;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<List<IncidentReportDto>>> getAllIncidents(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            Pageable pageable) {
        Page<IncidentReport> incidentsPage = incidentReportService.findAll(pageable);
        List<IncidentReportDto> incidentDtos = incidentsPage.getContent().stream()
                .map(incidentReportMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(incidentDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<IncidentReportDto>> getIncidentById(@PathVariable Long id) {
        return incidentReportService.findById(id)
                .map(incident -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(incidentReportMapper.toDto(incident))))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Incident not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @GetMapping("/date-range")
    public ResponseEntity<BaseApiResponse<List<IncidentReportDto>>> getIncidentsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Pageable pageable) {
        Page<IncidentReport> incidentsPage = incidentReportService.findByIncidentDateBetween(startDate, endDate, pageable);
        List<IncidentReportDto> incidentDtos = incidentsPage.getContent().stream()
                .map(incidentReportMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(incidentDtos));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<IncidentReportDto>> createIncident(
            @Valid @RequestBody IncidentReportDto incidentDto) {
        Optional<Equipment> equipmentOpt = equipmentService.findById(incidentDto.getEquipmentId());
        if (equipmentOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + incidentDto.getEquipmentId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Project> projectOpt = projectService.findById(incidentDto.getProjectId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + incidentDto.getProjectId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<IncidentType> typeOpt = incidentTypeService.findById(incidentDto.getTypeId());
        if (typeOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident type not found with id: " + incidentDto.getTypeId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<StatusEntity> statusOpt = statusEntityService.findById(incidentDto.getStatusId());
        if (statusOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Status not found with id: " + incidentDto.getStatusId(),
                    ErrorType.NOT_FOUND));
        }

        IncidentReport incident = incidentReportMapper.fromDtoWithReferences(
                incidentDto,
                equipmentOpt.get(),
                projectOpt.get(),
                typeOpt.get(),
                statusOpt.get());

        IncidentReport savedIncident = incidentReportService.save(incident);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                incidentReportMapper.toDto(savedIncident),
                "Incident created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<IncidentReportDto>> updateIncident(
            @PathVariable Long id,
            @Valid @RequestBody IncidentReportDto incidentDto) {
        if (!incidentReportService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }

        Optional<Equipment> equipmentOpt = equipmentService.findById(incidentDto.getEquipmentId());
        if (equipmentOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + incidentDto.getEquipmentId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Project> projectOpt = projectService.findById(incidentDto.getProjectId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + incidentDto.getProjectId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<IncidentType> typeOpt = incidentTypeService.findById(incidentDto.getTypeId());
        if (typeOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident type not found with id: " + incidentDto.getTypeId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<StatusEntity> statusOpt = statusEntityService.findById(incidentDto.getStatusId());
        if (statusOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Status not found with id: " + incidentDto.getStatusId(),
                    ErrorType.NOT_FOUND));
        }

        IncidentReport existingIncident = incidentReportService.findById(id).get();
        incidentReportMapper.updateEntityFromDto(incidentDto, existingIncident);
        existingIncident.setEquipment(equipmentOpt.get());
        existingIncident.setProject(projectOpt.get());
        existingIncident.setType(typeOpt.get());
        existingIncident.setStatus(statusOpt.get());

        IncidentReport updatedIncident = incidentReportService.save(existingIncident);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                incidentReportMapper.toDto(updatedIncident),
                "Incident updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteIncident(@PathVariable Long id) {
        if (!incidentReportService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Incident not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        incidentReportService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Incident deleted successfully"));
    }
}

