package com.mycompany.projetointegrador;

import java.sql.Connection;

import java.sql.DriverManager;

public class ConexaoBD {
    private static String host = "banco-pi-banco-de-dados-pi.l.aivencloud.com";
    private static String porta = "26247";
    private static String db = "banco_PI";
    private static String usuario = "avnadmin";
    private static String senha = "AVNS_lMl_1mqdHp4be6dwoYp";

    public static Connection obterConexao () throws Exception{
        String url = String.format(
        "jdbc:mysql://%s:%s/%s",
            host,
            porta,
            db
        );
        return DriverManager.getConnection(url, usuario, senha);
    }
}
