package com.javaadvanced.dragonwarrior.dao;

import com.javaadvanced.dragonwarrior.model.Character;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;


@Repository
public class CharacterDaoImpl implements CharacterDao {

    public static List<Character> characters = new ArrayList<>();

    static {
        characters.add(new Character(1, new String("Toto"), new String("Wizard")));
        characters.add(new Character(2, new String("Titi"), new String("Warrior")));
        characters.add(new Character(3, new String("Tutu"), new String("Wizard")));
    }

    @Override
    public List<Character> findAll() {
        return characters;
    }

    public Character findById(int id) {
        for (Character character : characters) {
            if (character.getId() == id) {
                return character;
            }
        }
        return null;
    }

    public Character save(Character character) {
        characters.add(character);
        return character;
    }

    public void delete(int id) {
        characters.removeIf(character -> String.valueOf(character.getId()).equals(String.valueOf(id)));
    }

    public void update(int id, String newName) {
        for (Character c : characters) {
            if (c.getId() == id) {
                c.setName(newName);
                return;
            }
        }
    }
}
