/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:16:11
 */
package com.inqueris.survey.dto;

import lombok.Data;
import java.util.List;

@Data
public class SurveyTemplateRequestDTO {
    private String name;
    private String description;
    private Long createdById; // ID do usuário que criou a pesquisa
    private List<SurveyFieldRequestDTO> fields;
}
