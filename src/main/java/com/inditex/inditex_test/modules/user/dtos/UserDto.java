package com.inditex.inditex_test.modules.user.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
  public long id;
  public String nickname;

  @JsonProperty("country_id")
  public String countryId;

  @JsonProperty("site_id")
  public String siteId;
}
