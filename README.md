# 🍔 Sistema para Cantina Escolar
 
Aplicação web para reserva antecipada de lanches em escola pública, desenvolvida para reduzir filas no intervalo e dar previsibilidade de demanda à cantina.
 
Projeto Integrador — Engenharia de Computação, Universidade de Taubaté (UNITAU), 2026.
 
![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-4169E1?style=flat&logo=postgresql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white)
 
---
 
## O problema
 
No intervalo, a cantina da escola concentra centenas de alunos em poucos minutos. O resultado é fila longa, aluno que volta para a sala sem conseguir comprar e cantina que produz no escuro — sem saber quanto preparar, sobra ou falta.
 
## A solução
 
O aluno faz a reserva antes do intervalo, pelo celular. A cantina recebe os pedidos com antecedência, produz na medida e entrega em um balcão de retirada separado da fila comum.
 
---
 
## Funcionalidades
 
- **Cadastro e autenticação** de alunos e funcionários da cantina
- **Catálogo de produtos** com controle de estoque em tempo real
- **Reserva antecipada** com horário de corte configurável
- **Limite de capacidade** por intervalo, evitando mais pedidos do que a cantina consegue produzir
- **Ciclo de vida do pedido** — reservado, em preparo, pronto, retirado
- **Painel da cantina** para gestão de produtos e acompanhamento dos pedidos do dia
- **Interface responsiva**, pensada para uso no celular
---
 
## Tecnologias
 
| Camada | Tecnologia |
|---|---|
| Linguagem | Java 17 |
| Framework | Spring Boot |
| Persistência | Spring Data JPA / Hibernate |
| Banco de dados | PostgreSQL |
| Template engine | Thymeleaf |
| Interface | HTML, CSS |
 
---
 
## Arquitetura
 
Arquitetura em camadas, com as responsabilidades separadas e as regras de negócio isoladas da camada web:
 
```
controller  →  recebe as requisições e devolve as views
service     →  regras de negócio e controle transacional
repository  →  acesso a dados via Spring Data JPA
model       →  entidades de domínio
```
 
**Controle transacional de estoque.** A baixa de estoque e a criação do pedido acontecem na mesma transação, o que garante consistência quando vários alunos pedem o mesmo produto ao mesmo tempo — sem reserva de item que já acabou.
 
**Regras de negócio na camada de serviço:**
 
- horário de corte — depois dele, o sistema não aceita mais reservas para aquele intervalo
- limite de capacidade — teto de pedidos por intervalo, definido pela cantina
- transições válidas do pedido — um pedido só avança pelos estados na ordem correta
---

## Autor
 
**Davi Nicésio Martins de Resende**
