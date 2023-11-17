package com.redis.crud.redis.repo;

import com.redis.crud.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate redisTemplate;

    public Product save(Product product){
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> getAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product getById(int id){
        System.out.println("From DB : {}");
        return (Product) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(int id){
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Product Removed";
    }
}

