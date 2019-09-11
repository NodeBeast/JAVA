public class Payroll{
	//Example class for use with dictionary
	private int ID;
	private String name;
	private String address;
	Payroll(int inID, String inname, String inaddr){
		ID = inID;
		name = inname;
		address = inaddr;
	}
	public Integer getID(){ 
		System.out.println(name+"'s ID number is: "+ID); 
		return ID; }
	public String getName(){ 
		System.out.println("Employee name: "+ name);
		return name;
	}
	public String getAddress(){ 
		System.out.println(name+"'s address is: "+address);
		return address; 
	}	
}
