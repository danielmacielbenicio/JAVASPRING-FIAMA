package com.example.login.repositories;

import com.example.login.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepo extends JpaRepository<Produto, Integer>{

    Integer id(int id);
}
