package com.tracker.read;

public class ReaderInfo {
	String repoPath;
	String filePath;
	int numberOfPrevVersions;
	String contentOfInterest;
	
	public ReaderInfo(String repoPath, String filePath, int numberOfPrevVersions){
		this.repoPath = repoPath;
		this.filePath = filePath;
		this.numberOfPrevVersions = numberOfPrevVersions;
	}
	public String getRepoPath() {
		return repoPath;
	}
	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getNumberOfPrevVersions() {
		return numberOfPrevVersions;
	}
	public void setNumberOfPrevVersions(int numberOfPrevVersions) {
		this.numberOfPrevVersions = numberOfPrevVersions;
	}
	public String getContentOfInterest() {
		return contentOfInterest;
	}
	public void setContentOfInterest(String contentOfInterest) {
		this.contentOfInterest = contentOfInterest;
	}
	
	
}
