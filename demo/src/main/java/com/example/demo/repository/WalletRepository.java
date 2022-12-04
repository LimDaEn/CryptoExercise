package com.example.demo.repository;

import com.example.demo.dao.Wallet;
import com.example.demo.dao.WalletId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, WalletId> {

    List<Wallet> findByUserId(int id);
}
