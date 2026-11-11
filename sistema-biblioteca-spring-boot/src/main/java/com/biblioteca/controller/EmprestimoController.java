package com.biblioteca.controller;

import com.biblioteca.dto.EmprestimoRequest;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.service.EmprestimoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {
    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) { this.service = service; }

    @PostMapping("/leitor/{leitorId}")
    public Emprestimo criar(@PathVariable Long leitorId, @Valid @RequestBody EmprestimoRequest request) {
        return service.criar(leitorId, request);
    }

    @GetMapping
    public List<Emprestimo> listar() { return service.listarTodos(); }
}
