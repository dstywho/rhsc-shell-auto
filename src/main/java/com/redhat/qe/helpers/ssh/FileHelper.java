package com.redhat.qe.helpers.ssh;

import com.redhat.qe.helpers.utils.AbsolutePath;
import com.redhat.qe.ssh.ExecSshSession;
import com.redhat.qe.ssh.ExecSshSession.Response;

public class FileHelper {
	
	public Response createFile(ExecSshSession session, AbsolutePath path, String content ){
		StringBuffer command = new StringBuffer();
		command.append("cat << 'ENDOFFILE' > " + path.toString() + "\n");
		command.append(content);
		command.append("\n");
		command.append("ENDOFFILE");
		return session.runCommandAndAssertSuccess( command.toString() );
	}

	
	public Response removeFile(ExecSshSession session, AbsolutePath path){
		return session.runCommandAndAssertSuccess("rm -f "+ path.toString());
	}
}
