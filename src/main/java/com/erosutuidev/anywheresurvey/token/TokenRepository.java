/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:14:39
 */
package com.erosutuidev.anywheresurvey.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
            SELECT t FROM Token t inner join User u on u.id = t.user.id
            where u.id = :userId and (t.expired = false or t.revoked = false)
            """)
    List<Token> findAllByValidTokenByUser(Long userId);

    Optional<Token> findByToken(String token);
}
