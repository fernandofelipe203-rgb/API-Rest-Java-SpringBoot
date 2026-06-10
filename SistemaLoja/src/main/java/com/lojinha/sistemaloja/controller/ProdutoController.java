package com.lojinha.sistemaloja.controller;

import com.lojinha.sistemaloja.model.Produto;
import com.lojinha.sistemaloja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return repository.save(produto);
    }


    @GetMapping
    public List<Produto> listar() {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {

        Produto produto = repository.findById(id).orElse(null);

        if (produto == null) {
            return null;
        }

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());

        return repository.save(produto);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}