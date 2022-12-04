package com.example.demo.repository;

import com.example.demo.dao.Price;
import com.example.demo.dao.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, PriceId> {
    List<Price> findByCommodity(String commodity);
}
