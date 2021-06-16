package com.bollore.stockbolloreback.controller;


import com.bollore.stockbolloreback.models.Personne;
import com.bollore.stockbolloreback.models.Role;
import com.bollore.stockbolloreback.enumeration.UserRoles;
import com.bollore.stockbolloreback.payload.request.LoginRequest;
import com.bollore.stockbolloreback.payload.request.SignupRequest;
import com.bollore.stockbolloreback.payload.response.ErrorMessage;
import com.bollore.stockbolloreback.payload.response.ErrorResponse;
import com.bollore.stockbolloreback.payload.response.JwtResponse;
import com.bollore.stockbolloreback.repository.PersonneRepository;
import com.bollore.stockbolloreback.repository.RoleRepository;
import com.bollore.stockbolloreback.security.jwt.JwtUtils;
import com.bollore.stockbolloreback.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*/")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PersonneRepository personneRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LoginRequest>> constraintViolations = validator.validate(loginRequest);

        if(constraintViolations.size() > 0) {

            ErrorResponse errorResponse = new ErrorResponse();
            ErrorMessage message = new ErrorMessage();
            constraintViolations.forEach( userConstraintViolation -> {

                message.getMessages().put(userConstraintViolation.getPropertyPath().toString(), userConstraintViolation.getMessage());
                errorResponse.setErrors(message);

            });

            return ResponseEntity.ok().body(errorResponse);

        } else {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<SignupRequest>> constraintViolations = validator.validate(signUpRequest);

        if(constraintViolations.size() > 0) {

            ErrorResponse errorResponse = new ErrorResponse();
            ErrorMessage message = new ErrorMessage();
            constraintViolations.forEach( userConstraintViolation -> {

                message.getMessages().put(userConstraintViolation.getPropertyPath().toString(), userConstraintViolation.getMessage());
                errorResponse.setErrors(message);

            });

            return ResponseEntity.ok().body(errorResponse);

        } else {

            if (personneRepository.existsByUsername(signUpRequest.getUsername())) {

                ErrorResponse errorResponse = new ErrorResponse();
                ErrorMessage message = new ErrorMessage();

                message.getMessages().put("username", "Nom d'utilisateur existe déja");
                errorResponse.setErrors(message);

                return ResponseEntity
                        .badRequest()
                        .body(errorResponse);
            }

            if (personneRepository.existsByEmail(signUpRequest.getEmail())) {
                ErrorResponse errorResponse = new ErrorResponse();
                ErrorMessage message = new ErrorMessage();

                message.getMessages().put("email", "Email existe déja");
                errorResponse.setErrors(message);

                return ResponseEntity
                        .badRequest()
                        .body(errorResponse);
            }


            // Create new user's account
            Personne personne = new Personne(
                    signUpRequest.getNom(),
                    signUpRequest.getPrenom(),
                    signUpRequest.getUsername(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));

            Set<String> strRoles = signUpRequest.getRole();
            Set<Role> roles = new HashSet<>();

            if (strRoles == null) {
                Role userRole = roleRepository.findByName(UserRoles.ROLE_PERSONNE)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(adminRole);

                            break;
                        case "demandeur":
                            Role dmdRole = roleRepository.findByName(UserRoles.ROLE_DEMANDEUR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(dmdRole);

                        case "gestionnaire":
                            Role gesRole = roleRepository.findByName(UserRoles.ROLE_GESTIONNAIRE)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(gesRole);

                        case "validateur":
                            Role vldtRole = roleRepository.findByName(UserRoles.ROLE_VALIDATEUR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(vldtRole);

                            break;
                        default:
                            Role userRole = roleRepository.findByName(UserRoles.ROLE_PERSONNE)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            roles.add(userRole);
                    }
                });
            }

            personne.setRoles(roles);
            personneRepository.save(personne);

            return ResponseEntity.ok(personne);

        }
    }
}
