package beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;


	private int id;
	private int userId;
	private int postId;
	private int branchId;
	private int divisionId;
	private String text;
	private Date insertDate;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postID) {
		this.postId = postID;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchID) {
		this.branchId = branchID;
	}

	public int getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(int divisionID) {
		this.divisionId = divisionID;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userID) {
		this.userId = userID;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
