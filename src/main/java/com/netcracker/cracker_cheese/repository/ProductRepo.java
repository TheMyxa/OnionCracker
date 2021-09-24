package com.netcracker.cracker_cheese.repository;


import com.netcracker.cracker_cheese.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product,Integer> {


}
