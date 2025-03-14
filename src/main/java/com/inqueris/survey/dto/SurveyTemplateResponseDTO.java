/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:16:12
 */
package com.inqueris.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyTemplateResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String createdBy;
    private List<SurveyFieldResponseDTO> fields;
}
