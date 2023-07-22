package com.example.demomaplerad.repository;

import com.example.demomaplerad.model.User;
import com.example.demomaplerad.model.Wallet;
import com.example.demomaplerad.model.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findWalletByCustomerAndCurrency(User user, Currency currency);
    boolean existsByCustomerAndCurrency(User customer, Currency currency);
}
