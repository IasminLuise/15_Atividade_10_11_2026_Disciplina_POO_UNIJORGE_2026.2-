package com.biblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LeitorRequest(
    @NotBlank String nome,
    @NotBlank @Email String email,
    @NotBlank String numeroCarteirinha
) {}
