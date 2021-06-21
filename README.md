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
    * Teste de integração [`RestAssured`](https://rest-assured.io/) / [`TestContainers`](https://www.testcontainers.org/)
    * Ferramenta para cobertura de tests [`Jacoco`](https://www.jacoco.org)
    * Ferramenta para cobertura de tests com mutação [`Pitest`](https://pitest.org/)

## Arquitetura

A estrutura do projeto foi desenvolvido pensando no conceito do
`Hexagonal Architecture`:

* **Configuration** - Configurações de plugins e framework.

* **Core** - Regras de negócios
    * **command** - São objetos com intuito de executar um comando no core do projeto, 
      isolado dos dados de entrada e saída
    * **entity** - Entidades, objetos que representam o negócio
    * **use case** - Implementam regras de negócio de um caso de uso
    * **port** - Comunicação entre o centro do hexágono e os lados externos

* **Adapter** - Para cada porta existe um adapter que implementa a porta
    * **Data Provider** - Camada responsável em fornecer os dados para o `core`.
    * **Entry Points** - Responsável em fornecer os end-points, essa camada trata os dados que o usuário envia
      e converte para os objetos que são utilizados no `core` tratando depois seu retorno.

## Documentação

**Registrar agendamento de mensagem**

POST http://localhost:9000/schedule

Body

```json
{
  "message": "mensagem de envio",
  "messageService": "EMAIL",
  "recipient": "abcd@gmail.com",
  "sendTime": "2021-06-21T10:19"
}
```

Retorno:

```json
{
  "id": 1,
  "sendTime": "2021-06-21T10:19:00",
  "recipient": "abcd@gmail.com",
  "messageService": "EMAIL",
  "message": "mensagem de envio",
  "scheduleStatusEnum": "SCHEDULED"
}
```

**Consulta de agendamento de mensagem**

GET http://localhost:9000/schedule/{id}

Retorno

```json
{
  "id": 1,
  "sendTime": "2021-06-21T10:19:00",
  "recipient": "abcd@gmail.com",
  "messageService": "EMAIL",
  "message": "mensagem de envio",
  "scheduleStatusEnum": "SCHEDULED"
}
```

**Cancelamento de agendamento de mensagem**

DELETE http://localhost:9000/schedule/{id}

Retorno 204

## Documentação dinâmica

O `Swagger` nos proporciona uma documentação dinâmica para os end-points do projeto, também nos dando liberdade para
efetuar execuções.

Link: [http://localhost:9000/swagger-ui.html]

## Observabilidade

```Endpoint que fornece métricas no formato necessário para utilização em um servidor prometheus.
http://localhost:9000/actuator/prometheus
```

## Executando com Docker

Os seguintes passos foram documentados para serem executados em uma plataforma
`Linux`, porém os comandos com o sistema `Windows` podem ser semelhantes, mas, o funcionamento não é totalmente
garantido.

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
  o `clean` do projeto, logo o `install` executará os testes unitários para garantir a integridade do projeto e gerar o
  executável (`.jar`)

```shell
$./mvn clean install
```

- Agora será realizado o `build` do [`Dockerfile`](./Dockerfile) gerando uma imagem no repositório local denominada
  de `renanfch/delibird`.

```shell
$docker build -t renanfch/delibird .
```

- Após ter gerado a imagem da API iremos executar o projeto utilizando o [`docker-compose`](./docker-compose.yml).

```shell
$docker-compose up
```
