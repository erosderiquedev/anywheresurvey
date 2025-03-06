/**
 * Author: Eros Derique Utui
 * User:erosderiquedev
 * Date:06/03/2025
 * Time:10:37
 */
package com.erosutuidev.anywheresurvey.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;

}
