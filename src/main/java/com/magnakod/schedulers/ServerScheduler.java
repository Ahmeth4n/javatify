package com.magnakod.schedulers;

import com.google.gson.Gson;
import com.magnakod.HttpRequestClient;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.entity.ServerReports;
import com.magnakod.entity.Servers;
import com.magnakod.repository.ServerReportsRepository;
import com.magnakod.repository.ServersRepository;
import com.magnakod.responses.BaseResponse;
import com.magnakod.service.serverReports.ServerReportsImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ServerScheduler {
    private final Logger logger = LoggerFactory.getLogger(ServerScheduler.class);
    private final long SCHEDULE_DELAY_TIME = (60 * 60 * 1 * 1000);
    @Autowired
    private ServersRepository serversRepository;
    @Autowired
    private ServerReportsImpl serverReports;
    @Autowired
    private ServerReportsRepository serverReportsRepository;
    private final HttpRequestClient httpRequestClient = HttpRequestClient.getInstance();
    private static final String SERVER_FORMAT = "http://%s:%s/api/getServerStatus?auth=%s";
    public String getUrl(Servers server){
        return String.format(SERVER_FORMAT,server.getServerIp(),server.getServerPort(),SpotifyConstants.SERVER_AUTHORIZATION_KEY);
    }
    @Scheduled(fixedDelay = SCHEDULE_DELAY_TIME)
    @Async
    public void run(){
        AtomicInteger succeededServerCount = new AtomicInteger(0);
        AtomicInteger failedServerCount = new AtomicInteger(0);
        HashMap<String,Boolean> reportText = new HashMap<>();

        logger.info("serverScheduler - run() started.");
        List<Servers> servers = serversRepository.findAll();

        if (servers.isEmpty()){
            logger.error("ServerScheduler ({}) - servers not found.",new Date());
        }else{
            logger.info("ServerScheduler ({}) - servers founded",new Date());
            OkHttpClient localClient = httpRequestClient.getClient();

            for (Servers server : servers){
                Request.Builder httpRequest = new Request.Builder()
                        .get()
                        .url(getUrl(server));
                try {
                    Response response = localClient.newCall(httpRequest.build()).execute();
                    BaseResponse serializedResponse = new Gson().fromJson(response.body().string(), BaseResponse.class);

                    if (serializedResponse.isStatus()){
                        succeededServerCount.incrementAndGet();
                        reportText.put(server.getServerIp()+":"+server.getServerPort(),true);
                    }else{
                        failedServerCount.incrementAndGet();
                        reportText.put(server.getServerIp()+":"+server.getServerPort(),false);
                    }

                } catch (IOException e) {
                    failedServerCount.incrementAndGet();
                    reportText.put(server.getServerIp()+":"+server.getServerPort(),false);
                }


            }
            logger.info("total success count: {} , failed count: {}, log text: {}",succeededServerCount.get(),failedServerCount.get(),reportText.toString());
            serverReports.update(new JSONObject(reportText).toString(),succeededServerCount.get(),failedServerCount.get());
        }
    }

}
