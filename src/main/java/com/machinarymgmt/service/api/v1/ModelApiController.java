//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.Make;
//import com.machinarymgmt.service.api.data.model.Model;
//import com.machinarymgmt.service.api.dto.ModelDto;
//import com.machinarymgmt.service.api.mapper.ModelMapper;
//import com.machinarymgmt.service.api.service.MakeService;
//import com.machinarymgmt.service.api.service.ModelService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//import static com.machinarymgmt.service.api.utils.Constants.MODEL_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(MODEL_URL)
//public class ModelApiController {
//
//    private final ModelService modelService;
//    private final MakeService makeService;
//    private final ModelMapper modelMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<ModelDto>>> getAllModels() {
//        List<ModelDto> models = modelService.findAllDto();
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(models));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<ModelDto>> getModelById(@PathVariable Long id) {
//        return modelService.findDtoById(id)
//                .map(model -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(model)))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Model not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @GetMapping("/make/{makeId}")
//    public ResponseEntity<BaseApiResponse<List<ModelDto>>> getModelsByMake(@PathVariable Long makeId) {
//        if (!makeService.existsById(makeId)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Make not found with id: " + makeId,
//                    ErrorType.NOT_FOUND));
//        }
//        List<ModelDto> models = modelService.findDtoByMakeId(makeId);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(models));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<ModelDto>> createModel(@Valid @RequestBody ModelDto modelDto) {
//        Optional<Make> makeOpt = makeService.findById(modelDto.getMakeId());
//        if (makeOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Make not found with id: " + modelDto.getMakeId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Make make = makeOpt.get();
//        if (modelService.existsByNameAndMake(modelDto.getModelName(), make)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Model already exists with name: " + modelDto.getModelName() + " for this make",
//                    ErrorType.DUPLICATE));
//        }
//
//        Model model = modelMapper.fromDtoWithReferences(modelDto, make);
//        Model savedModel = modelService.save(model);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                modelMapper.toDto(savedModel),
//                "Model created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<ModelDto>> updateModel(
//            @PathVariable Long id,
//            @Valid @RequestBody ModelDto modelDto) {
//        if (!modelService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Model not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        Optional<Make> makeOpt = makeService.findById(modelDto.getMakeId());
//        if (makeOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Make not found with id: " + modelDto.getMakeId(),
//                    ErrorType.NOT_FOUND));
//        }
//
//        Model existingModel = modelService.findById(id).get();
//        Make make = makeOpt.get();
//
//        if (!existingModel.getName().equals(modelDto.getModelName()) ||
//                !existingModel.getMake().getId().equals(modelDto.getMakeId())) {
//            if (modelService.existsByNameAndMake(modelDto.getModelName(), make)) {
//                return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Model already exists with name: " + modelDto.getModelName() + " for this make",
//                        ErrorType.DUPLICATE));
//            }
//        }
//
//        modelMapper.updateEntityFromDto(modelDto, existingModel);
//        existingModel.setMake(make);
//        Model updatedModel = modelService.save(existingModel);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                modelMapper.toDto(updatedModel),
//                "Model updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteModel(@PathVariable Long id) {
//        if (!modelService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Model not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        modelService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Model deleted successfully"));
//    }
//}
//
