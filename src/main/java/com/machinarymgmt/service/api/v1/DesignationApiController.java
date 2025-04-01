package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Designation;
import com.machinarymgmt.service.api.dto.DesignationDto;
import com.machinarymgmt.service.api.mapper.DesignationMapper;
import com.machinarymgmt.service.api.service.DesignationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + "/designations")
public class DesignationApiController {

    private final DesignationService designationService;
    private final DesignationMapper designationMapper;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<List<DesignationDto>>> getAllDesignations() {
        List<DesignationDto> designations = designationService.findAllDto();
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(designations));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<DesignationDto>> getDesignationById(@PathVariable Long id) {
        return designationService.findDtoById(id)
                .map(designation -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(designation)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Designation not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<DesignationDto>> createDesignation(@Valid @RequestBody DesignationDto designationDto) {
        if (designationService.existsByName(designationDto.getDesignationName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Designation already exists with name: " + designationDto.getDesignationName(),
                    ErrorType.DUPLICATE));
        }

        Designation designation = designationMapper.toEntity(designationDto);
        Designation savedDesignation = designationService.save(designation);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                designationMapper.toDto(savedDesignation),
                "Designation created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<DesignationDto>> updateDesignation(
            @PathVariable Long id,
            @Valid @RequestBody DesignationDto designationDto) {
        if (!designationService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Designation not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }

        Designation existingDesignation = designationService.findById(id).get();
        if (!existingDesignation.getName().equals(designationDto.getDesignationName()) &&
                designationService.existsByName(designationDto.getDesignationName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Designation already exists with name: " + designationDto.getDesignationName(),
                    ErrorType.DUPLICATE));
        }

        designationMapper.updateEntityFromDto(designationDto, existingDesignation);
        Designation updatedDesignation = designationService.save(existingDesignation);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                designationMapper.toDto(updatedDesignation),
                "Designation updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteDesignation(@PathVariable Long id) {
        if (!designationService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Designation not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        designationService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Designation deleted successfully"));
    }
}

