/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venda.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author informatica
 */
public class Conexao {

    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    public void Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdvenda?zeroDateTimeBehavior=convertToNull", "root", "root");
        } catch (ClassNotFoundException cnfe) {

        } catch (SQLException sqle) {

        }

    }

    public void fechar() {
        try {
            if (cn != null) {

                if (cn.isClosed() == false) {

                    cn.close();
                }
            }
        } catch (SQLException sqle) {

        }
    }

}
