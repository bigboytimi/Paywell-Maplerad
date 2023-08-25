package com.paywell.demomaplerad.repository;

import com.paywell.demomaplerad.model.User;
import com.paywell.demomaplerad.model.Wallet;
import com.paywell.demomaplerad.model.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findWalletByCustomerAndCurrency(User user, Currency currency);
    boolean existsByCustomerAndCurrency(User customer, Currency currency);
}
