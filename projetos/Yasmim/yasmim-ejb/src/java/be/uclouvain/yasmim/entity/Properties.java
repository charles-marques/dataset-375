package be.uclouvain.yasmim.entity;

public enum Properties {
    REPOSITORY_PATH("repositoryPath");

    private String name;

    Properties(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}