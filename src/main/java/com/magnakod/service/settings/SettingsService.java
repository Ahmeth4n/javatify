package com.magnakod.service.settings;

import com.magnakod.entity.Settings;
import com.magnakod.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SettingsService implements SettingsServiceImpl {

    @Autowired
    private SettingsRepository settingsRepository;
    @Override
    public void updateSettings(String proxies) {
        Settings settings = settingsRepository.findAll().get(0);
        settings.setLastUpdateDate(new Date());
        settings.setProxyList(proxies);
        settingsRepository.save(settings);
    }
}
