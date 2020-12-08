package com.javaadvanced.dragonwarrior.web.controller;

import com.javaadvanced.dragonwarrior.dao.CharacterDao;
import com.javaadvanced.dragonwarrior.model.Character;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    private CharacterDao characterDao;

    @RequestMapping(value = "/characters", method = RequestMethod.GET)

    public List<Character> getAllCharacters() {
        return characterDao.findAll();
    }

    @GetMapping(value = "/characters/{id}")
    public Character getOneCharacter(@PathVariable int id) {
        return characterDao.findById(id);
    }

//    @PostMapping(value = "/characters")
//    public void addOneCharacter(@RequestBody Character character){
//        characterDao.save(character);
//    }

    //    ajouter un personnage
//    ResponseEntitty : permet de définir le code http retourné
    @PostMapping(value = "/characters")
    public ResponseEntity<Void> addOneCharacter(@RequestBody Character character) {

        Character characterAdded = characterDao.save(character);

        if (characterAdded == null)
//            204 No Content + header
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(characterAdded.getId())
                .toUri();
//        Header built with characterAdded location
        return ResponseEntity.created(location).build();
    }
}
