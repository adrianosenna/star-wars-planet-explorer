# star-wars-planet-explorer
Esta é uma API simples utilizando arquitetura RESTFul com Spring Boot e MongoDB.

A API oferece dados de planetas que são inseridos via serviço REST. Dentro desses dados temos: Nome do planeta, clima, terreno e quantidade de aparoçoes nos filmes de Star Wars.

Para obter os dados, a API consome um serviço externo, o <a>https://swapi.co</a>, que disponibiliza todos os dados dos planetas mencionados na franquia.

#### Requisitos
<ul>
<li>JDK 1.8</li>
<li>Maven 3.2+</li>
<li>MongoDB</li>
</ul>

## Recursos

### Adicionar um planeta
Este serviço recebe os dados do planeta a ser adicionado: nome, clima e terreno.
<ul>
<li>Endpoint:http://localhost:8080/planets</li>
<li>HTTP Method: POST</li>
<li>Media Type: JSON (application/json)</li>
<li>Modelo do request body:

```
    {
        "name": "Earth",
        "climate": "tropical",
        "terrain": "florest, desert, ocean"
    } 
```

</li>
<li>Modelo do response body:

```
    {
        "id": "abcd1234",
        "name": "Earth",
        "climate": "tropical",
        "terrain": "florest, desert, ocean",
        "numberOfAppearanceInStarWars": 0
    }
```

</li>
</ul>

### Listar planetas
Este serviço retorna informações dos planetas adicionados através da API.
<ul>
<li>Endpoint:http://localhost:8080/planets</li>
<li>HTTP Method: GET</li>
<li>Media Type: JSON (application/json)</li>
</ul>

### Buscar por nome
Este serviço retorna as informações dos planetas com critério de pesquisa pelo nome.
<ul>
<li>Endpoint: http://localhost:8080/planets?name=[nome do planeta]</li>
<li>HTTP Method: GET</li>
<li>Media Type: JSON (application/json)</li>
</ul>

### Buscar por ID
Este serviço informações do planeta do ID informado.
<ul>
<li>Endpoint:http://localhost:8080/planets/[id do planeta]</li>
<li>HTTP Method: GET</li>
<li>Media Type: JSON (application/json)</li>
</ul>

### Remover planeta
Este serviço remove o planeta cadastrado através da API.
<ul>
<li>Endpoint:http://localhost:8080/planets/[id do planeta]</li>
<li>HTTP Method: DELETE</li>
<li>Media Type: JSON (application/json)</li>
</ul>
