package com.example.marketPlaceApplication.services;

import com.example.marketPlaceApplication.models.Item;
import com.example.marketPlaceApplication.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    public List<Item> getItemByName (String name) {
        return itemRepository.findByName(name);
    }
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
    public Item updateItem(Long id, Item item) {
        Item tItem=itemRepository.findById(id).orElseThrow();
        tItem.setName(item.getName());
        tItem.setDescription(item.getDescription());
        tItem.setPrice(item.getPrice());
        return itemRepository.save(tItem);
    }
}
