package com.tracker.commit;

public class CommitInfo {
	
	private String repoPath;
	private String filePath;
	private String commitComment;
	
	public CommitInfo(String repoPath, String filePath){
		this.repoPath = repoPath;
		this.filePath = filePath;
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
	public String getCommitComment() {
		return commitComment;
	}
	public void setCommitComment(String commitComment) {
		this.commitComment = commitComment;
	}

}
