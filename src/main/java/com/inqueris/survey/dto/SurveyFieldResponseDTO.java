/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:16:12
 */
package com.inqueris.survey.dto;

import com.inqueris.survey.FieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyFieldResponseDTO {
    private Long id;
    private String fieldName;
    private String label;
    private FieldType type;
    private List<String> options;
}
