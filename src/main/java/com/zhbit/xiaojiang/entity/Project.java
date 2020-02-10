/**
 * FileName: Project
 * Author:   小江
 * Date:     2020/2/4 15:35
 * History:
 */
package com.zhbit.xiaojiang.entity;

public class Project {

	private String projectId;
	private String projectName;
	private String projectContent;
	private String time_start;
	private String time_end;
	private String leader;
	private String phone;
	private Float inputMoney;
	private Float outputMoney;
	private Float cost;
	private String costDetail;
	private Float process;

	private Document document;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectContent() {
		return projectContent;
	}

	public void setProjectContent(String projectContent) {
		this.projectContent = projectContent;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Float getInputMoney() {
		return inputMoney;
	}

	public void setInputMoney(Float inputMoney) {
		this.inputMoney = inputMoney;
	}

	public Float getOutputMoney() {
		return outputMoney;
	}

	public void setOutputMoney(Float outputMoney) {
		this.outputMoney = outputMoney;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public String getCostDetail() {
		return costDetail;
	}

	public void setCostDetail(String costDetail) {
		this.costDetail = costDetail;
	}

	public Float getProcess() {
		return process;
	}

	public void setProcess(Float process) {
		this.process = process;
	}
}