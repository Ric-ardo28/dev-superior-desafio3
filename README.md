# 🧾 Desafio 03 - CRUD de Clientes

Projeto desenvolvido como parte da **Formação Desenvolvedor Moderno** da [DevSuperior](https://devsuperior.com.br), no módulo de **API REST com Spring Boot**.

Este projeto consiste em uma API REST para gerenciamento de clientes, implementando todas as operações CRUD com validações, tratamento de exceções e banco de dados em memória (H2).

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Lombok
- Bean Validation
- Maven
- H2 Database
- JPA

---

## 📦 Funcionalidades da API

- ✅ Busca paginada de clientes
- ✅ Busca de cliente por ID
- ✅ Inserção de cliente com validação
- ✅ Atualização de cliente com validação
- ✅ Deleção de cliente
- ✅ Tratamento de exceções (404 e 422)
- ✅ Seed com 10 clientes pré-cadastrados

---

## 🧪 Regras de validação

- `name`: não pode ser vazio
- `birthDate`: não pode ser data futura (`@PastOrPresent`)

---

## ⚠️ Tratamento de exceções

| Tipo de erro         | Código HTTP | Descrição                              |
|----------------------|-------------|----------------------------------------|
| Cliente não encontrado | 404         | Quando o ID não existe (GET, PUT, DELETE) |
| Dados inválidos      | 422         | Quando há erro de validação nos campos |

---

## 🗃️ Estrutura da entidade `Client`

```java
private Long id;
private String name;
private String cpf;
private Double income;
private LocalDate birthDate;
private Integer children;
```

---

## 💻 Como executar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. Navegue até a pasta do projeto e abra com sua IDE (IntelliJ ou VS Code).

3. Execute o projeto com Spring Boot.

4. Acesse o banco de dados H2:
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User: `sa`
   - Password: *(em branco)*

---

## 📫 Testando a API com o Postman

### 🔍 Buscar cliente por ID
```
GET /clients/1
```

### 📄 Buscar clientes com paginação
```
GET /clients?page=0&size=6&sort=name
```

### ➕ Inserir novo cliente
```json
POST /clients
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}
```

### ✏️ Atualizar cliente
```json
PUT /clients/1
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 7000.0,
  "birthDate": "1994-07-20",
  "children": 3
}
```

### ❌ Deletar cliente
```
DELETE /clients/1
```

---

## ✅ Checklist de requisitos

- [x] Busca por ID retorna cliente existente
- [x] Busca por ID retorna 404 para cliente inexistente
- [x] Busca paginada funciona corretamente
- [x] Inserção com dados válidos
- [x] Inserção com dados inválidos retorna 422
- [x] Atualização com dados válidos
- [x] Atualização de cliente inexistente retorna 404
- [x] Atualização com dados inválidos retorna 422
- [x] Deleção de cliente existente
- [x] Deleção de cliente inexistente retorna 404

---

## 👨‍💻 Autor

**Ricardo Rodrigues Santana**  
[LinkedIn](https://www.linkedin.com/in/ricardo-r-santana/)  
Desenvolvedor Back-end | Java | Spring Boot | Git | APIs RESTful | PostgreSQL | SQL | Análise e Desenvolvimento de Sistemas
