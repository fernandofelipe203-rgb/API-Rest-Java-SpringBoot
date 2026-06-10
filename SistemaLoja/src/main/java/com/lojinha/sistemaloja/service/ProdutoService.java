package com.lojinha.sistemaloja.service;
import com.lojinha.sistemaloja.exception.ProdutoNaoEncontradoException;

import com.lojinha.sistemaloja.model.Produto;
import com.lojinha.sistemaloja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com id: " + id));
    }

    public Produto atualizar(Long id, Produto produtoAtualizado) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com id: " + id));

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());

        return repository.save(produto);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
