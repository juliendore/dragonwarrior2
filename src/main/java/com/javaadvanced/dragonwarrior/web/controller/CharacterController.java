package com.javaadvanced.dragonwarrior.web.controller;

import com.javaadvanced.dragonwarrior.dao.CharacterDao;
import com.javaadvanced.dragonwarrior.model.Character;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

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
}
