package com.magnakod.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.magnakod.dto.LoginRequestModel;
import com.magnakod.entity.User;
import com.magnakod.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping(path="/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    private LoginRequestModel loginRequestModel;
    @Value("${spring.jwt.secret_key}")
    private String jwtSecretKey;

    @PostMapping(path = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody User user){
        String data = null;

        Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
        boolean getUser = userRepository.existsUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (getUser){
            String jwtToken = JWT.create()
                    .withIssuer("EasyServ")
                    .withSubject("EasyServ Token")
                    .withClaim("username", user.getUsername())
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 300000L))
                    .withJWTId(UUID.randomUUID().toString())
                    .sign(algorithm);
            loginRequestModel = new LoginRequestModel(true,jwtToken);
            data = new Gson().toJson(loginRequestModel);

            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        loginRequestModel = new LoginRequestModel(false,null);
        data = new Gson().toJson(loginRequestModel);

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
