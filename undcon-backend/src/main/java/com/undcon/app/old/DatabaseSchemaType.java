package com.undcon.app.old;

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

    public String getLocation() {
        return location;
    }
}