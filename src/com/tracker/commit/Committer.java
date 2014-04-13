package com.tracker.commit;

import java.util.Date;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.StatusCommand;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.revwalk.RevCommit;

import com.tracker.GitUtil;

public class Committer {
	

	public static boolean isModified(CommitInfo commitInfo) throws Exception {
		Git gitClient = GitUtil.getGitClient(commitInfo.getRepoPath());
		StatusCommand statusCommand = gitClient.status();
		statusCommand.addPath(commitInfo.getFilePath());
		Status status = statusCommand.call();
		System.out.println("Modified : " + status.getModified());
		System.out.println("Changed : " + status.getChanged());
		System.out.println("Added : " + status.getAdded());
		System.out.println("Uncomitted Changes : " + status.getUncommittedChanges());
		return ((!status.getModified().isEmpty()) 
				|| (!status.getChanged().isEmpty())
				|| (!status.getRemoved().isEmpty())
				|| (!status.getMissing().isEmpty())
				);
	}
	

	public static void commit(CommitInfo commitInfo, boolean forceCommit){
		try{
			try{
				commitOnly(commitInfo, forceCommit);
			}catch(NoHeadException ex){
				GitUtil.createRepo(commitInfo.getRepoPath());
				commitOnly(commitInfo, forceCommit);
			}
			
		}catch(Exception e){
			System.out.println("Commit Failed for File Pattern :  "+commitInfo.getFilePath()
					+" in Repository at "+commitInfo.getRepoPath()+"; Reason : " + e.getMessage());
			e.printStackTrace();
			return;
		}

	}
	
	static void commitOnly(CommitInfo commitInfo, boolean forceCommit) throws Exception{
		boolean shouldCommit = false;
		if(forceCommit){
			shouldCommit = true;
		}else{
			shouldCommit = isModified(commitInfo);
		}
		if(!shouldCommit){
			System.out.println("No change to commit.");
			return;
		}	
		Git gitClient = GitUtil.getGitClient(commitInfo.getRepoPath());
			AddCommand add = gitClient.add().addFilepattern(commitInfo.getFilePath());
			add.call();
			CommitCommand committ = gitClient.commit().setMessage(commitInfo.getCommitComment() + ";  TimeStamp : " + new Date());
			RevCommit revCommit = committ.call();
			System.out.println("Committed File Pattern : " + commitInfo.getFilePath() + "; Repository Path : " + commitInfo.getRepoPath());
			System.out.println("Commit Info : \nID=" +  revCommit.getId() 
					+ "; \nTime=" + revCommit.getCommitTime() 
					+ "; \nAuthor=" + revCommit.getAuthorIdent().getName());
			
	}
	
	public static void main(String[] args){
		try{
		// Change this!
		String repoPath = "C:/develop/workspace/Trackit/newRepo";
		String folderName = "trackedFolder";
		
		CommitInfo info = new CommitInfo(repoPath, folderName);
		info.setCommitComment("Testing Committer API...");
		Committer.commit(info, false);
		//Committer.isModified(info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
