package com.example.demo.mongo.controller;

import com.example.demo.mongo.model.GroceryItem;
import com.example.demo.mongo.repository.ItemRepository;
import com.example.demo.mongo.service.ProductService;
import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class HomeController {

    @Autowired
    private ItemRepository groceryItemRepo;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(){
        return "HELLO MONGO!!!";
    }

    @GetMapping("/getall")
    public List<GroceryItem> getAll(){
        System.out.println("pozvan je kontroler getAll...");
        //groceryItemRepo.findAll();
        //return groceryItemRepo.findAll();
        List itemList=groceryItemRepo.findAll();
        //itemList.forEach(item-> System.out.println(getItemDetails((GroceryItem) item)));
        return itemList;

    }

    @PostMapping("")
    public GroceryItem addNew(@RequestBody GroceryItem newItem){
        return groceryItemRepo.save(newItem);
    }

    public String getItemDetails(GroceryItem item){
        System.out.println(
                "Item name: "+item.getName()+" | Quantity: "+item.getQuantity()+" | Category: "+item.getCategory()
        );
    return "";
    }

    @PutMapping("")
    public void updateProduct(@RequestParam int newQuantity){
        System.out.println("pozvan je kontroler updateProduct...");
        productService.updateProduct(newQuantity);
    }



}
