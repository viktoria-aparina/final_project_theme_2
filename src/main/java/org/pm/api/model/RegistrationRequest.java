package org.pm.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegistrationRequest {
  private String formName;
  private String phone;
  private String email;
  private String password;
  private String defaultCurrency;
  private String selectedLanguage;
  private String isPlayerAgree;
}
