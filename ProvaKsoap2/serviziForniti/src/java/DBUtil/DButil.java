/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manuel
 */
public class DButil {

    private static final Logger LOGGER = Logger.getLogger(DButil.class.getName());
    static final String NOME_CLASSE_DRIVER_DB = "org.sqlite.JDBC"; // deve essere presente nelle Libraries
    static final String DRIVER_DB = "jdbc:sqlite:";
    static final String PATH_DB = "C:\\Users\\Manuel\\Desktop\\serviziForniti\\Databases\\";
    static final String NOME_DB = "databaseSocial.db";
    static final String USER_DB = "";
    static final String PWD_DB = "";
    static final String URL_DB = DRIVER_DB + PATH_DB + NOME_DB;

    static Connection conn = null;

    public static Connection getConnection() { // Design Pattern SINGLETON 
        if (conn == null) {
            try {
                // Class.forName() implicitamente carica a run-time il driver
                // del database utilizzato
                Class.forName(NOME_CLASSE_DRIVER_DB);
                conn = DriverManager.getConnection(URL_DB, USER_DB, PWD_DB);
            } catch (ClassNotFoundException | SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return conn;
    }

    /*
		public static String update(String nomeTabella, ArrayList condizione, ArrayList campi) {
		String strSQL = "UPDATE tblGenere SET genere = ?, note = ? WHERE idGenere = ?";
		PreparedStatement stm = null;
		int rowsAffected = 0;
		String msg = "";
		try {
			stm = getConnection().prepareStatement(strSQL);
			stm.setString(1, g.getGenere());
			stm.setString(2, g.getNote());
			stm.setLong(3, g.getIdGenere());
			rowsAffected = stm.executeUpdate();
		} catch (SQLException ex) {
			msg = ex.toString() + "\n Righe aggiornate: " + rowsAffected;
			LOGGER.log(Level.SEVERE, msg, ex);
		}
		return msg;
	}
     */
    public static ArrayList<String> getprimaryKeys(String nomeTabella) {
        ArrayList<String> nomiPK = null;
        String strSQL = "SELECT sql FROM sqlite_master WHERE name=?";
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(strSQL);
            stm.setString(1, nomeTabella);
            ResultSet rs = stm.executeQuery();
            String defTabella = rs.getString("sql");
            Pattern patPKsingle = Pattern.compile("CREATE TABLE\\s+\\w+\\s+\\(\\s+(\\w+)\\s+.*PRIMARY KEY");
            Matcher mtcPKsingle = patPKsingle.matcher(defTabella);
            Boolean trovataPKsingle = mtcPKsingle.find();
            if (trovataPKsingle) {
                nomiPK = new ArrayList();
                nomiPK.add(mtcPKsingle.group(1));
            } else {
                Pattern patPKmulti = Pattern.compile("PRIMARY KEY\\s*\\(\\s*([\\w, ]+\\w)\\)");
                Matcher mtcPKmulti = patPKmulti.matcher(defTabella);
                Boolean trovataPKmulti = mtcPKmulti.find();
                if (trovataPKmulti) {
                    nomiPK = new ArrayList<>(Arrays.asList((mtcPKmulti.group(1)).split("\\s*,\\s*")));
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return nomiPK;
    }

    public static boolean isCampoPK(String nomeCampo, String nomeTabella) {
        ArrayList<String> nomiPK = getprimaryKeys("tblGenere");
        for (String nomePK : nomiPK) {
            if (nomeCampo.equals(nomePK)) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getNomiCampi(Class c) {
        Field[] campi = c.getDeclaredFields();
        ArrayList<String> ris = new ArrayList<>();
        for (int i = 0; i < campi.length; i++) {
            String nomeCampo = campi[i].getName();
            //si devono eliminare tutte le variabili di classe
            if (nomeCampo.equals("LOGGER")) {
                continue;
            }
            ris.add(nomeCampo);
        }
        return ris;
    }

    public static String getEmail(String s) {
        String sql = "select email"
                + "  from UTENTE"
                + "  where UTENTE.cognomeNome = ?";
        String ris = "";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ris += "[" + rs.getString("email") + "]  ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ris;
    }
}
