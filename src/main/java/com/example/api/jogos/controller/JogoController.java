package com.example.api.jogos.controller;

import com.example.api.jogos.model.Jogo;
import com.example.api.jogos.repository.JogoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    private final JogoRepository repository;

    public JogoController(JogoRepository repository) {
        this.repository = repository;
    }

    // GET /jogos - lista todos os jogos
    @GetMapping
    public ResponseEntity<List<Jogo>> getAll() {
        List<Jogo> jogos = repository.findAll();
        return ResponseEntity.ok(jogos); // 200 OK
    }

    // GET /jogos/{id} - busca jogo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getById(@PathVariable int id) {
        return repository.findById(id)
                .map(jogo -> ResponseEntity.ok(jogo))       // 200 OK se encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 se não encontrado
    }

    // POST /jogos - adiciona um novo jogo
    @PostMapping
    public ResponseEntity<Jogo> create(@RequestBody Jogo jogo) {
        // Validação simples: título obrigatório
        if (jogo.getTitulo() == null || jogo.getTitulo().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request
        }

        Jogo novoJogo = repository.save(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoJogo); // 201 Created
    }

    // PUT /jogos/{id} - atualiza um jogo existente
    @PutMapping("/{id}")
    public ResponseEntity<Jogo> update(@PathVariable int id, @RequestBody Jogo jogoAtualizado) {
        return repository.findById(id)
                .map(jogoExistente -> {
                    // Atualiza campos
                    if (jogoAtualizado.getTitulo() != null) jogoExistente.setTitulo(jogoAtualizado.getTitulo());
                    if (jogoAtualizado.getGenero() != null) jogoExistente.setGenero(jogoAtualizado.getGenero());
                    if (jogoAtualizado.getAno() > 0) jogoExistente.setAno(jogoAtualizado.getAno());

                    Jogo atualizado = repository.save(jogoExistente);
                    return ResponseEntity.ok(atualizado); // 200 OK
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404 se não encontrado
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return repository.findById(id)
                .map(jogo -> {
                    repository.delete(jogo);
                    ResponseEntity<Void> response = ResponseEntity.noContent().build();
                    return response;
                })
                .orElse(ResponseEntity.notFound().build()); // 404 se não encontrado
    }

}
