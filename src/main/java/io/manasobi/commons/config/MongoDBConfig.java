package io.manasobi.commons.config;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@Configuration
@EnableMongoRepositories("io.manasobi.license.pager")
public class MongoDBConfig extends AbstractMongoConfiguration {

	@Bean
    public Jongo jongo() {
		
        DB db;
        
        try {
            db = new MongoClient("127.0.0.1", 27017).getDB("manasobi");
        } catch (UnknownHostException e) {
            throw new MongoException("Connection error : ", e);
        }
        
        return new Jongo(db);
    }

    @Bean
    public MongoCollection users() {
        return jongo().getCollection("users");
    }

    @Bean
    public MongoCollection licenseDetailsRepo() {
    	return jongo().getCollection("licenseDetails");
    }

    
    
    @Value("${spring.data.mongodb.host:127.0.0.1}")
	private String host;

	@Value("${spring.data.mongodb.port:27017}")
	private int port;
	
	@Value("${spring.data.mongodb.database:manasobi}")
	private String database;
	
	@Override
	protected String getDatabaseName() {
		return database;
	}

	@Override
	public Mongo mongo() throws Exception {		
		return new MongoClient(host, port);
	}
	
	@Override
	protected String getMappingBasePackage() {
		return "io.manasobi";
	}
	
	
    
    
}
