package com.webarc.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

/**
 * Responsável em migrar os schemas.
 */
public final class DatabaseMigration {

    private static final Logger LOGGER = LogManager.getLogger();

    private DatabaseMigration() {
        // static class
    }

    /**
     * Realiza a migração de um schema.
     * 
     * @param databaseSchemaType o tipo do schema que será migrado.
     * @param schema o schema que será migrado.
     */
    public static void migrateTenantSchema(String databaseSchemaType, String schema) {
        DatabaseSchemaType schemaType = DatabaseSchemaType.valueOf(databaseSchemaType);
        migrateTenantSchema(schemaType, schema);
    }

    /**
     * Realiza a migração de um schema.
     * 
     * @param databaseSchemaType o tipo do schema que será migrado.
     * @param schema o schema que será migrado.
     */
    public static void migrateTenantSchema(DatabaseSchemaType databaseSchemaType, String schema) {
        LOGGER.info("Migrando tenant {}", schema);
        Flyway flyway = new Flyway();
        flyway.setDataSource(BackendConfig.getDatabaseUrl(), BackendConfig.getDatabaseUser(), BackendConfig.getDatabasePassword());
        flyway.setLocations(databaseSchemaType.getLocation());
        flyway.setSqlMigrationPrefix("V");
        flyway.setBaselineOnMigrate(true);
        flyway.setSchemas(schema);
        flyway.setOutOfOrder(true);
        flyway.repair();
        flyway.migrate();
    }

    /**
     * Migra todos os schemas.
     * 
     * @throws Exception se ocorrer algum erro na migração.
     */
    public static void migrateAllSchemas() throws SQLException {
        migrateTenantSchema(DatabaseSchemaType.PUBLIC, "public");
        migrateTenantSchema(DatabaseSchemaType.QUARTZ_SCHEDULER, "quartz");
        getAllSchemas().stream().forEach(schema -> migrateTenantSchema(DatabaseSchemaType.TENANTS, schema));
    }

    private static Collection<String> getAllSchemas() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", BackendConfig.getDatabaseUser());
        props.setProperty("password", BackendConfig.getDatabasePassword());
        List<String> schemas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(BackendConfig.getDatabaseUrl(), props);
                PreparedStatement preparedStatement = conn.prepareStatement("select schema_name from public.clientes");
                ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                String tenantName = rs.getString("tenant");
                if (TenantDAO.tenantHaveDatabase(tenantName)) {
                    schemas.add(tenantName);
                }
            }
            return schemas;
        }
    }
}

/**
 * Tipos de schema da base de dados.
 */
enum DatabaseSchemaType {

        /**
         * Caminho dos cripts para o schema publico.
         */
        PUBLIC("filesystem:script/migration/public"),
        /**
         * Caminho dos scripts para o schema de cada cliente.
         */
        TENANTS("filesystem:script/migration/tenants"),
        /**
         * Caminho dos scripts do schema do Quartz.
         */
        QUARTZ_SCHEDULER("filesystem:script/migration/quartz-scheduler");

    private String location;

    DatabaseSchemaType(String location) {
        this.location = location;
    }

    /**
     * Retorna a localização dos scripts.
     * 
     * @return a localização dos scripts.
     */
    public String getLocation() {
        return location;
    }
}

