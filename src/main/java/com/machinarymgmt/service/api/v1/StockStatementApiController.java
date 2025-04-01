package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.data.model.StockStatement;
import com.machinarymgmt.service.api.dto.StockStatementDto;
import com.machinarymgmt.service.api.mapper.StockStatementMapper;
import com.machinarymgmt.service.api.service.EquipmentService;
import com.machinarymgmt.service.api.service.ItemService;
import com.machinarymgmt.service.api.service.ProjectService;
import com.machinarymgmt.service.api.service.StockStatementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + "/stock-statements")
public class StockStatementApiController {

    private final StockStatementService stockStatementService;
    private final ProjectService projectService;
    private final ItemService itemService;
    private final EquipmentService equipmentService;
    private final StockStatementMapper stockStatementMapper;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<List<StockStatementDto>>> getAllStockStatements(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            Pageable pageable) {
        Page<StockStatement> statementsPage = stockStatementService.findAll(pageable);
        List<StockStatementDto> statementDtos = statementsPage.getContent().stream()
                .map(stockStatementMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(statementDtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<StockStatementDto>> getStockStatementById(@PathVariable Long id) {
        return stockStatementService.findById(id)
                .map(statement -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(stockStatementMapper.toDto(statement))))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Stock statement not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @GetMapping("/month-year")
    public ResponseEntity<BaseApiResponse<List<StockStatementDto>>> getStockStatementsByMonthAndYear(
            @RequestParam Integer month,
            @RequestParam Integer year) {
        List<StockStatement> statements = stockStatementService.findByMonthAndYear(month, year);
        List<StockStatementDto> statementDtos = statements.stream()
                .map(stockStatementMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(statementDtos));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<StockStatementDto>> createStockStatement(@Valid @RequestBody StockStatementDto statementDto) {
        Optional<Project> projectOpt = projectService.findById(statementDto.getProjectId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + statementDto.getProjectId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Item> itemOpt = itemService.findById(statementDto.getItemId());
        if (itemOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Item not found with id: " + statementDto.getItemId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Equipment> equipmentOpt = equipmentService.findById(statementDto.getEquipmentId());
        if (equipmentOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + statementDto.getEquipmentId(),
                    ErrorType.NOT_FOUND));
        }

        StockStatement statement = stockStatementMapper.fromDtoWithReferences(
                statementDto,
                projectOpt.get(),
                itemOpt.get(),
                equipmentOpt.get());

        StockStatement savedStatement = stockStatementService.save(statement);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                stockStatementMapper.toDto(savedStatement),
                "Stock statement created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<StockStatementDto>> updateStockStatement(
            @PathVariable Long id,
            @Valid @RequestBody StockStatementDto statementDto) {
        if (!stockStatementService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Stock statement not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }

        Optional<Project> projectOpt = projectService.findById(statementDto.getProjectId());
        if (projectOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + statementDto.getProjectId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Item> itemOpt = itemService.findById(statementDto.getItemId());
        if (itemOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Item not found with id: " + statementDto.getItemId(),
                    ErrorType.NOT_FOUND));
        }

        Optional<Equipment> equipmentOpt = equipmentService.findById(statementDto.getEquipmentId());
        if (equipmentOpt.isEmpty()) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Equipment not found with id: " + statementDto.getEquipmentId(),
                    ErrorType.NOT_FOUND));
        }

        StockStatement existingStatement = stockStatementService.findById(id).get();
        stockStatementMapper.updateEntityFromDto(statementDto, existingStatement);
        existingStatement.setProject(projectOpt.get());
        existingStatement.setItem(itemOpt.get());
        existingStatement.setEquipment(equipmentOpt.get());

        StockStatement updatedStatement = stockStatementService.save(existingStatement);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                stockStatementMapper.toDto(updatedStatement),
                "Stock statement updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteStockStatement(@PathVariable Long id) {
        if (!stockStatementService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Stock statement not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        stockStatementService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Stock statement deleted successfully"));
    }
}

