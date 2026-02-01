package com.lucky.springEcom.Services;

import com.lucky.springEcom.Models.Product;
import com.lucky.springEcom.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addOrUpdateProduct(Product product , MultipartFile image) throws IOException {

        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return productRepository.save(product);

    }

    public boolean deleteProductById(int id){

        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public List<Product> searchByName(String word){
        return productRepository.searchByWord(word);
    }
}
