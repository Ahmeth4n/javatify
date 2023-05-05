package com.magnakod.repository;

import com.magnakod.entity.Accounts;
import com.magnakod.entity.Servers;
import com.magnakod.entity.Tasks;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServersRepository  extends MongoRepository<Servers,String> {
    List<Servers> findAllByIdIsNotNull(Pageable pageable);
    Servers getServersByIdIs(@NotNull String id);
    List<Servers> findServersByServerStatusIsNot(Servers.SERVER_STATUS status);

}
