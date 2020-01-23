package fr.azuriarp.api.data;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private DatabaseCredentials credentials;
    private BasicDataSource dataSource;

    public DatabaseManager(DatabaseCredentials credentials) {
        this.credentials = credentials;

        this.setupDataSource();
    }

    private void setupDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername(credentials.getUsername());
        dataSource.setPassword(credentials.getPassword());
        dataSource.setUrl(credentials.toURI());

        this.dataSource = dataSource;
    }

    public void closePool() {
        try {
            this.dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (this.dataSource == null) {
            this.setupDataSource();
        }

        return this.dataSource.getConnection();
    }
}

