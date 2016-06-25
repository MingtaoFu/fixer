package team.hustsoft.PD.TableClass;

public class Customer{
	private int id;
	private String citizenId;
	private String contact;
	private enum type {
		home, company, agent, constracted;
	}
	private String addr;
	private String zipCode;
	private String email;
	private String companyName;
	private String companyPhone;
	private String mobilePhone;
	
	//setters and getters
	public Customer() {
 	}
}
