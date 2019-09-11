public class Driver {
	public static void main(String[] args) {
		
		Payroll p1 = new Payroll(1, "Marc", "999, Sherbet Ave");
		Payroll p2 = new Payroll(2, "Joe","1455 Tow");
		
		Dictionary<Integer, Payroll> IDdict;
		IDdict = new Dictionary<Integer,Payroll>();
		
		p2.getName();
		p2.getID();
		p2.getAddress();
		
		
		
	}

}
