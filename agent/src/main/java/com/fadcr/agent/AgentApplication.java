package com.fadcr.agent;

import com.fadcr.agent.config.ConfigFile;
import com.fadcr.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com")

public class AgentApplication {

	@Autowired
	AgentService agentService;

	public static void main(String[] args) {

		SpringApplication.run(AgentApplication.class, args);
	}

	@Component
	public class MyCommandLineRunner implements CommandLineRunner {

		@Override
		public void run(String... args) throws Exception {
			agentService.sendRegisterRequest();
		}
		}

}
