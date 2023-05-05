package com.magnakod.service.serverReports;

import com.magnakod.entity.ServerReports;
import com.magnakod.repository.ServerReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ServerReportsImpl implements ServerReportsService{
    @Autowired
    private ServerReportsRepository serverReportsRepository;

    @Override
    public void update(String data,int successCount, int failCount){
        ServerReports serverReports = new ServerReports();
        serverReports.setDate(new Date());
        serverReports.setServerReports(data);
        serverReports.setTotalSuccededCount(successCount);
        serverReports.setTotalFailedCount(failCount);
        serverReportsRepository.save(serverReports);
    }
}
