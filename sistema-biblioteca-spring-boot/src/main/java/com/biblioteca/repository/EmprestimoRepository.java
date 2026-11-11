package com.biblioteca.repository;

import com.biblioteca.entity.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByLeitorId(Long leitorId);
}
