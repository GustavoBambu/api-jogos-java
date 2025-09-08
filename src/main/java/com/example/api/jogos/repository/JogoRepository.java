package com.example.api.jogos.repository;

import com.example.api.jogos.model.Jogo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JogoRepository {

    private List<Jogo> jogos = new ArrayList<>();
    private int nextId = 4; // próximo ID a ser usado

    // Construtor: adiciona alguns jogos iniciais
    public JogoRepository() {
        jogos.add(new Jogo(1, "Zelda BOTW", "Aventura", 2017));
        jogos.add(new Jogo(2, "God of War", "Ação", 2018));
        jogos.add(new Jogo(3, "Elden Ring", "RPG", 2022));
    }

    // Retorna todos os jogos
    public List<Jogo> findAll() {
        return jogos;
    }

    // Buscar jogo por ID
    public Optional<Jogo> findById(int id) {
        return jogos.stream().filter(j -> j.getId() == id).findFirst();
    }

    // Adicionar um novo jogo
    public Jogo save(Jogo jogo) {
        if (jogo.getId() == 0) {  // se não tiver ID, gera automaticamente
            jogo.setId(nextId++);
        }
        jogos.add(jogo);
        return jogo;
    }

    // Remover jogo
    public void delete(Jogo jogo) {
        jogos.remove(jogo);
    }
}
