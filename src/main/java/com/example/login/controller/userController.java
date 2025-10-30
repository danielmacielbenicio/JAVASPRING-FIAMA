package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id ){
        if (userRepo.existsById(id)){
            userRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Excluido com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse ID não existe");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable int id, @RequestBody User novo){
    Optional<User> UserExistente = userRepo.findById(id);

    if (UserExistente.isPresent()){
        User User = UserExistente.get();
        User.setName(novo.getName());
        User.setPassword(novo.getPassword());
        userRepo.save(User);
        return ResponseEntity.ok(User);
    }else {
        return ResponseEntity.notFound().build();
    }
    }



}
