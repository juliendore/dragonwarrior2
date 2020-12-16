package com.javaadvanced.dragonwarrior.dao;

import com.javaadvanced.dragonwarrior.model.Character;
import java.util.List;

public interface CharacterDao {
    public List<Character> findAll();
    public Character findById(int id);
    public Character save(Character character);
    public boolean delete(int id);
    public boolean update(int id, Character newData);
}
