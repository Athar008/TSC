package com.nagraaa.tv.controller;

import com.nagraaa.tv.entity.Character;
import com.nagraaa.tv.entity.TvShow;
import com.nagraaa.tv.interfaces.CharacterRepository;
import com.nagraaa.tv.interfaces.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shows")
public class TvShowController {

    @Autowired
    private TvShowRepository tvShowRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping
    public List<TvShow> getAllTvShows() {
        return tvShowRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable(value = "id") Long tvShowId) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            return ResponseEntity.ok().body(tvShow);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}/characters")
    public ResponseEntity<List<Character>> getCharactersByTvShowId(@PathVariable(value = "id") Long tvShowId) {
        List<Character> characters = characterRepository.findByTvShowId(tvShowId);
        if (characters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characters);
    }



}
