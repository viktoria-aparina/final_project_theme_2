package org.pm.api.controllers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class BaseController {
  private final String baseUrl = "https://pm.ua";

  protected RequestSpecification requestSpec = new RequestSpecBuilder()
      .setBaseUri(baseUrl)
      .setContentType(ContentType.JSON)
      .build();
}
