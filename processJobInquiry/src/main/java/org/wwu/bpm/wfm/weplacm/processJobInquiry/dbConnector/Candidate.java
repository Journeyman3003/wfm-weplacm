package org.wwu.bpm.wfm.weplacm.processJobInquiry.dbConnector;

import java.util.ArrayList;
import java.util.Collection;

public class Candidate {
	private String email, name;
	private ArrayList<Skill> skills;
	private int dbID;
	public Candidate(){
		skills = new ArrayList<Skill>();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Collection<Skill> getSkills(){
		return skills;
	}
	
	public void setSkills(ArrayList<Skill> skills){
		this.skills=skills;
	}
	
	public int getCandidateID(){
		return dbID;
	}
	
	public void setCandidateID(int id){
		dbID=id;
	}
	
	public void addSkill(String skillName){
		skills.add(new Skill(skillName));
	}
	
	public void addSkill(Skill skill){
		skills.add(skill);
	}
	
	public boolean hasSkill(String name){
		return skills.stream().filter(s -> s.getName().equals(name)).count()>0;
	}
	
	public boolean hasSkill(Skill skill){
		return skills.stream().filter(s -> s.getName().equals(skill.getName())).count()>0;
	}
	
	public void removeSkill(Skill skill){
		skills.removeIf(s -> skill.toString().equals(s.toString()));
	}
	
	public void removeSkill(String skillName){
		skills.removeIf(skill -> skill.getName().equals(skill.getName()));
	}
}