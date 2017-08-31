package beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String name;
	private String branch;
	private int branchID;
	private String password;
	private int divisionID;
	private String divisionName;
	private Date insertDate;
	private Date updateDate;

	public int getId(){
		return id;
	}

	public void setID(int id){
		this.id = id;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getBranchID(){
		return branchID;
	}

	public void setBranchID(int branchID){
		this.branchID = branchID;
	}

	public String getBranchName(){
		return branch;
	}

	public void setBranchName(String email){
		this.branch = email;
	}


	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public int getDivisionID(){
		return divisionID;
	}

	public void setDivisionID(int divisionID){
		this.divisionID = divisionID;
	}


	public String getDivisionName(){
		return divisionName;
	}

	public void setDivisionName(String divisionName){
		this.divisionName = divisionName;
	}

	public Date getInsertDate(){
		return insertDate;
	}

	public void setInsertDate(Date insertDate){
		this.insertDate = insertDate;
	}

	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
}
