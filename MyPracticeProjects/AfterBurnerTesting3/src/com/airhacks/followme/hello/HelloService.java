package com.airhacks.followme.hello;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
public class HelloService
{
	@Inject
	private String message;

    @PostConstruct
    public void init() {
        System.out.println("Initializing");
    }

    public void sayHelloTo(String name) {
        System.out.println(message + " " + name);
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up!");
    }
}
