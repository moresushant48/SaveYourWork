package io.moresushant48.model;

public class AnalyticsData {
	
	private long totalCustomers;
	
	private long totalFiles;
	
	private long totalFeedbacks;
	
	private long totalOnline;

	public AnalyticsData(long totalCustomers, long totalFiles, long totalFeedbacks, long totalOnline) {
		super();
		this.totalCustomers = totalCustomers;
		this.totalFiles = totalFiles;
		this.totalFeedbacks = totalFeedbacks;
		this.totalOnline = totalOnline;
	}

	public long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

	public long getTotalFiles() {
		return totalFiles;
	}

	public void setTotalFiles(long totalFiles) {
		this.totalFiles = totalFiles;
	}

	public long getTotalFeedbacks() {
		return totalFeedbacks;
	}

	public void setTotalFeedbacks(long totalFeedbacks) {
		this.totalFeedbacks = totalFeedbacks;
	}

	public long getTotalOnline() {
		return totalOnline;
	}

	public void setTotalOnline(long totalOnline) {
		this.totalOnline = totalOnline;
	}	
}
