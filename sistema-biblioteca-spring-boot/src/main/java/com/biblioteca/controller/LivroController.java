package com.biblioteca.controller;

import com.biblioteca.dto.LivroRequest;
import com.biblioteca.entity.Livro;
import com.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) { this.service = service; }

    @PostMapping
    public Livro criar(@Valid @RequestBody LivroRequest request) { return service.criar(request); }

    @GetMapping
    public List<Livro> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) { return service.buscar(id); }
}
