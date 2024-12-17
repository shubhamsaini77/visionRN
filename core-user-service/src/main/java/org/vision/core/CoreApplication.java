package org.vision.core;

import org.vision.core.common.VisionObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "org.vision.core")
public class CoreApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CoreApplication.class, args);

		SpringApplication springApplication = new SpringApplication(CoreApplication.class);
		springApplication.run(args);
	}

	private static boolean checkCacheManager()
	{
		try {
			VisionObject whiteRoseObject = new VisionObject();
			whiteRoseObject.setId(UUID.randomUUID().toString());
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
