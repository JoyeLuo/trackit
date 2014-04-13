package com.tracker;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.BaseRepositoryBuilder;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitUtil {
	
	public static void createRepo(String repoPath) throws Exception{
		BaseRepositoryBuilder builder = new BaseRepositoryBuilder<>();
        builder.setWorkTree(new File( repoPath));
        FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder();
        repoBuilder.setWorkTree(new File( repoPath));
        Repository repo =  repoBuilder.build();
        System.out.println("Creating Git Repository at " + repoPath);
        repo.create();
        Git.init();
  }
	
	public static Git getGitClient(String repoPath) throws Exception{
		BaseRepositoryBuilder builder = new BaseRepositoryBuilder<>();
        builder.setWorkTree(new File( repoPath));
        FileRepositoryBuilder repoBuilder = new FileRepositoryBuilder();
        repoBuilder.setWorkTree(new File( repoPath));
        Repository repo =  repoBuilder.build();
    	Git git = new Git( repo);
    	Git.init();
    	return git;
	}
	

}
