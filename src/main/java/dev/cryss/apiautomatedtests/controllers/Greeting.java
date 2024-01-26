package dev.cryss.apiautomatedtests.controllers;

public class Greeting {

    private Long id;
    private String content;
    public Greeting(long counter, String name) {
        this.id = counter;
        this.content = name;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
