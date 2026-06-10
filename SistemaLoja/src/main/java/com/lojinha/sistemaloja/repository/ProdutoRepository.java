package com.lojinha.sistemaloja.repository;

import com.lojinha.sistemaloja.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}