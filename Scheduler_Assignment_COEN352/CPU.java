public class CPU {
	
	public static void main(String[] args) {

		long quantum = 4000;
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
		sched.start(); //sched extends Thread
		
		
		
		System.out.println("---End Main---");
		// Sample:
		// 4 -->quantum: 4 mili-sec (full cycle of users)
		// A 2 -->UserName, NumProcesses
		// 4 3 -->(P0) ReadyTime, ServiceTime
		// 1 5 -->(P1) ReadyTime, ServiceTime
		// B 1
		// 5 6
	}

}
