/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:32
 */
package com.inqueris.survey;

import org.springframework.data.jpa.repository.JpaRepository;

interface SurveyFieldRepository extends JpaRepository<SurveyField, Long> {}
