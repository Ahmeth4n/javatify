package com.magnakod.repository;

import com.magnakod.entity.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends MongoRepository<Settings,String> {

}
