public class Scheduler extends Thread {
	public static void main(String[] args) {
		long quantum = 4;
		int userNumber = 2;
		int[] AlphaReqs = { 4, 3, 1, 5 };
		int[] BetaReqs = { 5, 6 };
		// Test Users
		User Alpha = new User("A", 2, AlphaReqs);
		User Beta = new User("B", 1, BetaReqs);
		User[] users = new User[2]; // Initialize users/processes/requests
		users[0] = Alpha;
		users[1] = Beta;
		// Init Scheduler and load in users & processes
		Thread sched = new Scheduler(quantum, userNumber, users);
		sched.start(); // sched extends Thread
		// sched.exit();
	}

	long quantum;
	int userNum;
	User[] users;
	Process currProcess; //
	Thread currRunningProcess; // Worker thread
	int readyUserNum;
	int readyUserAllowance;// only care about ready users
	long startTime; // Scheduler thread Initialization time
	int currTime; // *Truncated to integer values*
	boolean allDone;
	int cycles;

	// Constructor
	public Scheduler(long timeQuanta, int userNumber, User[] users) {
		this.quantum = timeQuanta;
		this.userNum = userNumber;
		this.startTime = System.currentTimeMillis();
		this.currTime = 0;
		this.users = users;
		this.readyUserNum = 0;
		this.readyUserAllowance = 0;
		this.allDone = false;
		System.out.println("---Scheduler init at t=" + currTime + ", " + " with " + userNum
				+ " users and time-quantum of " + quantum + "mS---");
		this.cycles = 0;
	}

	@Override
	public void run() { // Start new scheduling cycle
		while (!allDone) {// Keep cycling until all processes are complete
			cycles++;
			p("CYCLE START: "+cycles);
			// update currTime locally
			updateCurrTime();

			// Determine # ready users
			updateNumReadyUsers();

			// No Ready Processes, CPU idles
			while (readyUserNum == 0) {
				updateNumReadyUsers();
				idle(500);
			}

			// Determine readyUserAllowance time (if ready users exist)
			if (readyUserNum != 0) {
				readyUserAllowance = (int) ((quantum) / readyUserNum);
				//divide quantum amongst users that are ready at currTime
			}

			// Cycle through users that had ready processes at beginning of cycle
			for (int i = 0; i < userNum; i++) {// iterate though users
				if(users[i].hasReadyProcess == false){
					//User did not have ready processes at beginning of cycle, skip
					continue;
				}
				
				p("=======USER START=======");
				// Iterate through each users' processes
				for (int j = 0; j < users[i].getProcessNum(); j++) {
					
					if (!users[i].getReady(j)) {
						continue;// Skip to next process, user not ready
					}
					
					// Local Process
					currProcess = users[i].getProcess(j);

					// Assign Time Allowance for Process
					int processAllowance = readyUserAllowance / users[i].getReadyProcessCount();
					p("Setting time allowance to "+processAllowance);
					currProcess.setProcessAllowance(processAllowance);

					// Run Process
					synchronized (this) {// Get lock on process
						// System.out.println("Scheduler: Starting thread");
						if (!currProcess.isStarted()) {
							printOut(users[i], j, "Started");
						}
						printOut(users[i], j, currProcess.getStateString());
						currRunningProcess = currProcess;
						currRunningProcess.start();
						try {
							currRunningProcess.join();//
							p("Sched: Join Compete");
							printOut(users[i], j, currProcess.getStateString());// Process
																				// State
						} catch (Exception e) {
							System.out.println("Error waiting for process");
						}
					}
				}
			}
			if (allDone()) {
				p("Sched: AllDOne!");
				break;
			}
		}

	}

	// =========================HELPERS
	public void printOut(User u, int processID, String state) {
		printTime();
		System.out.print("User " + u.getName() + ", ");
		System.out.print("Process " + processID + ", ");
		System.out.print(state + "\n");
	}

	public void printTime() {
		updateCurrTime();
		if (currTime == 0) {
			return;
		}
		System.out.print("-Time: " + currTime + ", ");
	}

	public void idle(int t) {
		synchronized (this) {
			// System.out.println("Idle...");
			try {
				wait(t);
			} catch (Exception e) {
			}
		}
	}

	public void updateNumReadyUsers() {
		readyUserNum = 0; // Reset value
		updateCurrTime();
		for (int i = 0; i < userNum; i++) {
			if (users[i].hasReadyProcess(currTime)) {
				readyUserNum++;
			}
		}
	}

	public void updateCurrTime() {
		this.currTime = (int) ((System.currentTimeMillis() - startTime) / 1000);
	}

	public boolean allDone() {
		for (int i = 0; i < userNum; i++) {
			if (users[i].jobsPending())
				return false;
		}
		System.out.println("===ALL DONE!===");
		return true;
	}

	// ===Diagnostics
	public void p(String s) {
		System.out.println("   ***" + s + "***");
	}
	public void p(int i) {
		System.out.print(i + ": ");
	}
	// --------------------MAIN METHOD

}
