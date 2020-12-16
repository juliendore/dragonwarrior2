package com.javaadvanced.dragonwarrior.web.controller;

import com.javaadvanced.dragonwarrior.dao.CharacterDao;
import com.javaadvanced.dragonwarrior.model.Character;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(description = "Controleur pour les opérations CRUD sur les personnages.")
@RestController
public class CharacterController {

    @Autowired
    private CharacterDao characterDao;

    @ApiOperation(value = "Récupère l'ensemble des personnages disponibles.")
    @GetMapping(value = "/characters")
    public List<Character> getAllCharacters() {
        return characterDao.findAll();
    }

    @ApiOperation(value = "Récupère un personnage spécifique grâce à son ID.")
    @GetMapping(value = "/characters/{id}")
    public Character getOneCharacter(@ApiParam("ID du personnage à retourner.") @PathVariable int id) {
//        Ajouter les reponse erreur en cas d'erreur dans l'exec des méthodes
        return characterDao.findById(id);
    }

    @ApiOperation(value = "Supprime un personnage spécifique grâce à son ID.")
    @DeleteMapping(value = "/characters/{id}")
    public ResponseEntity<Void> deleteOneCharacter(@ApiParam("ID du personnage à supprimer.") @PathVariable int id) {
        var isRemoved = characterDao.delete(id);
        if (!isRemoved) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Met à jour un personnage spécifique grâce à son ID.")
    @PutMapping(value = "/characters/{id}")
    public Character updateOneCharacter(@ApiParam("ID du personnage à mettre à jour.") @PathVariable int id, @ApiParam("Nouvelles informations du personnage au format JSON.") @RequestBody Character newData) {

        //        Ajouter les reponse erreur en cas d'erreur dans l'exec des méthodes

        characterDao.update(id, newData);
        return characterDao.findById(id);
    }


    @ApiOperation(value = "Ajoute un personnage à la liste des personnages disponibles.")
    @PostMapping(value = "/characters")
    //    Gerer le doublon dans le DAO
    public ResponseEntity<Void> addOneCharacter(@ApiParam("Informations du nouveau personnage au format JSON.") @RequestBody Character character) {
        Character characterAdded = characterDao.save(character);

        if (characterAdded == null)
            //  204 No Content + header
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(characterAdded.getId())
                .toUri();
        //  Header built with characterAdded location
        return ResponseEntity.created(location).build();
    }
}
