# Back-end Rachai

Bem-vindo ao repositório do Back-end da plataforma Rachai! Este repositório contém o código-fonte e as lógicas essenciais para a autenticação, conexões com banco de dados e outras funcionalidades do servidor da plataforma Rachai.

## Sobre a Plataforma Rachai

O "Rachai" é uma plataforma de caronas criada por estudantes para resolver problemas de transporte na faculdade, tornando o acesso ao campus mais fácil através de um aplicativo e plataforma web intuitivos. A equipe está trabalhando em entrevistas, desenvolvimento de software, integração de dados, interfaces amigáveis, feedback dos usuários e custos para garantir o sucesso e manutenção do sistema.

## Tecnologias Utilizadas

- Linguagem de Programação: Java
- Framework: Spring Boot
- Autenticação: JWT (JSON Web Token)
- Banco de Dados: H2
- API: REST

## Como rodar e utilizar a API

Se você deseja rodar o projeto localmente na sua máquina, rode esse docker-compose.yaml:
```
version: "3"
services:
  backend:
    image: kevinmamar/rachaibackend:latest
    ports:
      - 8080:8080
```

### Gerando usuários para teste
Você pode gerar 250 usuários para testar a aplicação na rota: http://localhost:8080/user/teste
**Aviso, você não deve dar f5 após gerar os 250 usuários, aguarde aparecer a mensagem "usuários gerados: 250"**

## Colaboradores

- [Allan Santos - Dev Back-end](https://github.com/AllanSantos-DV)
- [Lucas Christian - Dev Front-end](https://github.com/Lucas-Christian)

Agradecemos por ser parte da comunidade Rachai e por contribuir para a construção de projetos de jogos emocionantes!

---

© 2023 Rachai. Todos os direitos reservados.