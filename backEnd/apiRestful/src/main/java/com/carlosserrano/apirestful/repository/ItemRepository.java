package com.carlosserrano.apirestful.repository;

import com.carlosserrano.apirestful.model.Item;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
 
/**
 * JpaRepository has several methods like save, delete, count , findOne etc. 
 * These methods are implemented by 
 * the SimpleJpaRepository class so we do not need to implement these methods.
 * @author carlosserrano
 */ 

@Repository
public interface ItemRepository
        extends JpaRepository<Item, Long> {

    //PASO2
    //https://www.baeldung.com/spring-data-jpa-query
    /*@Query(
  value = "SELECT * FROM items u WHERE u.title=?1", 
  nativeQuery = true)*/
    @Query(
  value = "SELECT * FROM items u WHERE u.title LIKE %?1%", 
  nativeQuery = true)
    public List<Item> findByTitle(String title);
 
}
