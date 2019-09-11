public class Process extends Thread {
	// Runnable allows multiple threads & non-thread functonality
	int ID;
	int readyTime;
	int serviceTime;
	int cumuExecTime;
	int processTimeAllowance;
	boolean started; // State bits
	boolean finished;// Finished

	// Default (empty) constructor
	public Process() {
		this.ID = 0; // Assigned ID
		this.readyTime = 0;
		this.serviceTime = 0;
		this.cumuExecTime = 0;
		this.started = false;
		this.finished = false;
		this.processTimeAllowance = 0;
	}

	// Constructor
	public Process(int processID, int readyTime, int serviceTime) {
		this.ID = processID; // Assigned ID
		this.readyTime = readyTime;
		this.serviceTime = serviceTime;
		this.cumuExecTime = 0;
		this.started = false;
		this.finished = false;
		this.processTimeAllowance = 0;
	}

	public void run() {
		while (!isOutOfTime() && !isDone()) {
			execute1Sec();
			cumuExecTime++;
			processTimeAllowance--;
		}
		synchronized (this) {
			this.notify();
		}
	}

	// ===================Helper Methods
	public void execute1Sec() {
		// System.out.println("Process running...");
		synchronized (this) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Execute1Sec: Exception");
			}
		}
	}

	// ===Boolean
	public boolean isStarted() {
		if (cumuExecTime > 0) {
			this.started = true;
		} else {
			this.started = false;
		}
		return this.started;
	}

	public boolean isDone() { // Process is complete
		if (cumuExecTime >= serviceTime) {
			this.finished = true;
		}
		return this.finished;
	}

	public boolean isOutOfTime() {
		if (processTimeAllowance <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public int getReadyTime() {
		return this.readyTime;
	}

	public int getServiceTime() {
		return this.serviceTime;
	}

	// ===Setters
	public void setProcessAllowance(int allowance) {
		this.processTimeAllowance = allowance;
	}

	// ===Getters
	public String getStateString() {
		if (isDone()) {
			return "Finished";
		} else if (isOutOfTime()) {
			return "Paused";
		} else if (!isOutOfTime()) {
			return "Resumed";
		} else {
			return "Process: getStateString Error!";
		}
	}

	// ===Diagnostics
	public void printProcess() {
		System.out.println("P" + ID + " RdyTime=" + readyTime + ", ServeTime=" + serviceTime + " has cumu-time="
				+ cumuExecTime + " processAllowance=" + processTimeAllowance + " isDone = " + isDone() + " isStarted = "
				+ isStarted());
	}
}