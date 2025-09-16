package com.elmiraouy.command;

import com.elmiraouy.neo4j.services.InitDbByQuery;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
@CommandLine.Command(name = "InitDB", mixinStandardHelpOptions = true,
        description = "Creation des enregistrements dans la base de donnees")
public class InitDataBase implements Runnable{


    private final InitDbByQuery neo4jQueryService;

    public InitDataBase(InitDbByQuery neo4jQueryService) {
        this.neo4jQueryService = neo4jQueryService;
    }

    @Override
    public void run() {
        System.out.println("hello $$$$$$$$$$$$$$$$$$$$$$$$$");
        neo4jQueryService.InitDatabase();
        System.out.println("Base de données initialisée ");
    }

}
