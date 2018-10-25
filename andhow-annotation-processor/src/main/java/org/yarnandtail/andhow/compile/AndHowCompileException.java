package org.yarnandtail.andhow.compile;

import java.util.Collections;
import java.util.List;

/**
 * Exception thrown by the AndHowCompileProcessor for any type of problem
 * during the compile.
 * 
 * This exception can accumulate a list of Problems so that all problems can
 * be found in a single pass and for easier testing (this exception can be 
 * inspected to determine the exact problem).
 * 
 * @author ericeverman
 */
public class AndHowCompileException extends RuntimeException {

	String msg = "The AndHowCompileProcessor found a problem during compilation " +
				"and threw a fatal exception - See the error details listed nearby.";
	final List<CompileProblem> problems;
	final Throwable cause;
		

	/**
	 * Instance when there are one or more AndHow domain 'problems' with the
	 * code being compiled.
	 * 
	 * Examples would include Properties that are not <code>static final</code>
	 * or too many init classes on the classpath.
	 * 
	 * @param problems A list of problems found during compilation.
	 */
	public AndHowCompileException(List<CompileProblem> problems) {
		
		cause = null;
		
		if (problems != null) {
			this.problems = problems;
		} else {
			this.problems = Collections.emptyList();
		}
	}
	
	/**
	 * Instance when there is some unexpected, non-AndHow related exception.
	 * 
	 * Example:  Unwritable file system.
	 * 
	 * @param message AndHow context description
	 * @param cause Error caught that caused the issue
	 */
	public AndHowCompileException(String message, Throwable cause) {
		msg = message;
		this.cause = cause;
		this.problems = Collections.emptyList();
	}

	@Override
	public synchronized Throwable getCause() {
		if (cause != null) {
			return cause;
		} else {
			return super.getCause();
		}
	}


	@Override
	public String getMessage() {
		if (msg != null) {
			return msg;
		} else {
			return super.getMessage();
		}
	}
	
	public List<CompileProblem> getProblems() {
		return problems;
	}
	
}