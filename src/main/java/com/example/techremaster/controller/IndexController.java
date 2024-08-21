package com.example.techremaster.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {

  @GetMapping("/whoami")
  String getUserData(@AuthenticationPrincipal OAuth2User user) {
    return "Hello, " + user.getAttribute("name")
        + ", your email is " + user.getAttribute("email");
  }

  @GetMapping("/logged-out")
  String loggedOut() {
    return "You are logged out";
  }
}
