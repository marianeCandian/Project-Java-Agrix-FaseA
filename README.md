# Agrix - Fase A
Este projeto em Java consiste em uma aplicação Spring Boot que faz parte do desenvolvimento do Agrix, um sistema para gestão e monitoramento de fazendas. Esta é a fase C do projeto, que implementa funcionalidades iniciais e servirá de base para as próximas etapas.

## Descrição
Maria e João são empreendedores preocupados com os impactos ambientais e sociais dos processos agrícolas. Por isso, criaram a AgroTech, uma empresa especializada em tecnologias para melhorar a eficiência no cultivo de plantações, visando reduzir o desperdício de recursos e alimentos.

O primeiro produto da empresa é o Agrix, um sistema para gestão e monitoramento de fazendas. Esta aplicação Spring Boot é parte do desenvolvimento do Agrix.

## Funcionalidades

Maria e João são pessoas empreendedoras que estão muito preocupadas com os impactos ambientais e sociais dos nossos processos agrícolas. Por isso, decidiram criar a AgroTech, uma empresa especializada em tecnologias para melhorar a eficiência no cultivo de plantações. Isso visa reduzir o desperdício de recursos em geral e de alimentos em específico, fazendo um uso mais responsável da terra disponível para plantio.

O primeiro produto dessa empresa será o Agrix, um sistema que permitirá a gestão e o monitoramento das fazendas participantes. Esse produto será desenvolvido em fases.

Uma aplicação Spring Boot com algumas funcionalidades iniciais, e que servirá de base para as próximas fases.

## Conceitos abordados
Estruturação de um projeto em camadas.
Implementação de uma API para interação com o sistema.
Implementação de persistência de dados usando Spring Data JPA.
Testes unitários para garantir a qualidade do código.
Dockerização da aplicação para facilitar a implantação.

## Requisitos Desenvolvidos

### 1. Crie uma API para controle de fazendas com a rota POST /farms

<details>
  <summary>Crie sua aplicação com uma API para gerenciamento de fazendas que inclua a rota POST</summary><br />

Neste requisito, você deverá criar a base para gerenciamento de fazendas da sua API, utilizando
Spring, Spring Boot, Spring Web e Spring Data. Lembre-se que, para isso, você deve criar e configurar sua aplicação apropriadamente, incluindo as dependências e quaisquer classes/camadas que julgar necessárias.

Neste requisito, além de criar a base da sua aplicação, você deverá criar também a primeira rota:

- `/farms` (`POST`)
    - deve receber via corpo do POST os dados de uma fazenda (veja abaixo para os
      dados de requisição e resposta)
    - deve salvar uma nova fazenda a partir dos dados recebidos
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da fazenda criada. O `id` da fazenda deve estar incluso na resposta.

<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição:
```json
{
  "name": "Fazendinha",
  "size": 5
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Fazendinha",
  "size": 5
}
```
</details>

</details>


### 2. Crie a rota GET /farms

<details>
  <summary>Crie a rota GET /farms da sua API, para retornar todas as fazendas cadastradas </summary><br />

Neste requisito, você deverá criar a rota:

- `/farms` (`GET`)
    - deve retornar uma lista de todas as fazendas. O `id` da fazenda deve estar
      incluso na resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "name": "Fazendinha",
    "size": 5.0
  },
  {
    "id": 2,
    "name": "Fazenda do Júlio",
    "size": 2.5
  }
]
```

</details>

</details>

### 3. Crie a rota GET /farms/{id}

<details>
  <summary>Crie a rota GET /farms da sua API, para retornar as informações de uma fazenda</summary><br />

Neste requisito, você deverá criar a rota:

- `/farms/{id}` (`GET`):
    - deve receber um `id` pelo caminho da rota e retornar a fazenda com esse `id`. O `id` da
      fazenda deve estar incluso na resposta.
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta. Você pode definir a estrutura da
      resposta como preferir, desde que contenha a mensagem.
        - Dica: pense desde já em como você vai fazer o gerenciamento de erros, pois isso afetará o
          restante da aplicação.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/3` (supondo que exista uma fazenda com `id = 3`):

```json
{
  "id": 3,
  "name": "My Cabbages!",
  "size": 3.49
}
```

</details>

</details>

### 4. Crie a rota POST /farms/{farmId}/crops

<details>
  <summary>Continue a implementação da sua aplicação, agora criando a rota POST /farms/{farmId}/crops para criação de plantações</summary><br />

Neste requisito, você deverá criar a rota criação de plantações. Lembre-se que as plantações estão em um relacionamento `n:1` com as fazendas, então considere isso na hora de implementar sua solução deste e dos próximos requisitos.

A rota a ser criada é:
- `/farms/{farmId}/crops` (`POST`)
    - deve receber o `id` da fazenda pelo caminho da rota (representado aqui por `farmId` apenas para diferenciar da plantação)
    - deve receber via corpo do POST os dados da plantação (veja abaixo para os dados de requisição
      e resposta)
    - deve salvar a nova plantação a partir dos dados recebidos, associada à fazenda com o ID
      recebido
    - em caso de sucesso, deve:
        - retornar o status HTTP 201 (CREATED)
        - retornar os dados da plantação criada. A resposta deve incluir o `id` da plantação e
          o `id` da fazenda, mas não deve incluir os dados da fazenda.
    - caso não exista uma fazenda com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.
    - 
<details>
  <summary>🔍 Formato/exemplo de requisição e resposta</summary><br />

Exemplo de requisição na rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
{
  "name": "Couve-flor",
  "plantedArea": 5.43
}
```

Exemplo de resposta:

```json
{
  "id": 1,
  "name": "Couve-flor",
  "plantedArea": 5.43,
  "farmId": 1
}
```

Note que o `id` da resposta se refere à plantação, e que o da fazenda está em `farmId`.

</details>

</details>

### 5. Crie a rota GET /farms/{farmId}/crops

<details>
  <summary>Crie a rota GET /farms/{farmId}/crops para listar as plantações de uma fazenda</summary><br />

Neste requisito, você deverá criar a rota para listar as plantações de uma fazenda. A rota a ser criada é:
- `/farms/{farmId}/crops` (`GET`):
    - deve receber o `id` de uma fazenda pelo caminho
    - deve retornar uma lista com todas as plantações associadas à fazenda
    - caso não exista uma fazenda com esse `id`, a rota retornar o status HTTP 404 com a
      mensagem `Fazenda não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/farms/1/crops` (supondo que exista uma fazenda com `id = 1`):

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  }
]
```

</details>

</details>

### 6. Crie a rota GET /crops

<details>
  <summary>Crie a rota GET /crops para listar todas as plantações cadastradas</summary><br />

A rota a ser criada é:
- `/crops` (`GET`)
    - deve retornar uma lista de todas as plantações cadastradas. A resposta deve incluir o `id` de
      cada plantação e o `id` da fazenda associada, mas não deve incluir os dados da fazenda.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

```json
[
  {
    "id": 1,
    "name": "Couve-flor",
    "plantedArea": 5.43,
    "farmId": 1
  },
  {
    "id": 2,
    "name": "Alface",
    "plantedArea": 21.3,
    "farmId": 1
  },
  {
    "id": 3,
    "name": "Tomate",
    "plantedArea": 1.9,
    "farmId": 2
  }
]
```

</details>

</details>

### 7. Crie a rota GET /crops/{id}

<details>
  <summary>Crie a rota GET /crops/{id} para retornar as informações de uma fazenda</summary><br />

A rota a ser criada é:
- `/crops/{id}` (`GET`):
    - deve receber o `id` de uma plantação pelo caminho da rota
    - caso exista a plantação com o `id` recebido, deve retornar os dados da plantação. A resposta
      deve incluir o `id` de cada plantação e o `id` da fazenda associada, mas não deve incluir os
      dados da fazenda.
    - caso não exista uma plantação com o `id` passado, a rota deve retornar o status HTTP 404 com a
      mensagem `Plantação não encontrada!` no corpo da resposta.

<details>
  <summary>🔍 Formato/exemplo de resposta</summary><br />

Exemplo de resposta para a rota `/crops/3` (supondo que exista uma plantação com `id = 3`:

```json
{
  "id": 3,
  "name": "Tomate",
  "plantedArea": 1.9,
  "farmId": 2
}
```

</details>

</details>

### 8. Crie um Dockerfile para sua aplicação

<details>
  <summary>Crie um Dockerfile multi-estágio configurando a imagem Docker da sua aplicação</summary><br />

Finalmente, você deve construir um `Dockerfile` para rodar a sua aplicação no Docker.

Seu `Dockerfile`:

- Deve ser multi-estágio
- O primeiro estágio deve se chamar `build-image` e deve ser utilizado para a construção do pacote da sua aplicação, contendo:
    - Um diretório de trabalho (workdir) chamado `/to-build-app`
    - A cópia dos arquivos necessários
    - A instalação das dependências utilizando Maven
        - Aqui, se quiser você pode utilizar o goal `dependency:go-offline` do Maven, que vai baixar todas as dependências e pode ajudar o Docker a criar um cache que agilize o processo de re-criação da imagem.
    - A construção do pacote JAR utilizando Maven com o goal `package`
- O segundo estágio deve ser utilizado para a construção da imagem final, contendo:
    - Um diretório de trabalho (workdir) chamado `/app`
    - A cópia dos arquivos necessários a partir da imagem do primeiro estágio
    - A exposição da porta `8080`
    - Um ponto de entrada (entrypoint) executando o pacote da aplicação

Notas:
1. Você pode usar as imagens de base que preferir para cada estágio. Uma possibilidade é utilizar a `maven:3-openjdk-17` para o estágio de construção, pois já traz o Maven instalado. Já para o estágio final você pode usar uma imagem de tamanho reduzido, como a `eclipse-temurin:17-jre-alpine`, por exemplo.
2. Apesar de o Maven já instalar as dependências na construção do pacote, como mencionado é útil termos uma execução da instalação separada da construção no primeiro estágio, para termos os benefícios de cache do Docker e reduzir o tempo de reconstrução.
3. Quando for testar sua imagem, lembre-se que a exposição da porta no Dockerfile não faz o mapeamento automaticamente (diferente do `docker-compose`). Nesse caso, é necessário passar o mapeamento por parâmetro para o docker na hora da execução da imagem.

</details>

## Por que isso é importante?
Este projeto é uma oportunidade para colocar em prática os conhecimentos adquiridos sobre Coleções, Spring Data JPA e Spring Relacionamentos. Além disso, permite exercitar habilidades na definição da arquitetura de um projeto e tomar decisões sobre sua estruturação.

## Como usar
1-Clone o repositório para o seu ambiente local:
git clone https://github.com/marianeCandian/Project-Java-Agrix-FaseC.git

2-Abra o projeto em sua IDE Java preferida.

3-Execute o programa principal para iniciar a aplicação.

4-Utilize ferramentas como Postman ou curl para enviar requisições para as rotas disponíveis na API.

5-Consulte a documentação da API ou os testes unitários para obter informações detalhadas sobre como usar cada rota.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests para melhorar este projeto.
