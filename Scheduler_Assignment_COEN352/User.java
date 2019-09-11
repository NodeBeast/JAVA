public class User {
	String userName;
	int userProcessNum;
	int[] userRequests;// Contains start/ready times of processes
	int readyProcessCount;
	boolean hasReadyProcess;
	boolean[] ready;// Indicate which user process is ready
	Process[] userJobs; // Contains all of user's inactive processes.

	// Constructor
	public User(String userName, int userProcessNum, int[] userRequests) {// Constructor
		this.userName = userName;
		this.userProcessNum = userProcessNum;
		// Create Empty Queues
		this.userJobs = new Process[userProcessNum];
		// Instantiate User's Processes
		for (int i = 0; i < userProcessNum; i++) {
			Process p = new Process(i, userRequests[i * 2], userRequests[(i * 2) + 1]);
			userJobs[i] = p;// Add to inactive list of user's processes
		}
		this.hasReadyProcess = false;
		this.readyProcessCount = 0;
		// Initialize Ready Queue
		this.ready = new boolean[userProcessNum];
		for (int i = 0; i < userProcessNum; i++) {
			ready[i] = false;
		}
	}

	public boolean jobsPending() {// User Still has requests to finish
		for (int i = 0; i < userProcessNum; i++) {
			if (userJobs[i].finished == false)
				return true;
		}
		return false;
	}

	public boolean hasReadyProcess(long currentTime) {
		// Checks userProArr iteratively for ready processes
		hasReadyProcess = false; // Reset
		readyProcessCount = 0;// Reset
		for (int i = 0; i < userProcessNum; i++) {
			if (currentTime >= userJobs[i].getReadyTime()) {
				hasReadyProcess = true;
				ready[i] = true;
				readyProcessCount++;
			} else{
				ready[i] = false;
			}
		}
		return hasReadyProcess;
	}
	public void updateReadyList(int curr){
		// Checks userProArr iteratively for ready processes
		hasReadyProcess = false; // Reset
		readyProcessCount = 0;// Reset
		for (int i = 0; i < userProcessNum; i++) {
			if (curr >= userJobs[i].getReadyTime()) {
				hasReadyProcess = true;
				ready[i] = true;
				readyProcessCount++;
			} else{
				ready[i] = false;
			}
		}
	}
	// ===Setters
	
	// ===Getters
	public Process getProcess(int index) {
		return userJobs[index];
	}
	public int getProcessNum() {
		return userProcessNum;
	}

	public String getName() {
		return this.userName;
	}
	public boolean getReady(int index) {
		return ready[index];
	}
	public int getReadyProcessCount() {
		return readyProcessCount;
	}
//===Diagnostics
	public void printUserState() {
		System.out.println("\n USER-STATE: User " + userName + " has " + readyProcessCount + "/" + userProcessNum
				+ " ready jobs and rdyProcess=" + hasReadyProcess);
		this.printReadyArr();
		for (int i = 0; i < userProcessNum; i++) {
			userJobs[i].printProcess();
		}
	}
	public void printReadyArr() {
		System.out.print("User" + userName + " ReadyArr = [ ");
		for (int i = 0; i < ready.length; i++) {
			System.out.print(ready[i] + " ");
		}
		System.out.print("] \n");
	}


}
// User gets time slice to divide up among it's processes
