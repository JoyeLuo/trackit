trackit
=======

Trackit tracks changes in a given folder using Git

Components :
TrackerTool - Auto-Commits changes to a git repository at configurable intervals
Reader - Reads commits to an Output Stream

\n
Using the tool :
\n
To start tracking your folder/files :
\n
Run TrackerTool with Parameter CommitInfo
\nrepoPath - The parent folder of the folder to be tracked. A .git repository will be created here.
\nfilePath - The path of the folder/file to be traced, relative to repoPath.

\n
To review versions of the tracked folder/file :

\nRun Reader with Parameter ReaderInfo
\nrepoPath  - Parent of the tracked folder. .git folder should be inside this.
\nfilePath - Path to the file to be read relative to repoPath
\nnumberOfPrevVersions - The number of file revisions to be read.
