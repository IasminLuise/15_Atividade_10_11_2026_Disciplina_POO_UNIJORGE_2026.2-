package com.biblioteca.service;

import com.biblioteca.dto.LeitorRequest;
import com.biblioteca.entity.Carteirinha;
import com.biblioteca.entity.Leitor;
import com.biblioteca.repository.LeitorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LeitorService {
    private final LeitorRepository repository;

    public LeitorService(LeitorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Leitor criar(LeitorRequest request) {
        Leitor leitor = new Leitor();
        leitor.setNome(request.nome());
        leitor.setEmail(request.email());

        Carteirinha carteirinha = new Carteirinha();
        carteirinha.setNumero(request.numeroCarteirinha());
        leitor.setCarteirinha(carteirinha);

        return repository.save(leitor);
    }

    public List<Leitor> listar() { return repository.findAll(); }

    public Leitor buscar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Leitor não encontrado: " + id));
    }
}
