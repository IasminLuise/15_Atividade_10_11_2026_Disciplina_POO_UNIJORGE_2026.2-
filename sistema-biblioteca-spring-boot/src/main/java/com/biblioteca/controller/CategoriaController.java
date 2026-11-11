package com.biblioteca.controller;

import com.biblioteca.dto.CategoriaRequest;
import com.biblioteca.entity.Categoria;
import com.biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) { this.service = service; }

    @PostMapping
    public Categoria criar(@Valid @RequestBody CategoriaRequest request) { return service.criar(request); }

    @GetMapping
    public List<Categoria> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Categoria buscar(@PathVariable Long id) { return service.buscar(id); }
}
