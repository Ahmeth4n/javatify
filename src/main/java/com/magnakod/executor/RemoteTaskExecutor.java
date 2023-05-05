package com.magnakod.executor;

import com.magnakod.Constants;
import com.magnakod.emulator.Spotify;
import com.magnakod.entity.Servers;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RemoteTaskExecutor extends Spotify {
    public boolean startTask(String url,Constants.SPOTIFY_TASK_TYPE taskType) {
        return false;
    }

    public boolean stopTask(String url) {
        return false;
    }
}
