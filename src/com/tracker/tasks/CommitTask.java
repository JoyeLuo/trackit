package com.tracker.tasks;

import com.tracker.commit.CommitInfo;
import com.tracker.commit.Committer;

public class CommitTask implements Runnable{
	volatile CommitInfo commitInfo;
	public CommitTask(CommitInfo commitInfo){
		this.commitInfo = commitInfo;
	}
	public void run(){
		if(commitInfo == null){
			System.out.println("Commit Info is null. Nothing to commit.");
			return;
		}
		Committer.commit(commitInfo, false);
	}
}
