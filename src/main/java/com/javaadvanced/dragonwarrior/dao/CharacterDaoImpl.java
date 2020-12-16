package com.javaadvanced.dragonwarrior.dao;

import com.javaadvanced.dragonwarrior.model.Character;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;


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
        for (Character c : characters) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public Character save(Character character) {
        if (!characters.isEmpty()) {

            Character maxId = characters
                    .stream()
                    .max(Comparator.comparing(Character::getId))
                    .orElseThrow(NoSuchElementException::new);


            System.out.println(maxId.getId());

            Character newCharacter = new Character(maxId.getId() + 1, character.getName(), character.getType());

            characters.add(newCharacter);

            return newCharacter;
        }

        Character newCharacter = new Character(1, character.getName(), character.getType());

        characters.add(newCharacter);

        return newCharacter;
    }

    public boolean delete(int id) {
        var isRemoved = characters.removeIf(character -> String.valueOf(character.getId()).equals(String.valueOf(id)));

        return isRemoved;
    }

    public boolean update(int id, Character newData) {
        for (Character c : characters) {
            if (c.getId() == id) {
                if (newData.getName() != null && newData.getName().length() > 0) {
                    c.setName(newData.getName());
                }
                if (newData.getType() != null && newData.getType().length() > 0) {
                    c.setType(newData.getType());
                }
                return true;
            }
        }
        return false;
    }
}
