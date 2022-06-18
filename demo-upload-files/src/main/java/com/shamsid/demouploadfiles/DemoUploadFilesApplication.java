package com.shamsid.demouploadfiles;

import com.shamsid.demouploadfiles.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class DemoUploadFilesApplication implements CommandLineRunner {
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(DemoUploadFilesApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}
}
