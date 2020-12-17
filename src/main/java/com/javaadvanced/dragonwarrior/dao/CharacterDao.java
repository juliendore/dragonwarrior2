//package com.javaadvanced.dragonwarrior.dao;
//
//import com.javaadvanced.dragonwarrior.model.Character;
//import java.util.List;
//
//public interface CharacterDao {
//    public List<Character> findAll();
//    public Character findById(int id);
//    public Character save(Character character);
//    public boolean delete(int id);
//    public boolean update(int id, Character newData);
//}

package com.javaadvanced.dragonwarrior.dao;

import com.javaadvanced.dragonwarrior.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterDao extends JpaRepository<Character, Integer> {
    public Character findById(int id);

//    public Character save(Character character);

//    public boolean delete(int id);

}