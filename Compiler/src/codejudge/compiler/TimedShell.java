
package codejudge.compiler;

import codejudge.compiler.languages.Language;

// This class is used for timing out the code. For instance, if the time limit of the problem is 2 sec, this will terminate the code after 2 sec
public class TimedShell extends Thread {
	
	private Language parent;
	private Process p;
	private long time;
	
	public TimedShell(Language parent, Process p, long time){
		this.parent = parent;
		this.p = p;
		this.time = time;
	}
	
	// Sleep until timeout and then terminate the process
	public void run() {
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			p.exitValue();
			parent.timedout = false;
		} catch (IllegalThreadStateException e) {
			parent.timedout = true;
			p.destroy();
		}
	}
}
