package com.inqueris.survey;

import com.inqueris.survey.dto.SurveyTemplateRequestDTO;
import com.inqueris.survey.dto.SurveyTemplateResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/surveys")
public class SurveyTemplateController {

    private final SurveyTemplateService surveyTemplateService;

    @PostMapping
    public ResponseEntity<SurveyTemplateResponseDTO> createSurvey(@RequestBody SurveyTemplateRequestDTO requestDTO) {
        SurveyTemplateResponseDTO responseDTO = surveyTemplateService.createSurveyTemplate(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyTemplateResponseDTO> getSurveyById(@PathVariable Long id) {
        SurveyTemplateResponseDTO responseDTO = surveyTemplateService.getSurveyById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SurveyTemplateResponseDTO>> getAllSurveys() {
        List<SurveyTemplateResponseDTO> surveys = surveyTemplateService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }
}
