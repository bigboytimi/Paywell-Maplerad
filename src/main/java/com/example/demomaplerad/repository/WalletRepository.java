package com.example.demomaplerad.repository;

import com.example.demomaplerad.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findWalletByAccountNumber(String accountNumber);
}
