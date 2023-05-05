package com.magnakod.repository;

import com.magnakod.dto.AccountModel;
import com.magnakod.entity.Accounts;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends MongoRepository<Accounts,String> {
    List<Accounts> findAccountsByUsageIsFalse();
    List<Accounts> findAllByIdIsNotNull(Pageable pageable);
    Accounts findAccountsByIdIs(String id);
}
