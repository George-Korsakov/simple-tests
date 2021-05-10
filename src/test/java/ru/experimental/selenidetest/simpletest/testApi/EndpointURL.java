package ru.experimental.selenidetest.simpletest.testApi;

public enum EndpointURL {

    RANDOM("/random");
    String path;
    EndpointURL(String path) {this.path = path;}
    public String getPath(){return path;}
    public String addPath(String AdditionalPath) {return path + AdditionalPath;}

}

