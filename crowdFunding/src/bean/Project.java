package bean;

import java.util.Date;

public class Project {
	private String projectName;
	private String raisePersonName;
	private int raisedAmount;
	private int maxAmount;
	private int minAmount;
	private Date startDate;
	private Date deadLine;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRaisePersonName() {
		return raisePersonName;
	}

	public void setRaisePersonName(String raisePersonName) {
		this.raisePersonName = raisePersonName;
	}

	public int getRaisedAmount() {
		return raisedAmount;
	}

	public void setRaisedAmount(int raisedAmount) {
		this.raisedAmount = raisedAmount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

}
