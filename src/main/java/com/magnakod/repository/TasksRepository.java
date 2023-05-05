package com.magnakod.repository;

import com.magnakod.entity.Tasks;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends MongoRepository<Tasks,String> {
    List<Tasks> findAllByIdIsNotNull(Pageable pageable);
    Tasks findTaskByIdIsAndTaskStatusIs(String id, boolean taskStatus);

}
