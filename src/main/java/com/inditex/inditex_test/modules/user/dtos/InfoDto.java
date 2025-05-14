package com.inditex.inditex_test.modules.user.dtos;

import java.util.List;
import lombok.Builder;

@Builder
public class InfoDto {

  public UserDto user;
  public List<CustomerDto> customer;
}
