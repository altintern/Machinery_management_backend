//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.StatusEntity;
//import com.machinarymgmt.service.api.dto.StatusEntityDto;
//import com.machinarymgmt.service.api.mapper.StatusEntityMapper;
//import com.machinarymgmt.service.api.service.StatusEntityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.List;
//
//import static com.machinarymgmt.service.api.utils.Constants.INCIDENT_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(INCIDENT_URL + "/statuses")
//public class StatusApiController {
//
//    private final StatusEntityService statusEntityService;
//    private final StatusEntityMapper statusEntityMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<StatusEntityDto>>> getAllStatuses() {
//        List<StatusEntityDto> statuses = statusEntityService.findAllDto();
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(statuses));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<StatusEntityDto>> getStatusById(@PathVariable Long id) {
//        return statusEntityService.findDtoById(id)
//                .map(status -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(status)))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Status not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<StatusEntityDto>> createStatus(@Valid @RequestBody StatusEntityDto statusDto) {
//        if (statusEntityService.existsByName(statusDto.getStatusName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Status already exists with name: " + statusDto.getStatusName(),
//                    ErrorType.DUPLICATE));
//        }
//
//        StatusEntity status = statusEntityMapper.toEntity(statusDto);
//        StatusEntity savedStatus = statusEntityService.save(status);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                statusEntityMapper.toDto(savedStatus),
//                "Status created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<StatusEntityDto>> updateStatus(
//            @PathVariable Long id,
//            @Valid @RequestBody StatusEntityDto statusDto) {
//        if (!statusEntityService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Status not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        StatusEntity existingStatus = statusEntityService.findById(id).get();
//        if (!existingStatus.getName().equals(statusDto.getStatusName()) &&
//                statusEntityService.existsByName(statusDto.getStatusName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Status already exists with name: " + statusDto.getStatusName(),
//                    ErrorType.DUPLICATE));
//        }
//
//        statusEntityMapper.updateEntityFromDto(statusDto, existingStatus);
//        StatusEntity updatedStatus = statusEntityService.save(existingStatus);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                statusEntityMapper.toDto(updatedStatus),
//                "Status updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteStatus(@PathVariable Long id) {
//        if (!statusEntityService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Status not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        statusEntityService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Status deleted successfully"));
//    }
//}
//
