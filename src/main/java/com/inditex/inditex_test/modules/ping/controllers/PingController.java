package com.inditex.inditex_test.modules.ping.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PingController {

  /**
   * @return "pong" String.
   */
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

}
