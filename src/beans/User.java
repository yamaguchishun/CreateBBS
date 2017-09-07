package beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String name;
	private int branchID;
	private int divisionID;
	private String password;
	private int isWorking;
	private Date insertDate;
	private Date updateDate;

	public int getId(){
		return id;
	}

	public void setId(int id){
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

	public int getBranchId(){
		return branchID;
	}

	public void setBranchId(int branchID){
		this.branchID = branchID;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public int getDivisionId(){
		return divisionID;
	}

	public void setDivisionId(int divisionID){
		this.divisionID = divisionID;
	}

	public int getIsWorking(){
		return isWorking;
	}

	public void setIsWorking(int isworking){
		this.isWorking = isworking;
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
