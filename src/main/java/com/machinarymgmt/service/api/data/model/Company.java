package com.machinarymgmt.service.api.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CompanyID")
    private Long id;
    
    @Column(name = "Company_Name", nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "company")
    private List<CompanyProjectEquipment> assignments;
}

