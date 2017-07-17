package org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector;

public class Skill {
	private String name;
	private int dbID;
	public Skill(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(){
		this.name=name;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + "]";
	}
	
	public int getDBID(){
		return dbID;
	}
	
	public void setDBID(int dbID){
		this.dbID=dbID;
	}
}
