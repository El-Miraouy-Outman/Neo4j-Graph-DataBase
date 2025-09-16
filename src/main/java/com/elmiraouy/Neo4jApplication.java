package com.elmiraouy;

import com.elmiraouy.command.InitDataBase;
import com.elmiraouy.command.TestQueryApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import picocli.CommandLine;
import picocli.spring.PicocliSpringFactory;

@SpringBootApplication
@CommandLine.Command(name ="Neo4jApplication",mixinStandardHelpOptions = true , description ="application")
public class Neo4jApplication implements CommandLineRunner {
    private final ApplicationContext applicationContext;

    public Neo4jApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
		System.out.println("class *************");
	}


	@Override
	public void run(String... args) throws Exception {
       PicocliSpringFactory picocliSpringFactory = new PicocliSpringFactory(applicationContext);
		CommandLine commandLine =new CommandLine(this,picocliSpringFactory);
		commandLine.addSubcommand(applicationContext.getBean(InitDataBase.class));
		commandLine.addSubcommand(applicationContext.getBean(TestQueryApi.class));
		commandLine.execute(args);
	}
}
