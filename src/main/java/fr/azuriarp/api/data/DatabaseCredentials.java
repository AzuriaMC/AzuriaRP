package fr.azuriarp.api.data;

public class DatabaseCredentials {

    private String host;
    private String username;
    private String password;
    private String dbName;
    private int port;

    public DatabaseCredentials(String host, String username, String password, String dbName, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.dbName = dbName;
        this.port = port;
    }

    public String toURI() {
        return "jdbc:mysql://" + host + ":" + port + "/" + dbName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
