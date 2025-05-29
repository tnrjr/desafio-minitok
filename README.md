# Desafio Técnico Backend - CRUD de Produtos

## Tecnologias
- Java 21
- Spring Boot 3.x
- MongoDB (Docker)
- Swagger UI
- JUnit e Mockito

## Como Executar o Projeto

### Pré-requisitos
- Java 21
- Docker

### Passos
1. Suba o MongoDB no Docker:
   docker run -d -p 27017:27017 --name mongodb mongo

2. Clone o projeto:
   git clone https://github.com/TN-Junior/desafio-minitok.git
   cd desafio-minitok

3. Execute o projeto:
   mvn clean install
   mvn spring-boot:run

4. Acesse a API:
   http://localhost:8080/swagger-ui/index.html

## Estrutura do Projeto
- `controller/`
- `service/`
- `repository/`
- `model/`
- `dto/`
- `config/`

## Testes
- Execute `mvn test`

## Tempo Estimado
- Desenvolvimento: ~6 horas
- Documentação: ~1 hora
- Testes: ~2 horas

## Desafios e Soluções
- Configuração do Docker para MongoDB
- Configuração Swagger para documentação interativa
- Organização em camadas seguindo boas práticas
