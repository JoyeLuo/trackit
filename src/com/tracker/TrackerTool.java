package com.tracker;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.tracker.commit.CommitInfo;
import com.tracker.tasks.CommitTask;

/**
 *  
 * Tracker Tool runs at configurable intervals and commits contents of the tracked folder to a GIT repository.
 * 
 * The Git repository is created locally, in the same parent folder as the one being tracked.
 *  
 *  
 * @author gargi
 *  
 */
public class TrackerTool {
	// Path to the Parent folder. Git repository gets created here.
	String repoPath;
	// Name of the folder to be tracked.
	String folderName;
	// configure this!
	int snapshotIntervalinSeconds = 20;  //Make it 60*60 (1 hr)
	ScheduledExecutorService executor;
	
	public void init(String repoPath, String folderName){
		this.repoPath = repoPath;
		this.folderName = folderName;
	}
	
	public void start(){
		System.out.println("Starting Tracker Tool");
		executor = new ScheduledThreadPoolExecutor(1);
		CommitInfo commitInfo = new CommitInfo(repoPath, folderName );
		commitInfo.setCommitComment("Tracker Tool Auto Commit");
		System.out.println("Scheduling Auto Commits with interval of "   + snapshotIntervalinSeconds + " seconds");
		executor.scheduleWithFixedDelay(new CommitTask(commitInfo), 5, snapshotIntervalinSeconds, TimeUnit.SECONDS);
	}
	
	public void stop(){
		System.out.println("Shutting Down Tracker tool");
		if(executor != null){
			executor.shutdown();
		}
	}
	
	public static void main(String[] args){
		// Change this!
		String repoPath = "C:/develop/workspace/Trackit/newRepo";
		String folderName = "trackedFolder";
		
		TrackerTool trackerTool = new TrackerTool();
		trackerTool.init(repoPath, folderName);
		trackerTool.start();
	}

}
