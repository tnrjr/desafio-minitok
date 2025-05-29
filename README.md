# Desafio Técnico Backend - CRUD de Produtos



## Como Executar o Projeto

### Pré-requisitos
- Java 11 +
- Docker

### Passos
1. Suba a API e o MongoDB no Docker:
```bash
docker-compose up --build
````
Conecte-se ao MongoDB:
```bash
docker exec -it mongodb mongosh
```
Ou, se o comando mongosh não estiver disponível:
```bash
docker exec -it mongodb mongo
```
Agora, no shell do Mongo, digite:
```bash
// Ver bancos disponíveis
show dbs

// Usar o banco criado no seu projeto (exemplo: crudproduto)
use crudproduto

// Ver as coleções (tabelas)
show collections

// Listar todos os documentos da coleção "products"
db.products.find()

// Filtrar por campo (exemplo: nome)
db.products.find({ name: "Produto Teste" })

// Buscar por ID (lembre-se: ID é uma String no formato ObjectId)
db.products.find({ _id: ObjectId("66518ddf401b2a94fb0f3850") })
```


5. Acesse a API no Swagger:
   http://localhost:8080/swagger-ui/index.html

## Rotas da API

### 1. Adicionar Pedido (POST)
- **URL:** ```http://localhost:8081/api/products```
- Exemplo de Parâmetros (JSON):
```json

{
  "name": "Carregador",
  "description": "adicionou produto",
  "price": 150.00,
  "inStock": true
}
```
- **Respostas:**
  - `200 OK`: Produto adicionado com sucesso.
  - `400 Bad Request`: Validação falhou (ex.: produto já existente ou algum parâmetro não foi enviado).

### 2. Buscar todos os Produtos (GET)
- **URL:** ```http://localhost:8081/api/products```
- **Respostas:**
  - `200 OK`: Produto encontrado.

 
### 3. Buscar Produto por ID (GET)
- **URL:** ```http://localhost:8081/api/products/{id}```
- **Respostas:**
  - `200 OK`: Lista de produtos retornada.
  - `404 Not Found`: Produto não encontrado.
 
### 4. Deletar Cliente (DELETE)
- **URL:** ```http://localhost:8081/api/products/{id}```
- **Respostas:**
  - `204 OK`: Lista de produtos retornada.
  - `404 Not Found`: Produto não encontrado.
 
### 5. Atualizar Cliente (PUT)
- **URL:** ```http://localhost:8081/api/products/{id}```
```json

{
  "name": "Carregador",
  "description": "adicionou produto",
  "price": 150.00,
  "inStock": false
}
```

- **Respostas:**
  - `204 OK`: Produto atualizado com sucesso.
  - `400 Bad Request`: Validação falhou.
  - `404 Not Foud`: Produto não encontrado

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
