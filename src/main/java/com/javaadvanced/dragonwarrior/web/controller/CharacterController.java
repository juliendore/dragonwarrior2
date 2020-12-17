package com.javaadvanced.dragonwarrior.web.controller;

import com.javaadvanced.dragonwarrior.dao.CharacterDao;
import com.javaadvanced.dragonwarrior.model.Character;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Api(description = "Controleur pour les opérations CRUD sur les personnages.")
@RestController
public class CharacterController {

    @Autowired
    private CharacterDao characterDao;


    //Récupérer la liste des produits
    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public @ResponseBody
    Iterable<Character> listeProduits() {
        return characterDao.findAll();
    }

    @ApiOperation(value = "Récupère un personnage spécifique grâce à son ID.")
    @GetMapping(value = "/characters/{id}")
    public Character getOneCharacter(@ApiParam("ID du personnage à retourner.") @PathVariable int id) {
        return characterDao.findById(id);
    }

    @PostMapping(path = "/characters")
    public @ResponseBody
    ResponseEntity<Object> addNewUser(@RequestBody Character person) {
        Character n = new Character();
        n.setName(person.getName());
        n.setType(person.getType());
        Character characterAdded = characterDao.save(n);

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

    @DeleteMapping(path = "characters/{id}")
    public String deletePerson(@PathVariable Integer id) {
        characterDao.deleteById(id);
        return "Deleted";
    }

    @PutMapping(path = "characters/{id}")
    public String updatePerson(@PathVariable Integer id, @RequestBody Character character) {
        Character actualCharacter = characterDao.findById(id).get();


        if (character.getName() != null && character.getName().length() > 0) {
            actualCharacter.setName(character.getName());
        }
        if (character.getType() != null && character.getType().length() > 0) {
            actualCharacter.setType(character.getType());
        }


        characterDao.save(actualCharacter);
        return "Updated";
    }
}
