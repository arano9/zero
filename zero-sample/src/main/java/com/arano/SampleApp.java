package com.arano;

import com.arano.annotation.ZeroComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.HelloService;
import service.HelloServiceB;

import java.util.HashMap;
import java.util.Map;

/**
 * @author arano
 * @since 2021/6/10 10:34
 */
@ZeroComponentScan
@SpringBootApplication
public class SampleApp implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SampleApp.class, args);
    }

    @Autowired
    HelloService helloComponent;
    @Autowired
    HelloServiceB helloServiceB;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(helloComponent.hello("arano"));
        HelloService.T t = new HelloService.T();
        t.setA("GO");
        t.setB("GOGO");
        System.out.println(helloComponent.echo(t));
        Map<String, String> map = new HashMap<>();
        map.put("refA", "valA");
        map.put("refB", "valB");
        System.out.println(helloServiceB.test(map, "paramA", "paramB"));

    }
}
