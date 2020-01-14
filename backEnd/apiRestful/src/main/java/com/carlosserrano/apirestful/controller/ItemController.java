package com.carlosserrano.apirestful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.carlosserrano.apirestful.model.Item;
import com.carlosserrano.apirestful.exceptions.RecordNotFoundException;
import com.carlosserrano.apirestful.services.ItemService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
 
//https://techblogstation.com/spring-boot/spring-boot-mysql-hibernate/

@RestController
@RequestMapping("/item")
public class ItemController
{
    @Autowired
    ItemService service;
 
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> list = service.getAllItems();
 
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Item entity = service.getItemById(id);
 
        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    //PASO2
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Item>> getItemByTitle(@PathVariable("title") String title)
                                                    throws RecordNotFoundException {
    	List<Item> list  = service.getItemByTitle(title);
 
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
   @PostMapping
    public ResponseEntity<Item> createOrUpdateItem(@Valid @RequestBody Item myItem)
                                                    throws RecordNotFoundException {
    	Item updated = service.createOrUpdateItem(myItem);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteItemById(id);
        return HttpStatus.OK;
    }
 
}
