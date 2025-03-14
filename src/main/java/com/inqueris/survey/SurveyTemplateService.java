/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:33
 */
package com.inqueris.survey;

import com.inqueris.survey.dto.SurveyFieldResponseDTO;
import com.inqueris.survey.dto.SurveyTemplateRequestDTO;
import com.inqueris.survey.dto.SurveyTemplateResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyTemplateService {
    private final SurveyTemplateRepository surveyTemplateRepository;
    private final SurveyFieldRepository surveyFieldRepository;
    private final OptionRepository optionRepository;

    @Transactional
    public SurveyTemplateResponseDTO createSurveyTemplate(SurveyTemplateRequestDTO requestDTO) {
        SurveyTemplate surveyTemplate = new SurveyTemplate();
        surveyTemplate.setName(requestDTO.getName());
        surveyTemplate.setDescription(requestDTO.getDescription());
        surveyTemplate = surveyTemplateRepository.save(surveyTemplate);

        SurveyTemplate finalSurveyTemplate = surveyTemplate;
        List<SurveyField> fields = requestDTO.getFields().stream().map(fieldDTO -> {
            SurveyField field = new SurveyField();
            field.setFieldName(fieldDTO.getFieldName());
            field.setLabel(fieldDTO.getLabel());
            field.setType(fieldDTO.getType());
            field.setSurveyTemplate(finalSurveyTemplate);
            surveyFieldRepository.save(field);

            if (fieldDTO.getType() == FieldType.SELECT) {
                List<Option> options = fieldDTO.getOptions().stream().map(optionValue -> {
                    Option option = new Option();
                    option.setValue(optionValue);
                    option.setSurveyField(field);
                    return optionRepository.save(option);
                }).collect(Collectors.toList());
                field.setOptions(options);
            }
            return field;
        }).collect(Collectors.toList());

        surveyTemplate.setFields(fields);
        return convertToDTO(surveyTemplate);
    }

    public SurveyTemplateResponseDTO getSurveyById(Long id) {
        SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Survey not found"));
        return convertToDTO(surveyTemplate);
    }

    private SurveyTemplateResponseDTO convertToDTO(SurveyTemplate surveyTemplate) {
        SurveyTemplateResponseDTO dto = new SurveyTemplateResponseDTO();
        dto.setId(surveyTemplate.getId());
        dto.setName(surveyTemplate.getName());
        dto.setDescription(surveyTemplate.getDescription());

        List<SurveyFieldResponseDTO> fieldDTOs = surveyTemplate.getFields().stream().map(field -> {
            SurveyFieldResponseDTO fieldDTO = new SurveyFieldResponseDTO();
            fieldDTO.setId(field.getId());
            fieldDTO.setFieldName(field.getFieldName());
            fieldDTO.setLabel(field.getLabel());
            fieldDTO.setType(field.getType());
            fieldDTO.setOptions(Optional.ofNullable(field.getOptions())
                    .map(options -> options.stream()
                            .map(Option::getValue)
                            .collect(Collectors.toList()))
                    .orElse(null));

            return fieldDTO;
        }).collect(Collectors.toList());

        dto.setFields(fieldDTOs);
        return dto;
    }

    public List<SurveyTemplateResponseDTO> getAllSurveys() {
        return surveyTemplateRepository.findAll()
                .stream()
                .map(survey -> new SurveyTemplateResponseDTO(
                        survey.getId(),
                        survey.getName(),
                        survey.getDescription(),
                        survey.getCreatedBy().getFirstname(),
                        survey.getFields().stream()
                                .map(field -> new SurveyFieldResponseDTO(
                                        field.getId(),
                                        field.getFieldName(),
                                        field.getLabel(),
                                        field.getType(),
                                        field.getOptions().stream()
                                        .map(option -> option.getValue())
                                        .collect(Collectors.toList())
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

    }
}


