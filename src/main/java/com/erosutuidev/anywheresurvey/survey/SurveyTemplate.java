/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:20
 */
package com.erosutuidev.anywheresurvey.survey;

import com.erosutuidev.anywheresurvey.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "surveyTemplate", cascade = CascadeType.ALL)
    private List<SurveyField> fields;

}
