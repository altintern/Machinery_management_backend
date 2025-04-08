//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.Department;
//import com.machinarymgmt.service.api.data.model.Designation;
//import com.machinarymgmt.service.api.data.model.Employee;
//import com.machinarymgmt.service.api.dto.EmployeeDto;
//import com.machinarymgmt.service.api.mapper.EmployeeMapper;
//import com.machinarymgmt.service.api.service.DepartmentService;
//import com.machinarymgmt.service.api.service.DesignationService;
//import com.machinarymgmt.service.api.service.EmployeeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(BASE_URL + "/employees")
//public class EmployeeApiController {
//
//    private final EmployeeService employeeService;
//    private final DepartmentService departmentService;
//    private final DesignationService designationService;
//    private final EmployeeMapper employeeMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<EmployeeDto>>> getAllEmployees(
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "10") Integer size,
//            Pageable pageable) {
//        Page<Employee> employeesPage = employeeService.findAll(pageable);
//        List<EmployeeDto> employeeDtos = employeesPage.getContent().stream()
//                .map(employeeMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(employeeDtos));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<EmployeeDto>> getEmployeeById(@PathVariable Long id) {
//        return employeeService.findById(id)
//                .map(employee -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(employeeMapper.toDto(employee))))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Employee not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<EmployeeDto>> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
//        Optional<Department> departmentOpt = departmentService.findById(employeeDto.getDeptId());
//        if (departmentOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Department not found with id: " + employeeDto.getDeptId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Designation> designationOpt = designationService.findById(employeeDto.getDesignationId());
//        if (designationOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Designation not found with id: " + employeeDto.getDesignationId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Employee employee = employeeMapper.fromDtoWithReferences(
//                employeeDto,
//                designationOpt.get(),
//                departmentOpt.get());
//
//        Employee savedEmployee = employeeService.save(employee);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                employeeMapper.toDto(savedEmployee),
//                "Employee created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<EmployeeDto>> updateEmployee(
//            @PathVariable Long id,
//            @Valid @RequestBody EmployeeDto employeeDto) {
//        if (!employeeService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Employee not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Department> departmentOpt = departmentService.findById(employeeDto.getDeptId());
//        if (departmentOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Department not found with id: " + employeeDto.getDeptId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Designation> designationOpt = designationService.findById(employeeDto.getDesignationId());
//        if (designationOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Designation not found with id: " + employeeDto.getDesignationId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Employee existingEmployee = employeeService.findById(id).get();
//        employeeMapper.updateEntityFromDto(employeeDto, existingEmployee);
//        existingEmployee.setDepartment(departmentOpt.get());
//        existingEmployee.setDesignation(designationOpt.get());
//
//        Employee updatedEmployee = employeeService.save(existingEmployee);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                employeeMapper.toDto(updatedEmployee),
//                "Employee updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
//        if (!employeeService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Employee not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        employeeService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Employee deleted successfully"));
//    }
//}
//
