package com.biblioteca.service;

import com.biblioteca.dto.LivroRequest;
import com.biblioteca.entity.Categoria;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.CategoriaRepository;
import com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    private final LivroRepository livroRepository;
    private final CategoriaRepository categoriaRepository;

    public LivroService(LivroRepository livroRepository, CategoriaRepository categoriaRepository) {
        this.livroRepository = livroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Livro criar(LivroRequest request) {
        Livro livro = new Livro();
        livro.setTitulo(request.titulo());
        livro.setAutor(request.autor());
        livro.setIsbn(request.isbn());

        if (request.categoriaIds() != null && !request.categoriaIds().isEmpty()) {
            List<Categoria> categorias = request.categoriaIds().stream()
                .map(id -> categoriaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada: " + id)))
                .collect(Collectors.toList());
            livro.setCategorias(categorias);
        }

        return livroRepository.save(livro);
    }

    public List<Livro> listar() { return livroRepository.findAll(); }

    public Livro buscar(Long id) {
        return livroRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + id));
    }
}
