package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.data.model.CompanyProjectEquipment;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.CompanyProjectEquipmentDto;
import com.machinarymgmt.service.api.mapper.CompanyProjectEquipmentMapper;
import com.machinarymgmt.service.api.service.CompanyProjectEquipmentService;
import com.machinarymgmt.service.api.service.CompanyService;
import com.machinarymgmt.service.api.service.EquipmentService;
import com.machinarymgmt.service.api.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + "/company-assignments")
public class CompanyProjectEquipmentApiController {

    private final CompanyProjectEquipmentService assignmentService;
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EquipmentService equipmentService;
    private final CompanyProjectEquipmentMapper assignmentMapper;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<List<CompanyProjectEquipmentDto>>> getAllAssignments(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            Pageable pageable) {
        Page<CompanyProjectEquipment> assignmentsPage = assignmentService.findAll(pageable);
        List<CompanyProjectEquipmentDto> assignmentDtos = assignmentsPage.getContent().stream()
                .map(assignmentMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(assignmentDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<CompanyProjectEquipmentDto>> getAssignmentById(@PathVariable Long id) {
        return assignmentService.findById(id)
                .map(assignment -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(assignmentMapper.toDto(assignment))))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Company assignment not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<CompanyProjectEquipmentDto>> createAssignment(@Valid @RequestBody CompanyProjectEquipmentDto assignmentDto) {
        Optional<Company> companyOpt = companyService.findById(assignmentDto.getCompanyId());
        if (companyOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company not found with id: " + assignmentDto.getCompanyId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Project> projectOpt = projectService.findById(assignmentDto.getProjectId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + assignmentDto.getProjectId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Equipment> equipmentOpt = equipmentService.findById(assignmentDto.getEquipmentId());
        if (equipmentOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + assignmentDto.getEquipmentId(),
                    ErrorType.NOT_FOUND));
        }

        CompanyProjectEquipment assignment = assignmentMapper.fromDtoWithReferences(
                assignmentDto,
                companyOpt.get(),
                projectOpt.get(),
                equipmentOpt.get());

        CompanyProjectEquipment savedAssignment = assignmentService.save(assignment);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                assignmentMapper.toDto(savedAssignment),
                "Company assignment created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<CompanyProjectEquipmentDto>> updateAssignment(
            @PathVariable Long id,
            @Valid @RequestBody CompanyProjectEquipmentDto assignmentDto) {
        if (!assignmentService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company assignment not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }

        Optional<Company> companyOpt = companyService.findById(assignmentDto.getCompanyId());
        if (companyOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company not found with id: " + assignmentDto.getCompanyId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Project> projectOpt = projectService.findById(assignmentDto.getProjectId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + assignmentDto.getProjectId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Equipment> equipmentOpt = equipmentService.findById(assignmentDto.getEquipmentId());
        if (equipmentOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + assignmentDto.getEquipmentId(),
                    ErrorType.NOT_FOUND));
        }

        CompanyProjectEquipment existingAssignment = assignmentService.findById(id).get();
        assignmentMapper.updateEntityFromDto(assignmentDto, existingAssignment);
        existingAssignment.setCompany(companyOpt.get());
        existingAssignment.setProject(projectOpt.get());
        existingAssignment.setEquipment(equipmentOpt.get());

        CompanyProjectEquipment updatedAssignment = assignmentService.save(existingAssignment);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                assignmentMapper.toDto(updatedAssignment),
                "Company assignment updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteAssignment(@PathVariable Long id) {
        if (!assignmentService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company assignment not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        assignmentService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Company assignment deleted successfully"));
    }
}

