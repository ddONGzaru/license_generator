package io.manasobi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import io.manasobi.commons.config.ConfigFileManager;


@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class AppRunner {

    public static void main(String[] args) {
    	
    	SpringApplication app = new SpringApplication(AppRunner.class);
    	
    	app.addListeners(getConfigFileApplicationListener(args));
    	app.run(args);
    }
    
    private static ConfigFileApplicationListener getConfigFileApplicationListener(String[] args) {
    	
    	ConfigFileManager configFileManager = new ConfigFileManager(args);
    	
    	ConfigFileApplicationListener listener = new ConfigFileApplicationListener();
    	
    	listener.setSearchLocations(configFileManager.getAppConfigLocation());
    	listener.setSearchNames(configFileManager.getAppConfigNames());
    	
    	return listener;    	
    }
    
}
