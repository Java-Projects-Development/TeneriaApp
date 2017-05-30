/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenerianoe.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author admin
 */
@ManagedBean(name = "Backup")
public class Backup implements Serializable {

    ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

    private String txtPath = "C:\\Users\\elgor\\Desktop";
    private String lblMessage;

    public void crearBackup() {
        if (txtPath.equals("")) {
            lblMessage = ("Please choose path to save!");
        } else {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            //File file = new File(dateFormat.format(now));
            String strFilename = dateFormat.format(now);

            long nowLong = now.getTime();
            //String strFilename;
            //strFilename = nowLong.toString();
            //strFilename = String.valueOf(nowLong);
            System.out.println(strFilename);
            //  String command = "C:/Program Files/MySQL/MySQL Server 5.0/bin/mysqldump -u(db user name) -p(db password) --add-drop-database -B (db name) -r " + "\"" + txtPath.getText().toString() + "\\" + strFilename + ".sql\"";
            String command = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump -uroot -padmin --add-drop-database -B teneria -r " + "\"" + txtPath.toString() + "\\" + strFilename + ".sql\"";
            System.out.println(command);
            Process p = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec(command);

                int processComplete = p.waitFor();

                if (processComplete == 0) {

                    // System.out.println("<html><font color='green'>Backup created successfully!</font></html>");
                    lblMessage = "Backup created successfully to " + txtPath.toString() + "\\" + strFilename + ".sql";

                } else {
                    lblMessage = "Could not create the backup";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void cargarBackup(){
    
    
    }
}
