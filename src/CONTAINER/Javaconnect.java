/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTAINER;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author DOMINIC YEGON
 */
public class Javaconnect {
    
    Connection conn=null;
    public static Connection ConnectDB(){
    try{
    Connection conn=DriverManager.getConnection("jdbc:sqlite:Church Management System.sqlite");
    return conn;
    }catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    }
    return null;
    }
    
}
