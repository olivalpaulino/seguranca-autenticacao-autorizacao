# 🔐 Spring Security com Java 21 – Autenticação e Autorização com Roles

Este projeto é um exemplo completo de aplicação web Spring Boot com autenticação e autorização usando **Spring Security 6**, **Java 21** e banco de dados **H2** em memória. Ele apresenta como proteger páginas e funcionalidades com base em **roles de usuário** (ex: ADMIN, USER), com uma interface web construída com **Thymeleaf** e controle de acesso através de **Spring MVC**.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot 3.4.5
- Spring Security 6
- Spring Data JPA
- H2 Database (em memória)
- Thymeleaf
- Maven

---

## 🧠 Conceitos abordados

- Autenticação de usuários com `UserDetailsService`
- Autorização baseada em roles (`ROLE_ADMIN`, `ROLE_USER`)
- Codificação de senhas com `BCryptPasswordEncoder`
- Configuração de `SecurityFilterChain`
- Controle de acesso por URL (ex: `/admin/**`, `/user/**`)
- Páginas públicas e restritas
- Página de login personalizada
- Página de erro de acesso negado (`403`)
- Logout via POST
- Interface com Thymeleaf + Spring Security

---

## 📂 Estrutura do projeto
src/
├── config/
│ └── SecurityConfig.java
├── controller/
│ └── AppController.java
├── model/
│ ├── User.java
│ └── Role.java
├── repository/
│ ├── UserRepository.java
│ └── RoleRepository.java
├── service/
│ └── UserDetailsServiceImpl.java
├── SpringSecurityDemoApplication.java
└── resources/
├── templates/
│ ├── home.html
│ ├── login.html
│ ├── user-page.html
│ ├── admin-page.html
│ └── access-denied.html
└── application.properties


---

## ⚙️ Como executar

### Pré-requisitos
- JDK 21
- Maven 3.8+

### Passo a passo

1. Clone o repositório:

   ```bash
   git clone [https://github.com/olivalpaulino/seguranca-autenticacao-autorizacao.git](https://github.com/olivalpaulino/seguranca-autenticacao-autorizacao.git)
   cd seguranca-autenticacao-autorizacao
2. Acesse a pasta do projeto com Intellij
3. Espere baixar as dependências com Maven
4. Execute o Projeto
5. Acesse: localhost:8080
6. Login: usuário: user e senha: 1234
7. Login2: usuário: admin e senha: 1234
8. Logue com o usuário user e tente acessar a rota localhost:8080/admin
9. Deslogue e logue com a conta de admin e tente acessar a mesma rota.

## Observação:
A documentação completa do projeto está dentro do curso Spring Boot na Prática, que pode ser adquirido aqui: [https://pay.kiwify.com.br/XVcZbZy](https://pay.kiwify.com.br/XVcZbZy)
Dentro deste link, você pode adquirir em conjunto o curso de Thymeleaf, para aprender a criar os templates, formulários web, que podem te fazer aprender ainda mais sobre o curso.
Bons estudos.
