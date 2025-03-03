/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:35
 */
package com.erosutuidev.anywheresurvey.response;

import org.springframework.data.jpa.repository.JpaRepository;

interface SurveyDataRepository extends JpaRepository<SurveyData, Long> {}