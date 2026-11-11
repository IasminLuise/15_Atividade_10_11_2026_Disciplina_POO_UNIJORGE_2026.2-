package com.biblioteca.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record EmprestimoRequest(
    @NotNull Long livroId,
    @NotNull LocalDate dataEmprestimo,
    LocalDate dataPrevistaDevolucao
) {}
