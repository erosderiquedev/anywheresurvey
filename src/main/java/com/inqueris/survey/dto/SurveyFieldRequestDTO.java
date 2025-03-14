/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:16:11
 */
package com.inqueris.survey.dto;

import com.inqueris.survey.FieldType;
import lombok.Data;
import java.util.List;

@Data
public class SurveyFieldRequestDTO {
    private String fieldName;
    private String label;
    private FieldType type;
    private List<String> options; // Para armazenar opções caso seja um campo SELECT
}
