package com.machinarymgmt.service.api.v1;

import com.machinarymgmt.service.api.builder.ApiResponseBuilder;
import com.machinarymgmt.service.api.config.dto.BaseApiResponse;
import com.machinarymgmt.service.api.config.dto.ErrorType;
import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.dto.CompanyDto;
import com.machinarymgmt.service.api.mapper.CompanyMapper;
import com.machinarymgmt.service.api.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.machinarymgmt.service.api.utils.Constants.BASE_URL;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL + "/companies")
public class CompanyApiController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final ApiResponseBuilder responseBuilder;

    @GetMapping
    public ResponseEntity<BaseApiResponse<List<CompanyDto>>> getAllCompanies() {
        List<CompanyDto> companies = companyService.findAllDto();
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseApiResponse<CompanyDto>> getCompanyById(@PathVariable Long id) {
        return companyService.findDtoById(id)
                .map(company -> ResponseEntity.ok(responseBuilder.buildSuccessResponse(company)))
                .orElseGet(() -> ResponseEntity.ok(responseBuilder.buildErrorResponse(
                        "Company not found with id: " + id,
                        ErrorType.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<BaseApiResponse<CompanyDto>> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        if (companyService.existsByName(companyDto.getCompanyName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company already exists with name: " + companyDto.getCompanyName(),
                    ErrorType.DUPLICATE));
        }

        Company company = companyMapper.toEntity(companyDto);
        Company savedCompany = companyService.save(company);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                companyMapper.toDto(savedCompany),
                "Company created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseApiResponse<CompanyDto>> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyDto companyDto) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }

        Company existingCompany = companyService.findById(id).get();
        if (!existingCompany.getName().equals(companyDto.getCompanyName()) &&
                companyService.existsByName(companyDto.getCompanyName())) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company already exists with name: " + companyDto.getCompanyName(),
                    ErrorType.DUPLICATE));
        }

        companyMapper.updateEntityFromDto(companyDto, existingCompany);
        Company updatedCompany = companyService.save(existingCompany);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(
                companyMapper.toDto(updatedCompany),
                "Company updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteCompany(@PathVariable Long id) {
        if (!companyService.existsById(id)) {
            return ResponseEntity.ok(responseBuilder.buildErrorResponse(
                    "Company not found with id: " + id,
                    ErrorType.NOT_FOUND));
        }
        companyService.deleteById(id);
        return ResponseEntity.ok(responseBuilder.buildSuccessResponse(null, "Company deleted successfully"));
    }
}

