package com.biblioteca.service;

import com.biblioteca.dto.EmprestimoRequest;
import com.biblioteca.entity.Emprestimo;
import com.biblioteca.entity.Leitor;
import com.biblioteca.entity.Livro;
import com.biblioteca.repository.EmprestimoRepository;
import com.biblioteca.repository.LeitorRepository;
import com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final LeitorRepository leitorRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository,
                             LeitorRepository leitorRepository,
                             LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.leitorRepository = leitorRepository;
        this.livroRepository = livroRepository;
    }

    public Emprestimo criar(Long leitorId, EmprestimoRequest request) {
        Leitor leitor = leitorRepository.findById(leitorId)
            .orElseThrow(() -> new IllegalArgumentException("Leitor não encontrado: " + leitorId));
        Livro livro = livroRepository.findById(request.livroId())
            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado: " + request.livroId()));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLeitor(leitor);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(request.dataEmprestimo());
        emprestimo.setDataPrevistaDevolucao(request.dataPrevistaDevolucao());
        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> listarPorLeitor(Long leitorId) {
        if (!leitorRepository.existsById(leitorId)) {
            throw new IllegalArgumentException("Leitor não encontrado: " + leitorId);
        }
        return emprestimoRepository.findByLeitorId(leitorId);
    }

    public List<Emprestimo> listarTodos() { return emprestimoRepository.findAll(); }
}
