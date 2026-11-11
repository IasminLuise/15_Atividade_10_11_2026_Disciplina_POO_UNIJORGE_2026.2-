# Sistema de Gerenciamento de Biblioteca

Projeto acadêmico desenvolvido com **Java 17, Spring Boot, Spring Data JPA, Hibernate e MySQL**. O objetivo é demonstrar os relacionamentos `@OneToOne`, `@OneToMany` / `@ManyToOne` e `@ManyToMany`.

## 1. Funcionalidades

- Cadastro e consulta de leitores com carteirinha exclusiva.
- Cadastro e consulta de categorias.
- Cadastro e consulta de livros associados a uma ou várias categorias.
- Registro de empréstimos vinculados a um leitor e a um livro.
- Consulta dos empréstimos de um leitor.

## 2. Relacionamentos JPA implementados

### OneToOne — Leitor e Carteirinha
A entidade `Carteirinha` é a dona do relacionamento e contém a chave estrangeira `leitor_id`, configurada com `unique = true`. Isso impede que um leitor seja associado a mais de uma carteirinha.

### OneToMany / ManyToOne — Leitor e Emprestimo
Um leitor pode ter vários empréstimos. A entidade `Emprestimo` contém a chave estrangeira `leitor_id`, pois é o lado `@ManyToOne` do relacionamento.

### ManyToMany — Livro e Categoria
A entidade `Livro` é a dona do relacionamento. A anotação `@JoinTable` configura a tabela intermediária `livro_categoria`, com as colunas `livro_id` e `categoria_id`.

> Observação: `Emprestimo` também referencia `Livro` por `@ManyToOne`, permitindo identificar qual exemplar foi emprestado.

## 3. Requisitos

- JDK 17 ou superior.
- Maven 3.9 ou superior (ou usar o Maven Wrapper, caso seja adicionado).
- MySQL 8 ou superior.
- VS Code com Extension Pack for Java e Spring Boot Extension Pack, ou IntelliJ IDEA.

## 4. Configuração do banco

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE biblioteca;
```

Abra `src/main/resources/application.properties` e configure usuário e senha. Por padrão:
- usuário: `root`
- senha: vazia

Você também pode definir as variáveis de ambiente:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

O Hibernate está configurado com `ddl-auto=update`, portanto cria/atualiza as tabelas durante a inicialização. Para produção, use migrações (Flyway/Liquibase) e não `update`.

## 5. Executar o projeto

No terminal, dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

Ou compile:

```bash
mvn clean package
```

Depois execute o JAR gerado em `target/`:

```bash
java -jar target/sistema-biblioteca-1.0.0.jar
```

A API ficará disponível em `http://localhost:8080`.

## 6. Testar com Postman

### 6.1 Cadastrar leitor e carteirinha — POST `/api/leitores`

```json
{
  "nome": "Ana Souza",
  "email": "ana@email.com",
  "numeroCarteirinha": "CAR-0001"
}
```

A operação salva o leitor e sua carteirinha em conjunto.

### 6.2 Listar leitores — GET `/api/leitores`

### 6.3 Cadastrar categoria — POST `/api/categorias`

```json
{
  "nome": "Programação",
  "descricao": "Livros sobre linguagens e desenvolvimento de software"
}
```

Crie também outras categorias, por exemplo Tecnologia, Matemática e Literatura.

### 6.4 Cadastrar livro — POST `/api/livros`

Use os IDs das categorias já cadastradas:

```json
{
  "titulo": "Introdução à Programação",
  "autor": "Maria Oliveira",
  "isbn": "9780000000001",
  "categoriaIds": [1, 2]
}
```

O livro será associado às categorias informadas. Para não associar categorias, envie `categoriaIds` como lista vazia ou omita o campo.

### 6.5 Registrar empréstimo — POST `/api/emprestimos/leitor/1`

O número `1` na URL é o ID do leitor. O `livroId` deve corresponder a um livro existente.

```json
{
  "livroId": 1,
  "dataEmprestimo": "2026-11-10",
  "dataPrevistaDevolucao": "2026-11-24"
}
```

### 6.6 Consultar empréstimos de um leitor — GET `/api/leitores/1/emprestimos`

### 6.7 Listar livros, categorias e empréstimos

- `GET /api/livros`
- `GET /api/categorias`
- `GET /api/emprestimos`

## 7. Resumo dos endpoints

| Método | Endpoint | Finalidade |
|---|---|---|
| POST | `/api/leitores` | Cadastrar leitor e carteirinha |
| GET | `/api/leitores` | Listar leitores |
| GET | `/api/leitores/{id}` | Consultar leitor |
| GET | `/api/leitores/{id}/emprestimos` | Consultar empréstimos do leitor |
| POST | `/api/categorias` | Cadastrar categoria |
| GET | `/api/categorias` | Listar categorias |
| GET | `/api/categorias/{id}` | Consultar categoria |
| POST | `/api/livros` | Cadastrar livro e categorias |
| GET | `/api/livros` | Listar livros |
| GET | `/api/livros/{id}` | Consultar livro |
| POST | `/api/emprestimos/leitor/{leitorId}` | Registrar empréstimo |
| GET | `/api/emprestimos` | Listar empréstimos |

## 8. Estrutura de pastas

```text
src/main/java/com/biblioteca/
├── BibliotecaApplication.java
├── controller/
├── dto/
├── entity/
├── repository/
└── service/

src/main/resources/
└── application.properties
```

## 9. Observações acadêmicas

- `@Entity` transforma a classe em uma entidade persistente.
- `@Id` identifica a chave primária.
- `@GeneratedValue` permite gerar o identificador automaticamente.
- `@JoinColumn` configura a coluna de chave estrangeira.
- `mappedBy` identifica o lado inverso do relacionamento bidirecional.
- `cascade = CascadeType.ALL` no relacionamento leitor-carteirinha permite persistir a carteirinha ao salvar o leitor.
- `@JsonIgnore` evita ciclos na serialização JSON dos relacionamentos bidirecionais.

Este projeto é uma base funcional para estudo e demonstração dos relacionamentos solicitados. Antes da entrega, execute a aplicação no seu ambiente, teste os endpoints e ajuste o projeto às exigências específicas do professor.
