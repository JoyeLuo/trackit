trackit
=======

Auto-Versioning tool to track changes in any folder or file.

Uses Git to track file revisions. Git repository is created and Auto-Commits made at configurable intervals.

(Note : This is a rudimentary initial cut involving running Java files manually)


Components :

com.tracker.TrackerTool - Auto-Commits changes to a git repository at configurable intervals

com.tracker.read.Reader - Reads commits to an Output Stream



Using the tool :

To start tracking your folder/files :

Run com.tracker.TrackerTool with Parameter CommitInfo

repoPath - The parent folder of the folder to be tracked. A .git repository will be created here.

filePath - The path of the folder/file to be traced, relative to repoPath.



To review versions of the tracked folder/file :


Run com.tracker.read.Reader with Parameter ReaderInfo

repoPath  - Parent of the tracked folder. .git folder should be inside this.

filePath - Path to the file to be read relative to repoPath

numberOfPrevVersions - The number of file revisions to be read.


Dependencies :

Git needs to be installed. 

The jar files in lib folder need to be in the classpath.

Built with Java 7
