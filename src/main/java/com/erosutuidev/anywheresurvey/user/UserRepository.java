/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:30
 */
package com.erosutuidev.anywheresurvey.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {}
