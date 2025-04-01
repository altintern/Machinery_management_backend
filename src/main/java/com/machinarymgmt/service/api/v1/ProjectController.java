package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.machinarymgmt.service.api.utils.Constants.PROJECT_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(PROJECT_URL)
public class ProjectController {

    private final ProjectService projectService;
    private final ApiResponseBuilder responseBuilder;

    // Get all projects with pagination
    @GetMapping
    public ResponseEntity<BaseApiResponse<Project>> getAllProjects(Pageable pageable) {
        Page<Project> projects = projectService.findAll(pageable);
        return ResponseEntity.ok(responseBuilder.buildPagedResponse(projects));
    }

    // Get a project by ID
    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Project>> getProjectById(@PathVariable Long id) {
        return projectService.findById(id)
                .map(project -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(project)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Project not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    // Create a new project
    @PostMapping
    public ResponseEntity<BaseApiResponse<Project>> createProject(@RequestBody Project project) {
        if (projectService.existsByName(project.getName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project already exists with name: " + project.getName(),
                    ErrorType.DUPLICATE));
        }
        Project savedProject = projectService.save(project);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(savedProject, "Project created successfully"));
    }

    // Update an existing project
    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Project>> updateProject(@PathVariable Long id, @RequestBody Project project) {
        if (!projectService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        project.setId(id);
        Project updatedProject = projectService.save(project);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(updatedProject, "Project updated successfully"));
    }

    // Delete a project by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteProject(@PathVariable Long id) {
        if (!projectService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Project not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        projectService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Project deleted successfully"));
    }
}
