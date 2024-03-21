package com.hexaware.entity;



//Customer Class
//• Attributes:
//o customer_name,
//o email,
//o phone_number,
//• Methods and Constructors:
//o Implement default constructors and overload the constructor with Customer
//attributes, generate getter and setter methods.
//o display_customer_details(): Display customer details.

public class Customer {
	private int customerId;
	private String customerName;
	private String email;
	private String phoneNumber;
	
	
//	public ArrayList<Customer> customerList = new ArrayList<>();
	
	Customer(){
		
	}
	
	public Customer(int customerId,String customerName, String email, String phoneNumber){
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	 public String getCustomerName() {
	        return customerName;
	    }

	    public void setCustomerName(String customerName) {
	        this.customerName = customerName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhoneNumber() {
	        return phoneNumber;
	    }

	    public void setPhoneNumber(String phoneNumber) {
	        this.phoneNumber = phoneNumber;
	    }
	
	    public String displayCustomerDetails() {
	    	return "Customer: "+"\nName: "+customerName+"\nEmail: "+email+"\nPhone: "+phoneNumber;
	    }

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		
		@Override
		public String toString() {
			return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
					+ ", phoneNumber=" + phoneNumber + "]";
		}
}
