/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:03/03/2025
 * Time:15:22
 */
package com.erosutuidev.anywheresurvey.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // GESTOR, SUPERVISOR, AGENTE
}
