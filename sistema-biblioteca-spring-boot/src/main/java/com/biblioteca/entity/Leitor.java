package com.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "leitores")
public class Leitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @OneToOne(mappedBy = "leitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Carteirinha carteirinha;

    @JsonIgnore
    @OneToMany(mappedBy = "leitor")
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void setCarteirinha(Carteirinha carteirinha) {
        this.carteirinha = carteirinha;
        if (carteirinha != null) carteirinha.setLeitor(this);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Carteirinha getCarteirinha() { return carteirinha; }
    public List<Emprestimo> getEmprestimos() { return emprestimos; }
}
