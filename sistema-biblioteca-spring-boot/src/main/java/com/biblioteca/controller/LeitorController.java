package com.biblioteca.controller;

import com.biblioteca.dto.LeitorRequest;
import com.biblioteca.entity.Leitor;
import com.biblioteca.service.LeitorService;
import com.biblioteca.service.EmprestimoService;
import com.biblioteca.entity.Emprestimo;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leitores")
public class LeitorController {
    private final LeitorService leitorService;
    private final EmprestimoService emprestimoService;

    public LeitorController(LeitorService leitorService, EmprestimoService emprestimoService) {
        this.leitorService = leitorService;
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public Leitor criar(@Valid @RequestBody LeitorRequest request) {
        return leitorService.criar(request);
    }

    @GetMapping
    public List<Leitor> listar() { return leitorService.listar(); }

    @GetMapping("/{id}")
    public Leitor buscar(@PathVariable Long id) { return leitorService.buscar(id); }

    @GetMapping("/{id}/emprestimos")
    public List<Emprestimo> emprestimos(@PathVariable Long id) {
        return emprestimoService.listarPorLeitor(id);
    }
}
