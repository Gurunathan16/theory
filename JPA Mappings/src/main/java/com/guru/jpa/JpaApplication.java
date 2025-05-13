package com.guru.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args)
	{

		SpringApplication.run(JpaApplication.class, args);


	}
}
