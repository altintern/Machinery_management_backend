//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.Employee;
//import com.machinarymgmt.service.api.data.model.OvertimeReport;
//import com.machinarymgmt.service.api.dto.OvertimeReportDto;
//import com.machinarymgmt.service.api.mapper.OvertimeReportMapper;
//import com.machinarymgmt.service.api.service.EmployeeService;
//import com.machinarymgmt.service.api.service.OvertimeReportService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(BASE_URL + "/overtime-reports")
//public class OvertimeReportApiController {
//
//    private final OvertimeReportService overtimeReportService;
//    private final EmployeeService employeeService;
//    private final OvertimeReportMapper overtimeReportMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<OvertimeReportDto>>> getAllOvertimeReports(
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "10") Integer size,
//            Pageable pageable) {
//        Page<OvertimeReport> reportsPage = overtimeReportService.findAll(pageable);
//        List<OvertimeReportDto> reportDtos = reportsPage.getContent().stream()
//                .map(overtimeReportMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(reportDtos));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<OvertimeReportDto>> getOvertimeReportById(@PathVariable Long id) {
//        return overtimeReportService.findById(id)
//                .map(report -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(overtimeReportMapper.toDto(report))))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Overtime report not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @GetMapping("/date-range")
//    public ResponseEntity<BaseApiResponse<List<OvertimeReportDto>>> getOvertimeReportsByDateRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//            Pageable pageable) {
//        Page<OvertimeReport> reportsPage = overtimeReportService.findByDateBetween(startDate, endDate, pageable);
//        List<OvertimeReportDto> reportDtos = reportsPage.getContent().stream()
//                .map(overtimeReportMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(reportDtos));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<OvertimeReportDto>> createOvertimeReport(@Valid @RequestBody OvertimeReportDto reportDto) {
//        Optional<Employee> employeeOpt = employeeService.findById(reportDto.getEmployeeId());
//        if (employeeOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Employee not found with id: " + reportDto.getEmployeeId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        OvertimeReport report = overtimeReportMapper.fromDtoWithReferences(
//                reportDto,
//                employeeOpt.get());
//
//        OvertimeReport savedReport = overtimeReportService.save(report);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                overtimeReportMapper.toDto(savedReport),
//                "Overtime report created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<OvertimeReportDto>> updateOvertimeReport(
//            @PathVariable Long id,
//            @Valid @RequestBody OvertimeReportDto reportDto) {
//        if (!overtimeReportService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Overtime report not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Employee> employeeOpt = employeeService.findById(reportDto.getEmployeeId());
//        if (employeeOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Employee not found with id: " + reportDto.getEmployeeId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        OvertimeReport existingReport = overtimeReportService.findById(id).get();
//        overtimeReportMapper.updateEntityFromDto(reportDto, existingReport);
//        existingReport.setEmployee(employeeOpt.get());
//
//        OvertimeReport updatedReport = overtimeReportService.save(existingReport);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                overtimeReportMapper.toDto(updatedReport),
//                "Overtime report updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteOvertimeReport(@PathVariable Long id) {
//        if (!overtimeReportService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Overtime report not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        overtimeReportService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Overtime report deleted successfully"));
//    }
//}
//
