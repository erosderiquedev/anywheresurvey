/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:20
 */
package com.inqueris.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fieldName;
    private String label;

    @Enumerated(EnumType.STRING)
    private FieldType type; // TEXT, NUMBER, SELECT, IMAGE, LOCATION

    @ManyToOne
    private SurveyTemplate surveyTemplate;

    @OneToMany(mappedBy = "surveyField", cascade = CascadeType.ALL)
    private List<Option> options;
}
