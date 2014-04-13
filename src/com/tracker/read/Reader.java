package com.tracker.read;

import java.io.OutputStream;
import java.util.Iterator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

import com.tracker.GitUtil;

public class Reader {
	
	public static void read(ReaderInfo readerInfo, OutputStream outStream) throws Exception{
		Git gitClient = GitUtil.getGitClient(readerInfo.getRepoPath());   
		
		try{	
			LogCommand log = gitClient.log();
	    	Iterator<RevCommit> iter = log.call().iterator();
	        int count = 0;
	        while(iter.hasNext()){
	        	if((readerInfo.getNumberOfPrevVersions() != 0) && (count >= readerInfo.numberOfPrevVersions)){
	        		break;
	        	}
	        	count++;
	        	RevCommit commit = iter.next();
	        	String commitInfo = "\n\nAuthor : " + commit.getAuthorIdent().getName() 
	        			+" \nDate : " + commit.getAuthorIdent().getWhen() + "\n"; 
	            RevTree tree = commit.getTree();
	            TreeWalk treeWalk = new TreeWalk(gitClient.getRepository());
		        treeWalk.addTree(tree);
		        treeWalk.setRecursive(true);
		        treeWalk.setFilter(PathFilter.create(readerInfo.getFilePath()));
		        if (!treeWalk.next()) {
		        	continue;
		        }
		        ObjectId objectId = treeWalk.getObjectId(0);
		        ObjectLoader loader = gitClient.getRepository().open(objectId);
		        outStream.write(commitInfo.getBytes());
		        outStream.write(loader.getBytes());
	        }
		}catch(NoHeadException nhe){
			System.out.println("Repository not created yet : "  + readerInfo.getRepoPath());
		}finally{
	        gitClient.getRepository().close();
		}
	}
	
	
	

	
	public static void main(String[] args){
		try{
			String repoPath = "C:/develop/workspace/Trackit/newRepo";
			String folderName = "trackedFolder/file1.txt";
			ReaderInfo info = new ReaderInfo(repoPath, folderName, 3);
			Reader.read(info, System.out);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}


