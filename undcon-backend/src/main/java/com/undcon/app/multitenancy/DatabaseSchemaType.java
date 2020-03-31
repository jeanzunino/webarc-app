package com.undcon.app.multitenancy;

/**
 * Tipos de schema da base de dados.
 */
enum DatabaseSchemaType {

        /**
         * Caminho dos cripts para o schema publico.
         */
        PUBLIC("script/migration/public"),
        /**
         * Caminho dos scripts para o schema de cada cliente.
         */
        TENANTS("script/migration/tenants");

    private String location;

    DatabaseSchemaType(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}