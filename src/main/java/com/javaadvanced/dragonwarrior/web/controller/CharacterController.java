package com.javaadvanced.dragonwarrior.web.controller;

import com.javaadvanced.dragonwarrior.model.Character;
import org.springframework.web.bind.annotation.*;

@RestController
public class CharacterController {

    @RequestMapping(value = "/characters", method = RequestMethod.GET)
    public String getAllCharacters() {
        return "Voici la liste de tout les personnages";
    }


    @GetMapping(value = "/characters/{id}")
    public Character getOneCharacter(@PathVariable int id) {

        Character character = new Character(id, new String("Toto"), new String("Wizard"));

//        return "Voici le personnage avec l'id :  " + id;

        return character;
    }


}
