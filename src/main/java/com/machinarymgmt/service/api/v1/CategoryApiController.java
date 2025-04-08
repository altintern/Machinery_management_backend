//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.EquipmentCategory;
//import com.machinarymgmt.service.api.dto.EquipmentCategoryDto;
//import com.machinarymgmt.service.api.mapper.EquipmentCategoryMapper;
//import com.machinarymgmt.service.api.service.EquipmentCategoryService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//import static com.machinarymgmt.service.api.utils.Constants.CATEGORY_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(CATEGORY_URL)
//public class CategoryApiController{
//
//    private final EquipmentCategoryService categoryService;
//    private final EquipmentCategoryMapper categoryMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<EquipmentCategoryDto>>> getAllCategories() {
//        List<EquipmentCategoryDto> categories = categoryService.findAllDto();
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(categories));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<EquipmentCategoryDto>> getCategoryById(@PathVariable Long id) {
//        return categoryService.findDtoById(id)
//                .map(category -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(category)))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Category not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<EquipmentCategoryDto>> createCategory(@Valid @RequestBody EquipmentCategoryDto categoryDto) {
//        if (categoryService.existsByName(categoryDto.getCategoryName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Category already exists with name: " + categoryDto.getCategoryName(),
//                    ErrorType.DUPLICATE));
//        }
//
//        EquipmentCategory category = categoryMapper.toEntity(categoryDto);
//        EquipmentCategory savedCategory = categoryService.save(category);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                categoryMapper.toDto(savedCategory),
//                "Category created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<EquipmentCategoryDto>> updateCategory(
//            @PathVariable Long id,
//            @Valid @RequestBody EquipmentCategoryDto categoryDto) {
//        if (!categoryService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Category not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        EquipmentCategory existingCategory = categoryService.findById(id).get();
//        if (!existingCategory.getName().equals(categoryDto.getCategoryName()) &&
//                categoryService.existsByName(categoryDto.getCategoryName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Category already exists with name: " + categoryDto.getCategoryName(),
//                    ErrorType.DUPLICATE));
//        }
//
//        existingCategory.setName(categoryDto.getCategoryName());
//        EquipmentCategory updatedCategory = categoryService.save(existingCategory);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                categoryMapper.toDto(updatedCategory),
//                "Category updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteCategory(@PathVariable Long id) {
//        if (!categoryService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Category not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        categoryService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Category deleted successfully"));
//    }
//}
//
