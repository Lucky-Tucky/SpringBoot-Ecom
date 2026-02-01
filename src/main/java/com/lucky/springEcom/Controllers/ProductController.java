package com.lucky.springEcom.Controllers;

import com.lucky.springEcom.Models.Product;
import com.lucky.springEcom.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        Product product = productService.getProductById(id);

        if(product == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(product , HttpStatus.OK);
    }


    @GetMapping("/product/{id}/image")
    public ResponseEntity<?> getImageData(@PathVariable int id){
        Product product = productService.getProductById(id);
        if(product !=null ){
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product , @RequestPart MultipartFile image){

        try{
             return new ResponseEntity<>(productService.addOrUpdateProduct(product, image), HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/product")
    public ResponseEntity<String> updateProduct(@RequestPart Product product , @RequestPart MultipartFile image){
        try{
            productService.addOrUpdateProduct(product, image);
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }catch (IOException e){
            return new ResponseEntity<>(e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        boolean success = productService.deleteProductById(id);

        if(success){
             return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Error in Deletion", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/search/{word}")
    public ResponseEntity<?> getProductBySearch(@PathVariable String word){
        List<Product> products = productService.searchByName(word);

        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
