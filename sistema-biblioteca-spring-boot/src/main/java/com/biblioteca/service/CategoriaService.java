package com.biblioteca.service;

import com.biblioteca.dto.CategoriaRequest;
import com.biblioteca.entity.Categoria;
import com.biblioteca.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) { this.repository = repository; }

    public Categoria criar(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNome(request.nome());
        categoria.setDescricao(request.descricao());
        return repository.save(categoria);
    }

    public List<Categoria> listar() { return repository.findAll(); }

    public Categoria buscar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + id));
    }
}
