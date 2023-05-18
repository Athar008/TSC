package com.nagraaa.tv.controller;

import com.nagraaa.tv.entity.Character;
import com.nagraaa.tv.entity.TvShow;
import com.nagraaa.tv.interfaces.CharacterRepository;
import com.nagraaa.tv.interfaces.TvShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<TvShow> createTvShow(@RequestBody TvShow tvShow) {
        TvShow createdTvShow = tvShowRepository.save(tvShow);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvShow);
    }
    @PostMapping("/{tvShowId}/characters")
    public ResponseEntity<Character> createCharacter(
            @PathVariable Long tvShowId,
            @RequestBody Character character
    ) {
        Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowId);
        if (optionalTvShow.isPresent()) {
            TvShow tvShow = optionalTvShow.get();
            character.setTvShowId(tvShowId); // Set the TV show ID in the character object
            Character createdCharacter = characterRepository.save(character);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }






}
