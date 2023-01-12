package org.pm.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequest {
  private String formName;
  private String phone;
  private String email;
  private String password;
  private String defaultCurrency;
  private String selectedLanguage;
  private String isPlayerAgree;
}
