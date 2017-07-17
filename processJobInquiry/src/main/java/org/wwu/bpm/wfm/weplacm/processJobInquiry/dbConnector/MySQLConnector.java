package org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 * This class sets up a connection to a MySQL-DB.
 * Scheme has to be called "weplacm", username and
 * password need to be "root", "root".
 * 
 * Use MySQLConnector.getConnection() to get an 
 * instance.
 * 
 * When calling getConnection, the tables candidates,
 * skills and candidates_skill_matching will be 
 * generated automatically, as well as a set of 5
 * candidates, 3 skills and 3 links between candidates
 * and skills. If this should not happen, change the
 * variable autogenerate to false.
 * @author oliver
 *
 */
public class MySQLConnector {
	private static String dbHost="localhost";
	private static String dbPort="3306";
	private static String dbName="weplacm";
	private static String dbUser="root";
	private static String dbPass="root";
	private static boolean autogenerate = true;

	private static Connection con;

	private MySQLConnector(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(autogenerate){
			createMissingDBs();
			createDefaultEntries();
		}
	}

	public static Connection getConnection(){
		if(con==null)
			new MySQLConnector();
		return con;
	}

	private static void createMissingDBs(){
		Vector<String> dbNames = new Vector<String>();
		try {
			ResultSet rs = getConnection().getMetaData().getTables(getConnection().getCatalog(), null, null, null);
			int i=0;
			while(rs.next()){
				dbNames.add(rs.getString(3));
			}

			if(!dbNames.contains("candidates")){
				String statement = 
						"CREATE TABLE `weplacm`.`candidates` ("
								+"`id_candidate` INT NOT NULL AUTO_INCREMENT,"
								+"`name` VARCHAR(45) NULL,"
								+"`email` VARCHAR(45) NULL,"
								+"PRIMARY KEY (`id_candidate`),"
								+"UNIQUE INDEX `email_UNIQUE` (`email` ASC));";
				Statement stmt = getConnection().createStatement();
				stmt.executeUpdate(statement);
			} 
			if(!dbNames.contains("skills")){
				String statement = 
						"CREATE TABLE `weplacm`.`skills` ("
								+"`id_skill` INT NOT NULL AUTO_INCREMENT,"
								+"`name` VARCHAR(45) NULL,"
								+"PRIMARY KEY (`id_skill`),"
								+"UNIQUE INDEX `id_skill_UNIQUE` (`id_skill` ASC));";
				Statement stmt = getConnection().createStatement();
				stmt.executeUpdate(statement);
			}
			if(!dbNames.contains("candidates_skill_matching")){
				String statement = 
						"CREATE TABLE `weplacm`.`candidates_skill_matching` ("
								+"`candidate_id` INT NOT NULL,"
								+"`skill_id` INT NOT NULL,"
								+"PRIMARY KEY (`candidate_id`, `skill_id`),"
								+"INDEX `skill_id_fk_idx` (`skill_id` ASC),"
								+"CONSTRAINT `candidate_id_fk`"
								+" FOREIGN KEY (`candidate_id`)"
								+" REFERENCES `weplacm`.`candidates` (`id_candidate`)"
								+" ON DELETE NO ACTION"
								+" ON UPDATE NO ACTION,"
								+"CONSTRAINT `skill_id_fk`"
								+" FOREIGN KEY (`skill_id`)"
								+" REFERENCES `weplacm`.`skills` (`id_skill`)"
								+" ON DELETE NO ACTION"
								+" ON UPDATE NO ACTION);";
				Statement stmt = getConnection().createStatement();
				stmt.executeUpdate(statement);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void createDefaultEntries(){
		ArrayList<String> usernames = new ArrayList<String>();
		ArrayList<String> skills = new ArrayList<String>();
		usernames.add("Hans");
		usernames.add("Jürgen");
		usernames.add("Peter");
		usernames.add("Werner");
		usernames.add("Günther");

		skills.add("MSOffice");
		skills.add("Java");
		skills.add("Haskell");

		if(checkIfTableIsEmpty("candidates")){
			for(String name : usernames){
				try {
					PreparedStatement stmt = getConnection().prepareStatement(
							"INSERT INTO `weplacm`.`candidates` (`name`, `email`) VALUES (?, ?);"
							);
					stmt.setString(1, name);
					stmt.setString(2, (name+"@test.de"));
					stmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
		if(checkIfTableIsEmpty("skills")){
			for(String skill : skills){
				try {
					PreparedStatement stmt = getConnection().prepareStatement(
							"INSERT INTO `weplacm`.`skills` (`name`) VALUES (?);"
							);
					stmt.setString(1, skill);
					stmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}

		if(checkIfTableIsEmpty("candidates_skill_matching")){
			for(int i=1; i<(Math.min(usernames.size(), skills.size())+1); i++){
				try {
					PreparedStatement stmt = getConnection().prepareStatement(
							"INSERT INTO `weplacm`.`candidates_skill_matching` (`candidate_id`, `skill_id`) VALUES (?, ?);"
							);
					stmt.setInt(1, i);
					stmt.setInt(2, i);
					stmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private static boolean checkIfTableIsEmpty(String tablename){
		try {
			PreparedStatement stmt = getConnection().prepareStatement(
					"SELECT * FROM weplacm."+tablename					
					);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return false;
			else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}