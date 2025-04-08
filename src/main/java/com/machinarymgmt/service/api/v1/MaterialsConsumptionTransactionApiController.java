//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.Equipment;
//import com.machinarymgmt.service.api.data.model.Item;
//import com.machinarymgmt.service.api.data.model.MaterialsConsumptionTransaction;
//import com.machinarymgmt.service.api.data.model.Project;
//import com.machinarymgmt.service.api.dto.MaterialsConsumptionTransactionDto;
//import com.machinarymgmt.service.api.mapper.MaterialsConsumptionTransactionMapper;
//import com.machinarymgmt.service.api.service.EquipmentService;
//import com.machinarymgmt.service.api.service.ItemService;
//import com.machinarymgmt.service.api.service.MaterialsConsumptionTransactionService;
//import com.machinarymgmt.service.api.service.ProjectService;
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
//@RequestMapping(BASE_URL + "/materials-consumption")
//public class MaterialsConsumptionTransactionApiController {
//
//    private final MaterialsConsumptionTransactionService transactionService;
//    private final ProjectService projectService;
//    private final EquipmentService equipmentService;
//    private final ItemService itemService;
//    private final MaterialsConsumptionTransactionMapper transactionMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<MaterialsConsumptionTransactionDto>>> getAllTransactions(
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "10") Integer size,
//            Pageable pageable) {
//        Page<MaterialsConsumptionTransaction> transactionsPage = transactionService.findAll(pageable);
//        List<MaterialsConsumptionTransactionDto> transactionDtos = transactionsPage.getContent().stream()
//                .map(transactionMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(transactionDtos));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<MaterialsConsumptionTransactionDto>> getTransactionById(@PathVariable Long id) {
//        return transactionService.findById(id)
//                .map(transaction -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(transactionMapper.toDto(transaction))))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Materials consumption transaction not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @GetMapping("/date-range")
//    public ResponseEntity<BaseApiResponse<List<MaterialsConsumptionTransactionDto>>> getTransactionsByDateRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
//            Pageable pageable) {
//        Page<MaterialsConsumptionTransaction> transactionsPage = transactionService.findByIssueDateBetween(startDate, endDate, pageable);
//        List<MaterialsConsumptionTransactionDto> transactionDtos = transactionsPage.getContent().stream()
//                .map(transactionMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(transactionDtos));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<MaterialsConsumptionTransactionDto>> createTransaction(@Valid @RequestBody MaterialsConsumptionTransactionDto transactionDto) {
//        Optional<Project> projectOpt = projectService.findById(transactionDto.getProjectId());
//        if (projectOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Project not found with id: " + transactionDto.getProjectId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Equipment> equipmentOpt = equipmentService.findById(transactionDto.getEquipmentId());
//        if (equipmentOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Equipment not found with id: " + transactionDto.getEquipmentId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Item> itemOpt = itemService.findById(transactionDto.getItemId());
//        if (itemOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Item not found with id: " + transactionDto.getItemId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        MaterialsConsumptionTransaction transaction = transactionMapper.fromDtoWithReferences(
//                transactionDto,
//                projectOpt.get(),
//                equipmentOpt.get(),
//                itemOpt.get());
//
//        MaterialsConsumptionTransaction savedTransaction = transactionService.save(transaction);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                transactionMapper.toDto(savedTransaction),
//                "Materials consumption transaction created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<MaterialsConsumptionTransactionDto>> updateTransaction(
//            @PathVariable Long id,
//            @Valid @RequestBody MaterialsConsumptionTransactionDto transactionDto) {
//        if (!transactionService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Materials consumption transaction not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Project> projectOpt = projectService.findById(transactionDto.getProjectId());
//        if (projectOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Project not found with id: " + transactionDto.getProjectId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Equipment> equipmentOpt = equipmentService.findById(transactionDto.getEquipmentId());
//        if (equipmentOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Equipment not found with id: " + transactionDto.getEquipmentId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Item> itemOpt = itemService.findById(transactionDto.getItemId());
//        if (itemOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Item not found with id: " + transactionDto.getItemId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        MaterialsConsumptionTransaction existingTransaction = transactionService.findById(id).get();
//        transactionMapper.updateEntityFromDto(transactionDto, existingTransaction);
//        existingTransaction.setProject(projectOpt.get());
//        existingTransaction.setEquipment(equipmentOpt.get());
//        existingTransaction.setItem(itemOpt.get());
//
//        MaterialsConsumptionTransaction updatedTransaction = transactionService.save(existingTransaction);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                transactionMapper.toDto(updatedTransaction),
//                "Materials consumption transaction updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteTransaction(@PathVariable Long id) {
//        if (!transactionService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Materials consumption transaction not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        transactionService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Materials consumption transaction deleted successfully"));
//    }
//}
//
