package com.machinarymgmt.service.api.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "IncidentType")
public class IncidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private Long id;
    
    @Column(name = "Type_Name", nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "type")
    private List<IncidentReport> incidents;
}

