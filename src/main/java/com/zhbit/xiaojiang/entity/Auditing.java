/**
 * FileName: Auditing
 * Author:   小江
 * Date:     2020/2/8 23:06
 * History:
 */
package com.zhbit.xiaojiang.entity;

public class Auditing {
	private String auditingId;
	private String projectName_tmp;
	private String projectContent_tmp;
	private String time_start_tmp;
	private String time_end_tmp;
	private String leader_tmp;
	private String phone_tmp;
	private Float inputMoney_tmp;
	private Float outputMoney_tmp;
	private Float cost_tmp;
	private String costDetail_tmp;
	private int auditingStatus;

	public String getAuditingId() {
		return auditingId;
	}

	public void setAuditingId(String auditingId) {
		this.auditingId = auditingId;
	}

	public String getProjectName_tmp() {
		return projectName_tmp;
	}

	public void setProjectName_tmp(String projectName_tmp) {
		this.projectName_tmp = projectName_tmp;
	}

	public String getProjectContent_tmp() {
		return projectContent_tmp;
	}

	public void setProjectContent_tmp(String projectContent_tmp) {
		this.projectContent_tmp = projectContent_tmp;
	}

	public String getTime_start_tmp() {
		return time_start_tmp;
	}

	public void setTime_start_tmp(String time_start_tmp) {
		this.time_start_tmp = time_start_tmp;
	}

	public String getTime_end_tmp() {
		return time_end_tmp;
	}

	public void setTime_end_tmp(String time_end_tmp) {
		this.time_end_tmp = time_end_tmp;
	}

	public String getLeader_tmp() {
		return leader_tmp;
	}

	public void setLeader_tmp(String leader_tmp) {
		this.leader_tmp = leader_tmp;
	}

	public String getPhone_tmp() {
		return phone_tmp;
	}

	public void setPhone_tmp(String phone_tmp) {
		this.phone_tmp = phone_tmp;
	}

	public Float getInputMoney_tmp() {
		return inputMoney_tmp;
	}

	public void setInputMoney_tmp(Float inputMoney_tmp) {
		this.inputMoney_tmp = inputMoney_tmp;
	}

	public Float getOutputMoney_tmp() {
		return outputMoney_tmp;
	}

	public void setOutputMoney_tmp(Float outputMoney_tmp) {
		this.outputMoney_tmp = outputMoney_tmp;
	}

	public Float getCost_tmp() {
		return cost_tmp;
	}

	public void setCost_tmp(Float cost_tmp) {
		this.cost_tmp = cost_tmp;
	}

	public String getCostDetail_tmp() {
		return costDetail_tmp;
	}

	public void setCostDetail_tmp(String costDetail_tmp) {
		this.costDetail_tmp = costDetail_tmp;
	}

	public int getAuditingStatus() {
		return auditingStatus;
	}

	public void setAuditingStatus(int auditingStatus) {
		this.auditingStatus = auditingStatus;
	}
}