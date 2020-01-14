package com.carlosserrano.apirestful.services;

import com.carlosserrano.apirestful.exceptions.RecordNotFoundException;
import com.carlosserrano.apirestful.model.Item;
import com.carlosserrano.apirestful.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Service annotation used to declare the class as a Service class 
 * which holds the business logic.
 * In our above Service class , we are injecting the instance of class 
 * ItemRepository using @Autowired annotation.
 * Spring Data JPA will automatically generate the proxy instance of the 
 * class ItemRepository and will inject it to the instance of SItemService class.
 * The above methods is ItemService class are calling the JpaRepository’s methods 
 * to retrieve the Items/ delete the Item / Create or Update the Item from the database.
 * @author carlosserrano
 */
 
@Service
public class ItemService {
     
    @Autowired
    ItemRepository repository;
     
    public List<Item> getAllItems()
    {
        List<Item> itemList = repository.findAll();
         
        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Item>();
        }
    }
     
    public Item getItemById(Long id) throws RecordNotFoundException
    {
        Optional<Item> item = repository.findById(id);
         
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No student record exist for given id",id);
        }
    }
     
    public Item createOrUpdateItem(Item entity) throws RecordNotFoundException
    {
    	    	
    	if(entity.getId()!=null)
    	{
    	  Optional<Item> item = repository.findById(entity.getId());
        
    	if(item.isPresent())
        {
            Item newEntity = item.get();
            newEntity.setId(entity.getId());
            newEntity.setTitle(entity.getTitle());
            newEntity.setDescription(entity.getDescription());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    	}
    	
    	else
    	{
    		entity = repository.save(entity);
    		return entity;
    	}	    
 }
     
    public void deleteItemById(Long id) throws RecordNotFoundException
    {
        Optional<Item> item = repository.findById(id);
         
        if(item.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }

    //PASO 2
    public List<Item> getItemByTitle(String title) {
        List<Item> itemList = repository.findByTitle(title);
        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Item>();
        }
    }
}
