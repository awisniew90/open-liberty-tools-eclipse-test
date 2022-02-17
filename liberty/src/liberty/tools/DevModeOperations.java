package liberty.tools;

import org.eclipse.core.resources.IProject;

import liberty.tools.utils.Dialog;
import liberty.tools.utils.Project;

/**
 * Provides the implementation of all supported dev mode operations.
 */
public class DevModeOperations {

	// TODO: Figure out if there are any special needs for Windows/Linux.
	// TODO: Figure out how to get this dynamically from the eclipse configuration,
	// and establish precedence.
	private final String MVN_INSTALL = "/Users/mezarin/tools/maven/apache-maven-3.6.3";
	private final String JAVA_HOME = "/Users/mezarin/tools/java/sdks/openjdk/jdk-11.0.8+10/Contents/Home";

	/**
	 * Starts the server in development mode.
	 * 
	 * @return An error message or null if the command was processed successfully.
	 */
	public void start() {
		IProject project = Project.getSelected();
		String projectPath = Project.getPath(project);
		if (projectPath == null) {
			Dialog.displayErrorMessage("Unable to find the path to selected project. Be sure to select one.");
			return;
		}
		String cmd = "";
		if (Project.isMaven(project)) {
			if (!Project.isMavenBuildFileValid(project)) {
				System.out.println("Maven build file on project" + project.getName() + " is not valid..");
			}
			cmd = "mvn io.openliberty.tools:liberty-maven-plugin:dev -f " + projectPath;
		} else if (Project.isGradle(project)) {
			if (!Project.isGradleBuildFileValid(project)) {
				System.out.println("Build file on project" + project.getName() + " is not valid.");
			}
			cmd = "gradle libertyDev -b=" + projectPath;
		} else {
			Dialog.displayErrorMessage("Project" + project.getName() + "is not a Gradle or Maven project.");
			return;
		}

		try {
			runCommand(cmd);
		} catch (Exception e) {
			Dialog.displayErrorMessageWithDetails("An error was detected while performing the start action.", e);
			return;
		}

	}

	/**
	 * Starts the server in development mode.
	 * 
	 * @return An error message or null if the command was processed successfully.
	 */
	public void startWithParms(String userParms) {
		IProject project = Project.getSelected();
		String projectPath = Project.getPath(project);
		if (projectPath == null) {
			Dialog.displayErrorMessage("Unable to find the path to selected project. Be sure to select one.");
			return;
		}
		String cmd = "";
		if (Project.isMaven(project)) {
			cmd = "mvn io.openliberty.tools:liberty-maven-plugin:dev " + userParms + " -f " + projectPath;
		} else if (Project.isGradle(project)) {
			cmd = "gradle libertyDev " + userParms + " -b=" + projectPath;
		} else {
			Dialog.displayErrorMessage("Project" + project.getName() + "is not a Gradle or Maven project.");
			return;
		}

		System.out.println("@ed Command to run: " + cmd);
		try {
			runCommand(cmd);
		} catch (Exception e) {
			Dialog.displayErrorMessageWithDetails("An error was detected while performing the start... action.", e);
			return;
		}
	}

	/**
	 * Starts the server in development mode.
	 * 
	 * @return An error message or null if the command was processed successfully.
	 */
	public void startInContainer() {
		IProject project = Project.getSelected();
		String projectPath = Project.getPath(project);
		if (projectPath == null) {
			Dialog.displayErrorMessage("Unable to find the path to selected project. Be sure to select one.");
			return;
		}

		String cmd = "";
		if (Project.isMaven(project)) {
			cmd = "mvn io.openliberty.tools:liberty-maven-plugin:devc -f " + projectPath;
		} else if (Project.isGradle(project)) {
			cmd = "gradle libertyDevc -b=" + projectPath;
		} else {
			Dialog.displayErrorMessage("Project" + project.getName() + "is not a Gradle or Maven project.");
		}

		try {
			runCommand(cmd);
		} catch (Exception e) {
			Dialog.displayErrorMessageWithDetails("An error was detected while performing the start in container action.",
					e);
			return;
		}
	}

	/**
	 * Starts the server in development mode.
	 * 
	 * @return An error message or null if the command was processed successfully.
	 */
	public void stop() {
		String cmd = "exit";
		try {
			runCommand(cmd);
		} catch (Exception e) {
			Dialog.displayErrorMessageWithDetails("An error was detected while performing the stop action.", e);
			return;
		}
	}

	/**
	 * Runs the tests provided by the application.
	 * 
	 * @return An error message or null if the command was processed successfully.
	 */
	public void runTests() {
		String cmd = " ";
		try {
			runCommand(cmd);
		} catch (Exception e) {
			Dialog.displayErrorMessageWithDetails("An error was detected while performing the run tests action.", e);
			return;
		}
	}

	/**
	 * Opens test reports.
	 */
	public void openTestReports() {
		// TODO1: Find the location of the files.
		// TODO2: Display them in a view.
	}

	/**
	 * Runs the specified command on a terminal.
	 * 
	 * @param cmd The command to run.
	 * 
	 * @throws Exception If an error occurs while running the specified command.
	 */
	public void runCommand(String cmd) throws Exception {
		// TODO: Implement me.
		throw new UnsupportedOperationException(
				"Not yet implemented. Waiting for a brave soul to do it. Not sure how to do this!");
	}
}