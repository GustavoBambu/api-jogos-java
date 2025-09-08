Essa é uma API simples para gerenciar jogos. É possível listar, adicionar, atualizar e remover jogos.

Como rodar:
Certifique-se de ter Java e Maven instalados.

No terminal, entre na pasta do projeto e rode:
mvn spring-boot:run
A API vai rodar no endereço http://localhost:8080

Rotas:

GET /jogos : lista todos os jogos
GET /jogos/{id} : busca um jogo pelo ID
POST /jogos : adiciona um novo jogo (é necessário informar o titulo)
PUT /jogos/{id} : atualiza um jogo existente
DELETE /jogos/{id} : remove um jogo

Exemplo de uso:

Para adicionar um jogo: enviar POST com JSON contendo titulo, genero e ano.
Para atualizar: enviar PUT com JSON com os campos que deseja atualizar.
Para deletar: enviar DELETE com o ID do jogo.

Status retornados:

200 para sucesso em GET e PUT
201 para criação em POST
204 para exclusão em DELETE
400 se os dados estiverem inválidos
404 se o jogo não existir
