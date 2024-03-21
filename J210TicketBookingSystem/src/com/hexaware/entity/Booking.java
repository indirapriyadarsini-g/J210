package com.hexaware.entity;

import java.time.LocalDate;
import java.util.ArrayList;


public class Booking {

	private Event event;
    private int numTickets;
    private double totalCost;
    private Customer customer;
    private ArrayList<Customer> customerList;
    private LocalDate bookingDate;

    private int bookingId = 1;

//    public Booking(Event event2, int numTickets2, ArrayList<Booking> bookingList2, String string) {
//    	this.event = event2;
//    	this.numTickets = numTickets2;
//    	this.
//    }
//    
    
    
    public static ArrayList<Booking> bookingList = new ArrayList<>();
  
    public Booking(Event event, int numTickets,Customer customer,LocalDate bookingDate) {
        super();
    	this.bookingId = bookingId++;
        this.event = event;
        this.numTickets = numTickets;
//        this.setCustomer(customer);
        this.setBookingDate(bookingDate);
        
//        calculateBookingCost();
    }

  
  

    
//    public void bookTickets(Event event, int numTickets, ArrayList<Booking> booking) {
//        ec.bookTickets(event,numTickets);
//    }

    
//    public void cancelBooking(Event event2, int numTickets2) {
//        bookingList.remove(cancelBooking(event,numTickets));
//    }

    public Booking(int bookingid2, Customer customer2, Event event2, int numOfTickets, double totalCost2,
			LocalDate bookingDate2) {
		this.bookingId = bookingid2;
		this.customer = customer2;
		this.event = event2;
		this.numTickets = numOfTickets;
		this.totalCost = totalCost2;
		this.bookingDate = bookingDate2;
	}





	public int getAvailableNoOfTickets() {
        return event.getAvailableSeats();
    }

    public Event getEventDetails() {
        return event;
    }


    public int getBookingId() {
        return bookingId;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }


	public LocalDate getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDate bookingDate2) {
		this.bookingDate = bookingDate2;
	}


	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}





	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}





	public Customer getCustomer() {
		return customer;
	}





	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", customer=" + customer + ", event=" + event + ", numTickets="
				+ numTickets + ", totalCost=" + totalCost + ", bookingDate=" + bookingDate + "]";
	}
}
