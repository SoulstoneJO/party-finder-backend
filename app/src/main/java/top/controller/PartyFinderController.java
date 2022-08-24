package top.controller;

import generated.api.PartyFinderApi;
import generated.model.ConsumerProfileResponse;
import generated.model.SignInRequest;
import generated.model.SignInResponse;
import generated.model.SignUpRequest;
import generated.model.SignUpResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import top.service.ConsumerProfileService;
import top.service.SignInService;
import top.service.SignUpService;

/** PartyFinderController. */
@Controller
public class PartyFinderController implements PartyFinderApi {
  @Autowired SignUpService signUpService;
  @Autowired SignInService signInService;
  @Autowired ConsumerProfileService consumerProfileService;

  @Override
  public ResponseEntity<SignUpResponse> signUp(@Valid SignUpRequest signUpRequest) {
    return ResponseEntity.ok(signUpService.doSignUp(signUpRequest));
  }

  @Override
  public ResponseEntity<SignInResponse> signIn(@Valid SignInRequest signInRequest) {
    return ResponseEntity.ok(signInService.doSignIn(signInRequest));
  }

  @Override
  public ResponseEntity<ConsumerProfileResponse> consumerProfile(String token) {
    return ResponseEntity.ok(consumerProfileService.queryProfile(token));
  }
}
