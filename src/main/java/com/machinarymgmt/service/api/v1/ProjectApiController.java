//package com.machinarymgmt.service.api.v1;
//
//import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
//import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
//import com.machinarymgmt.service.api.config.dto.ErrorType;
//import com.machinarymgmt.service.api.data.model.Project;
//import com.machinarymgmt.service.api.dto.ProjectDto;
//import com.machinarymgmt.service.api.dto.ProjectRequestDto;
//import com.machinarymgmt.service.api.mapper.ProjectMapper;
//import com.machinarymgmt.service.api.service.ProjectService;
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
//import static com.machinarymgmt.service.api.utils.Constants.PROJECTAPI_URL;
//import static com.machinarymgmt.service.api.utils.Constants.PROJECT_URL;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(PROJECTAPI_URL)
//public class ProjectApiController {
//
//    private final ProjectService projectService;
//    private final ProjectMapper projectMapper;
//    private final ApiResponseBuilder responseBuilder;
//
//    @GetMapping
//    public ResponseEntity<BaseApiResponse<List<ProjectDto>>> getAllProjects(
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "10") Integer size,
//            Pageable pageable) {
//        Page<Project> projectPage = projectService.findAll(pageable);
//        List<ProjectDto> projectDtos = projectPage.getContent().stream()
//                .map(projectMapper::toDto)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(projectDtos));
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<ProjectDto>> getProjectById(@PathVariable Long id) {
//        return projectService.findById(id)
//                .map(project -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(projectMapper.toDto(project))))
//                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                        "Project not found with id: " + id,
//                        ErrorType.NOT_FOUND)));
//    }
//
//    @PostMapping
//    public ResponseEntity<BaseApiResponse<ProjectDto>> createProject(@Valid @RequestBody ProjectRequestDto requestDto) {
//        if (projectService.existsByName(requestDto.getProjectName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Project already exists with name: " + requestDto.getProjectName(),
//                    ErrorType.DUPLICATE));
//        }
//
//        Project project = projectMapper.toEntity(requestDto);
//        Project savedProject = projectService.save(project);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                projectMapper.toDto(savedProject),
//                "Project created successfully"));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<ProjectDto>> updateProject(
//            @PathVariable Long id,
//            @Valid @RequestBody ProjectRequestDto requestDto) {
//        Optional<Project> existingProjectOpt = projectService.findById(id);
//        if (existingProjectOpt.isEmpty()) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Project not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//
//        Project existingProject = existingProjectOpt.get();
//        if (!existingProject.getName().equals(requestDto.getProjectName()) &&
//                projectService.existsByName(requestDto.getProjectName())) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Project already exists with name: " + requestDto.getProjectName(),
//                    ErrorType.DUPLICATE));
//        }
//
//        projectMapper.updateEntityFromDto(requestDto, existingProject);
//        Project updatedProject = projectService.save(existingProject);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
//                projectMapper.toDto(updatedProject),
//                "Project updated successfully"));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<BaseApiResponse<Void>> deleteProject(@PathVariable Long id) {
//        if (!projectService.existsById(id)) {
//            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
//                    "Project not found with id: " + id,
//                    ErrorType.NOT_FOUND));
//        }
//        projectService.deleteById(id);
//        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Project deleted successfully"));
//    }
//}
//
