/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:21
 */
package com.erosutuidev.anywheresurvey.response;

import com.erosutuidev.anywheresurvey.survey.SurveyField;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SurveyResponse surveyResponse;

    @ManyToOne
    private SurveyField surveyField;

    private String value;
}
