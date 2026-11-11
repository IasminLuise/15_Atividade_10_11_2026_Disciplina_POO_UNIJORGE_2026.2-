package com.biblioteca.repository;

import com.biblioteca.entity.Carteirinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteirinhaRepository extends JpaRepository<Carteirinha, Long> {
}
