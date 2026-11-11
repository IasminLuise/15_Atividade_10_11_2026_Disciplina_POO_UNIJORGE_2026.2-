package com.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record LivroRequest(
    @NotBlank String titulo,
    @NotBlank String autor,
    String isbn,
    List<Long> categoriaIds
) {}
