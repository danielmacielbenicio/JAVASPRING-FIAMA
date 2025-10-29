package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class userController {
    @Autowired
    UserRepo userRepo;

    @PostMapping(value = "/user/cadastro")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        User usuario = new User(user.getName(), user.getEmail(), user.getPassword());
        userRepo.save(user);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping(value = "/user/listar")
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(userRepo.findAll());
    }
    @PostMapping(value = "/user/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User findUser = userRepo.findByEmail(user.getEmail());
        if (findUser == null) {
            return ResponseEntity.ok("Usuario não encontrado");
        }else {
            if (findUser.getPassword().equals(user.getPassword())){
                return ResponseEntity.ok("logado com sucesso");
            }else {
                return ResponseEntity.ok("senha incorreta");
            }
        }
    }


    @GetMapping(value = "{id}")
    public Optional<User> usuarioPorId(@PathVariable int id){
        return userRepo.findById(id);
    }



}
