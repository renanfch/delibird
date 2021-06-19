# Delibird
API para agendamento de mensagens com serviços como email, sms, whatsapp;

Stack utilizada:

* Linguagem: [`Java`](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* Compilação: [`Maven`](https://maven.apache.org/)
* Framework: [`Spring Boot`](https://spring.io/projects/spring-boot)
* Banco de dados: [`H2`](h2database.com) / [`Flyway`](https://flywaydb.org)
* Documentação: [`Swagger`](https://swagger.io)
* Tests:
    * Teste de unidade [`jUnit5`](https://junit.org/junit5/docs/current/user-guide/)
    * Ferramenta para cobertura de tests [`Jacoco`](https://www.jacoco.org)


## Arquitetura

A estrutura do projeto foi desenvolvido pensando no conceito do
`Hexagonal Architecture`:

* **Configuration** - Configurações

* **Core** - Regra de negócio
    * **entity** - Entidades, objetos que representam o negócio
    * **use case** - Casos de uso do projeto
    * **command** - São objetos do core para executar algum comando no usecase, para deixar isolado.
    * **port** - Comunicação entre o centro do hexagono e os lados externos
    
* **Adapter** - Para cada porta existe um adapter que é a implementação da porta
    * **Data Provider** - Camada responsável em fornecer os dados para o `core`.
    * **Entry Points** - Responsável em fornecer os end-points, essa camada trata os dados que o usuário envia processa utilizando os `use cases`
  do `core` e trata seu retorno.
      

## Documentação


## Documentação dinâmica

O `Swagger` nos proporciona uma documentação dinâmica para os end-points
do projeto, também nos dando liberdade para efetuar execuções.


Link: [http://localhost:9000/swagger-ui.html]

## Executando com Docker

Os seguintes passos foram documentados para serem executados em uma plataforma
`Linux`, porém os comandos com o sistema `Windows` podem ser semelhantes, mas,
o funcionamento não é totalmente garantido.

- Certifique-se que você tenha as seguintes ferramentas instaladas:

    - [`Docker`](https://docs.docker.com/install/)
    - [`Docker Compose`](https://docs.docker.com/compose/install/)
    - [`Git`](https://git-scm.com/downloads)

- Efetue o clone do projeto pelo `Git`:

```shell
$git clone https://github.com/renanfch/delibird.git
```

- Acesse a pasta do projeto para iniciarmos a compilação:

```shell
$cd ./delibird
```

- As instruções a seguir executará atravéz do [`Maven`](https://maven.apache.org/)
  o `clean` do projeto, logo o `install` executará os testes unitários
  para garantir a integridade do projeto e gerar o executável (`.jar`)

```shell
$./mvn clean install
```

- Agora será realizado o `build` do [`Dockerfile`](./Dockerfile) gerando uma imagem
  no repositório local denominada de `renanfch/delibird`.

```shell
$docker build -t renanfch/delibird .
```

- Após ter gerado a imagem da API iremos executar o projeto utilizando o [`docker-compose`](./docker-compose.yml).

```shell
$docker-compose up
```


## Observações

