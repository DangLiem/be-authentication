package com.hdtcn.authentication.controller;


import com.hdtcn.authentication.security.JwtRequest;
import com.hdtcn.authentication.security.JwtResponse;
import com.hdtcn.authentication.security.JwtTokenUtil;
import com.hdtcn.authentication.security.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManagers;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));

    }
    @GetMapping("/jwt/{token}")
    public Claims getAll(@PathVariable("token") String token) {
        return jwtTokenUtil.getAllClaimsFromToken(token);
    }
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
//        return ResponseEntity.ok(userDetailsService.save(user));
//    }
    @GetMapping("/permissions/{token}")
    public List<String> getPermissions(@PathVariable("token") String token) {
        String username = jwtTokenUtil.getUsernameFromToken(token);
        String username_;
        UserDetails userDetails = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails)principal;
            username_ = ((UserDetails)principal).getUsername();
        } else {
            username_ = principal.toString();
        }
        return null;
    }
    private boolean checkPermission(String permission, Collection<? extends GrantedAuthority> permissions) {
        AtomicBoolean check = new AtomicBoolean(false);
        permissions.forEach(el -> {
            if (permission.equals(el.getAuthority())) {
                check.set(true);
            }
        });
        return check.get();
    }
    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManagers.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

