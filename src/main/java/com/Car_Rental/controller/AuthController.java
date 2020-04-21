package com.Car_Rental.controller;

import com.Car_Rental.config.security.jwt.JwtTokenUtils;
import com.Car_Rental.controller.requests.authentication.AuthenticationRequest;
import com.Car_Rental.controller.requests.user.UserCreateRequest;
import com.Car_Rental.controller.response.AuthResponse;
import com.Car_Rental.service.security.RegistrationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/rest/Auth")
public class AuthController {

  private final JwtTokenUtils jwtTokenUtils;
  private final AuthenticationManager authenticationManager;
  private final RegistrationService registrationService;

  @ApiOperation(value = " LogIn and create Token")
  @PostMapping("/login")
  @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
  public ResponseEntity<AuthResponse> logIn (@ModelAttribute @Valid AuthenticationRequest request){
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()
            )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String authToken = jwtTokenUtils.generateToken(authentication);
    return ResponseEntity.ok(AuthResponse
            .builder()
            .login(request.getLogin())
            .authToken(authToken)
            .build());
  }

  @ApiOperation(value = " LogIn and create Token")
  @PostMapping("/registration")
  @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
  public ResponseEntity<AuthResponse> registration(@ModelAttribute @Valid UserCreateRequest request) {

    registrationService.notConfirmedRegistration(request);

    return ResponseEntity.ok(new AuthResponse());
  }

  @GetMapping("/confirmation")
  @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
  public ResponseEntity<AuthResponse> confirmUserAccount(@RequestParam("auth-token") String confirmationToken) {
    AuthenticationRequest request = registrationService.confirm(confirmationToken);
    return logIn(request);
  }
}
