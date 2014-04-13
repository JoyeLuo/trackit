package com.tracker;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.DiffCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.CanceledException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.DetachedHeadException;
import org.eclipse.jgit.api.errors.InvalidConfigurationException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.lib.BaseRepositoryBuilder;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
//import org.eclipse.jgit.storage.file.FileRepository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class TestAPI {

    private String localPath; 
    private String remotePath;
    private Repository localRepo;
    private Git git;
    
   // @Test
    public void testCreate() throws IOException {
       // Repository newRepo = new FileRepository(localPath + ".git");
    	if(localRepo == null){
    		localRepo = FileRepositoryBuilder.create(new File(localPath + ".git"));
    	}
    	localRepo.create();
    }

    //@Test    
    public void testClone() throws Exception, IOException, NoFilepatternException {     
        Git.cloneRepository() 
           .setURI(remotePath)
           .setDirectory(new File(localPath))
          
           .call();  
    }

    //@Test
    public void testAdd() throws Exception, IOException, NoFilepatternException { 
        File myfile = new File(localPath + "/myfile");
        myfile.createNewFile();
        git.add()
           .addFilepattern("myfile")
           .call();
    }

    //@Test
    public void testCommit() throws Exception, IOException, NoHeadException, NoMessageException,     ConcurrentRefUpdateException, JGitInternalException, WrongRepositoryStateException {
        git.commit()
           .setMessage("Added myfile")
           .call();
    }

    //@Test
    public void testPush() throws Exception, IOException, JGitInternalException, InvalidRemoteException {     
        git.push()
           .call();
    }    

    //@Test    
    public void testTrackMaster() throws Exception, IOException, JGitInternalException, RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException {     
        git.branchCreate() 
           .setName("master")
           .setUpstreamMode(SetupUpstreamMode.SET_UPSTREAM)
           .setStartPoint("origin/master")
           .setForce(true)
           .call();
    }

    //@Test
    public void testPull() throws Exception, IOException, WrongRepositoryStateException, InvalidConfigurationException, DetachedHeadException, InvalidRemoteException, CanceledException, RefNotFoundException, NoHeadException {
        git.pull()
           .call();
    }
}

