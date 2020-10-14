package model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="list")
public class taskListWrapper {
	
	private List<taskObject> list ;
	private String username;
	private String password;
	
	@XmlElement(name = "taskObject")
	public List<taskObject> getTasks(){
		return list;
	}
	
	public void setTasks(List<taskObject> list) {
		this.list = list;
	}
	
	@XmlElement(name = "username")
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@XmlElement(name = "password")
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
