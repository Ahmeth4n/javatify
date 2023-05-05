package com.magnakod.repository;

import com.magnakod.entity.ServerReports;
import com.magnakod.entity.Servers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServerReportsRepository  extends MongoRepository<ServerReports,String> {
    List<ServerReports> findAllByIdIsNotNull(Pageable pageable);

}
