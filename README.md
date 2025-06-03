# Desafio Técnico Backend - CRUD de Produtos

O projeto utiliza o MongoDB como banco de dados para armazenar as informações de produtos, categorias e fornecedores.

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
### Estrutura de Dados e Relacionamentos no MongoDB

#### Entidades e Relacionamentos
- **Produto (products)**
  - Campos:
    - ``name`` (String) - obrigatório
    - ``description`` (String) - opcional
    - ``price`` (Double) - obrigatório e positivo
    - ``inStock`` (Integer) - quantidade disponível
    - ``category`` (Objeto Category) - referência à categoria do produto
    - ``supplier`` (Objeto Supplier) - referência ao fornecedor do produto

- **Categoria (categories)**
  - Campos:
    - ``id`` (String)
    - ``name`` (String)

- **Fornecedor (suppliers)**
  - Campos:
    - ``id`` (String)
    - ``name`` (String)

**Relacionamentos**
- Um Produto deve estar associado a uma Categoria (categoryId) e um Fornecedor (supplierId).
- No MongoDB, as referências são armazenadas como subdocumentos embutidos no documento Product. Exemplo:
```bash
{
  "_id": ObjectId("66518ddf401b2a94fb0f3850"),
  "name": "Notebook Gamer",
  "description": "Alto desempenho para jogos",
  "price": 4500.00,
  "inStock": 10,
  "category": {
    "_id": "cat-123",
    "name": "Eletrônicos"
  },
  "supplier": {
    "_id": "sup-123",
    "name": "Fornecedor ABC"
  }
}
```
- Para criar um produto, você deve primeiro criar a Categoria e o Fornecedor, e depois usar seus ids no corpo da requisição de POST /api/products.





#### Acesse a API no Swagger:
http://localhost:8080/swagger-ui/index.html

## Endpoints da API

### 1. Exemplo de Requisições para Categoria e Fornecedor(Post)
Criar Categoria (POST)
- **URL:** ```http://localhost:8080/api/categories```
- Exemplo de Parâmetros (JSON):
```json
{
  "name": "Eletrônicos"
}
```
- **Respostas:**
  - `201 OK`: Produto adicionado com sucesso.
  - `400 Bad Request`: Nome não informado.

Criar Fornecedor (POST)
- **URL**: http://localhost:8080/api/suppliers
- **Exemplo de JSON:**
```json
{
  "name": "Fornecedor ABC"
}
```
- **Respostas:**
  - ``201 OK:`` Fornecedor criado com sucesso.
  - ``400 Bad Request:`` Nome não informado.

### 2. Fluxo Completo para Criar Produto
Criar Produto (POST)
- **URL:** http://localhost:8080/api/products
- **Exemplo de JSON (sucesso):**
```json
{
  "name": "Notebook Gamer",
  "description": "Alto desempenho para jogos",
  "price": 4500.00,
  "inStock": 10,
  "categoryId": "cat-123",
  "supplierId": "sup-123"
}
```
- **Resposta 201 Created (Sucesso):**
```bash
{
  "id": "prod-123",
  "name": "Notebook Gamer",
  "description": "Alto desempenho para jogos",
  "price": 4500.0,
  "inStock": 10,
  "category": "Eletrônicos",
  "supplier": "Fornecedor ABC"
}
```

### 3. Exemplos de Exceções
❌ Categoria não encontrada
JSON enviado:

```json
{
  "name": "Mouse sem fio",
  "price": 150.0,
  "categoryId": "cat-inexistente",
  "supplierId": "sup-123"
}
```
- **Resposta 404 Not Found:**
```bash
{
  "error": "Categoria não encontrada com ID: cat-inexistente"
}
```
❌ Fornecedor não encontrado
- **JSON enviado:**
```{
  "name": "Teclado",
  "price": 250.0,
  "categoryId": "cat-123",
  "supplierId": "sup-inexistente"
}
```
- **Resposta 404 Not Found:**
```bash
{
  "error": "Fornecedor não encontrado com ID: sup-inexistente"
}
```
❌ Validação falhou: campos obrigatórios não enviados
- **JSON enviado:**
```json
{
  "description": "Produto sem nome e preço"
}
```
- **Resposta 400 Bad Request:**
```bash
{
  "name": "O nome é obrigatório",
  "price": "O preço é obrigatório"
}
```
❌ Preço negativo
- **JSON enviado:**
```json
{
  "name": "Produto Teste",
  "price": -10.0,
  "categoryId": "cat-123",
  "supplierId": "sup-123"
}
```
- **Resposta 400 Bad Request:**
```bash
{
  "error": "O preço não pode ser negativo."
}
```

### 4. Buscar Produtos (GET)
Buscar todos os produtos
- **URL:** http://localhost:8080/api/products
- **Resposta 200 OK:**
```bash
[
  {
    "id": "prod-123",
    "name": "Notebook Gamer",
    "description": "Alto desempenho para jogos",
    "price": 4500.0,
    "inStock": 10,
    "category": "Eletrônicos",
    "supplier": "Fornecedor ABC"
  }
]
```

### 5. Buscar Produto por ID (GET)
- **URL:** ```http://localhost:8080/api/products/{id}```
- **Respostas:**
  - `200 OK`: Produto retornada.
  - `404 Not Found`: Produto não encontrado.

### 6. Deletar Produto (DELETE)
- **URL:** ```http://localhost:8080/api/products/{id}```
- **Respostas:**
  - `204 OK`: Produto deletada.
  - `404 Not Found`: Produto não encontrado.

### 7. Atualizar Produto (PUT)
- **URL:** ```http://localhost:8080/api/products/{id}```
```json

{
  "name": "Notebook Gamer Atualizado",
  "description": "Versão atualizada do produto",
  "price": 4800.0,
  "inStock": 15,
  "categoryId": "cat-123",
  "supplierId": "sup-123"
}

```


## Testes
- Execute `mvn test`

![image](https://github.com/user-attachments/assets/f071204e-898f-40a5-a43a-e9da32ff4caf)


## Tempo Estimado
- Desenvolvimento: ~6 horas
- Documentação: ~1 hora
- Testes: ~2 horas

## Desafios e Soluções
- Configuração do Docker para MongoDB
- Configuração Swagger para documentação interativa
- Organização em camadas seguindo boas práticas

Relatório: https://github.com/tnrjr/desafio-minitok/blob/main/RELATORIO.md
