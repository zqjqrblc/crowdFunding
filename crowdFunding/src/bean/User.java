package bean;

import java.util.List;

public class User {
	private String userName;
	private String password;
	private int amount;
	private List<Project> raisedProjectsList;
	private List<Project> investedProjectsList;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public List<Project> getRaisedProjectsList() {
		return raisedProjectsList;
	}

	public void setRaisedProjectsList(List<Project> raisedProjectsList) {
		this.raisedProjectsList = raisedProjectsList;
	}

	public List<Project> getInvestedProjectsList() {
		return investedProjectsList;
	}

	public void setInvestedProjectsList(List<Project> investedProjectsList) {
		this.investedProjectsList = investedProjectsList;
	}
}
