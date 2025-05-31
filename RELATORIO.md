# Relatório de Migração e Desafios

## Processo de Migração

A migração consistiu em reestruturar o CRUD de produtos para suportar relacionamentos com categorias e fornecedores, garantindo integridade referencial no MongoDB. Foram criados endpoints específicos para gerenciamento de categorias e fornecedores, além de ajustes no ProductService para buscar e associar essas referências corretamente.

Foram implementados testes unitários para garantir a qualidade do código, além de ajustes no tratamento global de exceções e no uso do Swagger para documentação automática da API.

## Principais Desafios Encontrados

- Gerenciamento de referências entre documentos no MongoDB (Category e Supplier).
- Ajustes nos testes unitários para considerar os novos relacionamentos.
- Configuração do Swagger para suportar a estrutura atual.
- Tratar o erro 500 no `/v3/api-docs` causado por exceções internas no contexto da API.
- Integração do ambiente com Docker, garantindo que o banco de dados (MongoDB) e a aplicação (Spring Boot) sejam executados de forma simples e eficiente em containers isolados.

## Tempo Total Estimado

Tempo total para desenvolvimento e ajustes: **9 horas** (aproximadamente).

