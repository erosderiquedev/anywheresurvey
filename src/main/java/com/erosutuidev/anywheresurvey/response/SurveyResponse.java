/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:21
 */
package com.erosutuidev.anywheresurvey.response;

import com.erosutuidev.anywheresurvey.participant.Participant;
import com.erosutuidev.anywheresurvey.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Participant participant;

    @ManyToOne
    private User collectedBy;

    private LocalDateTime collectedAt;
}
