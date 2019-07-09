package com.webarc.app;

public class BackendConfig {

	
	public static String getDatabaseUser() {
		return "postgres";
	}

	public static String getDatabaseUrl() {
		return "jdbc:postgresql://localhost:5432/psocial";
	}

	public static String getDatabasePassword() {
		return "198706";
	}

}
