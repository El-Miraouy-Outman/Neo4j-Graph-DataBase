package com.elmiraouy.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
@CommandLine.Command(name="QueryApi", mixinStandardHelpOptions = true ,description = "test application with api ")
public class TestQueryApi implements Runnable {
    @Override
    public void run() {

    }
}
