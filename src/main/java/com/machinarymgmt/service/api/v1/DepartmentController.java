//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.Department;
//import com.machinarymgmt.service.api.service.DepartmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(BASE_URL + "/departments")
//public class DepartmentController {
//
//    private final DepartmentService departmentService;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<Department>>> getAllDepartments() {
//        List<Department> departments = departmentService.findAll();
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(departments));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Department>> getDepartmentById(@PathVariable Long id) {
//        return departmentService.findById(id)
//                .map(department -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(department)))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Department not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<Department>> createDepartment(@RequestBody Department department) {
//        if (departmentService.existsByName(department.getName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Department already exists with name: " + department.getName(),
//                    ErrorType.DUPLICATE));
//        }
//        Department savedDepartment = departmentService.save(department);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(savedDepartment, "Department created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Department>> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
//        if (!departmentService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Department not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        department.setId(id);
//        Department updatedDepartment = departmentService.save(department);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(updatedDepartment, "Department updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteDepartment(@PathVariable Long id) {
//        if (!departmentService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Department not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        departmentService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Department deleted successfully"));
//    }
//}
//
