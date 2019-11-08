package com.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abc.service.UserService;

import static java.lang.System.exit;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) throws Exception {

        //disabled banner, don't want to see the spring logo
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

        //SpringApplication.run(SpringBootConsoleApplication.class, args);
    }
    
    private void printUsage() {
    	System.out.println("Usage: java -jar db-sync-1.0.jar [option]");
    	System.out.println("print        Print the differences between two databases");
    	System.out.println("fix          Fix the differences between two databases");
    }

    @Override
    public void run(String... args) throws Exception {

        if (args.length == 1) {
        	if ("print".equals(args[0]))
        		userService.printDiffUsers();
        	else if ("fix".equals(args[0]))
        		userService.fixDiffUsers();
        	else
        		printUsage();
        }else{
        	printUsage();
        }

        exit(0);
    }
}