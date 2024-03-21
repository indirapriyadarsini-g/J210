package com.hexaware.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.hexaware.entity.Booking;
import com.hexaware.entity.Customer;
import com.hexaware.entity.Event;
import com.hexaware.entity.EventType;
import com.hexaware.entity.Revenue;
import com.hexaware.entity.Tickets;
import com.hexaware.entity.Venue;
import com.hexaware.exception.EventNotFoundException;
import com.hexaware.exception.InvalidBookingIDException;
import com.hexaware.util.DBUtil;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository {

	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	Statement stmt;
	
//	IBookingSystemRepository repo = new BookingSystemRepositoryImpl(); 
	
	
	@Override
	public void createEvent(Event event) {
		try {
			conn = DBUtil.getDBConn();
			ps = conn.prepareStatement("insert into Event(event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) "
					+ "values(?,?,?,?,?,?,?,?,?)");
			
			ps.setInt(1, event.getEventId());
			ps.setNString(2, event.getEventName());
			ps.setDate(3,Date.valueOf(event.getEventDate()));
			ps.setTime(4, Time.valueOf(event.getEventTime()));
			ps.setInt(5, event.getVenue().getVenueId());
			ps.setInt(6, event.getTotalSeats());
			ps.setInt(7, event.getAvailableSeats());
			ps.setDouble(8, event.getTicketPrice());
			ps.setString(9, event.getEventType());
//			ps.setInt(10, event.getBookingId());
				
			int n = ps.executeUpdate();
			System.out.println(n+"rows inserted");
			}
			catch(Exception e) {
				e.printStackTrace();
			}

	}

//	@Override
//	public void getEventDetails(Event event) {
//		try {
//			conn = DBUtil.getDBConn();
//			ps = conn.prepareStatement("select * from event where eventid = ?;");
//			
//			ps.setInt(1, event.getEventId());
//			rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				System.out.println(rs.getStatement());
//			}
//			
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
	@Override
	public List<Event> getEventDetails() {
		List<Event> eventsList = new ArrayList<>();
		try {
			conn = DBUtil.getDBConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from event;");
			
			
			while(rs.next()) {
				int eventId = rs.getInt("event_id");
				String eventName = rs.getString("event_name");
				LocalDate date = rs.getDate("event_date").toLocalDate();
				LocalTime time = rs.getTime("event_time").toLocalTime();
				int totalSeats = rs.getInt("total_seats");
				int availableSeats = rs.getInt("available_seats");
				double ticketPrice = rs.getDouble("ticket_price");
				String eventType = rs.getString("event_type");
				int venueId = rs.getInt("venue_id");
				Venue venue = getVenueById(venueId);
				Event event = new Event();

				event.setEventId(eventId);
				event.setEventName(eventName);
				event.setEventDate(date);
				event.setEventTime(time);
				event.setTotalSeats(totalSeats);
				event.setAvailableSeats(availableSeats);
				event.setTicketPrice(ticketPrice);
				event.setEventType(EventType.valueOf(eventType.toUpperCase()));
				event.setVenue(venue);
				eventsList.add(event);
				
//				System.out.println(rs.getStatement());
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return eventsList;
		
	}

	

	@Override
	public List<Tickets> getAvailableNoOfTickets() throws SQLException {

		conn = DBUtil.getDBConn();
		stmt = conn.createStatement();
		String query = "select event_name, available_seats from event";
		rs = stmt.executeQuery(query);

		List<Tickets> ticketsList = new ArrayList<>();
		while (rs.next()) {
			String eventName = rs.getString(1);
			int availableTickets = rs.getInt(2);

			Tickets ticket = new Tickets(eventName, availableTickets);
			ticketsList.add(ticket);
		}

		return ticketsList;
	}

	
	@Override
	public double calculateBookingCost(int eventId, int numTickets) throws SQLException {
		double bookingCost = 0;
		try {
			
			conn = DBUtil.getDBConn();
			ps = conn.prepareStatement("select ticket_price * numTickets from event where event_id = ?");
			ps.setInt(1, eventId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				bookingCost = rs.getDouble(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
//		finally{
//			conn.close();
//		}
		return bookingCost;
	}


	@Override
	public void cancelBooking(int bookingId) throws SQLException, InvalidBookingIDException {
		conn = DBUtil.getDBConn();
		try {

			Booking booking = getBookingDetails(bookingId);

			Event event = booking.getEventDetails();
			int seats = getEventDetailsById(event.getEventId()).getAvailableSeats();
			seats += booking.getNumTickets();
			updateAvailableSeats(bookingId, 1, seats);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidBookingIDException e) {
			e.printStackTrace();  
		} catch (EventNotFoundException e) {
			e.printStackTrace();
		}
		ps = conn.prepareStatement("delete from booking where booking_id = ?");
		ps.setInt(1, bookingId);
		int row = ps.executeUpdate();

		if (row > 0) {
			System.out.println("Booking cancellation successful");
			System.out.println("Available seats updated successfully");
		} else {
			throw new InvalidBookingIDException();
		}
//		conn.close();

	}

	@Override
	public Booking getBookingDetails(int bookingId) 
			throws SQLException, EventNotFoundException, InvalidBookingIDException{
		Booking booking = null;
		try {
			conn = DBUtil.getDBConn();
			ps = conn.prepareStatement("select * from booking where booking_id = ?");
			ps.setInt(1, bookingId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int bookingid = rs.getInt(1);
				int customerId = rs.getInt(2);
				int eventId = rs.getInt(3);
				int numOfTickets = rs.getInt(4);
				double totalCost = rs.getDouble(5);
				LocalDate bookingDate = rs.getDate(6).toLocalDate();

				Customer customer = getCustomerDetails(customerId);

				Event event = getEventDetailsById(eventId);

				booking = new Booking(bookingid, customer, event, numOfTickets, totalCost, bookingDate);
				
			}
			throw new InvalidBookingIDException();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(EventNotFoundException e) {
			e.printStackTrace();
		}		
//		finally {
//			conn.close();
//		}
		return booking;
	}

	@Override
	public void bookTickets(int customerId, int eventId, int numTickets, double totalCost) throws SQLException {
		conn = DBUtil.getDBConn();

		String query = "insert into booking(customer_id,event_id,num_tickets,total_cost,booking_date) values (?,?,?,?,?)";

		ps = conn.prepareStatement(query);

		ps.setInt(1, customerId);
		ps.setInt(2, eventId);
		ps.setInt(3, numTickets);
		ps.setDouble(4, totalCost);
		ps.setDate(5, Date.valueOf(LocalDate.now()));

		ps.executeUpdate();

//		conn.close();
		
	}

	@Override
	public Venue getVenueById(int id) throws SQLException, NullPointerException {
		Venue venue = null;
		try {
			conn = DBUtil.getDBConn();

			ps = conn.prepareStatement("select * from venue where venue_id =?;");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				int vid = rs.getInt("venue_id");
				String vname = rs.getString("venue_name");
				String vaddress = rs.getString("address");

				venue = new Venue(vid, vname, vaddress);
				conn.close();
				return venue;
				
				
			}

//			conn.close();
			throw new NullPointerException();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return venue;
		
	}

	@Override
	public Customer getCustomerDetails(int customerId) throws SQLException {
		Customer customer = null;
		conn = DBUtil.getDBConn();
		
		String query = "select * from customers where customer_id = ?";
		ps =conn.prepareStatement(query);
		ps.setInt(1, customerId);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			int cid = rs.getInt("customer_id");
			String cname = rs.getString("customer_name");
			String email = rs.getString("email");
			String phno = rs.getString("phone_number");

			customer = new Customer(cid, cname, email, phno);
			
//			conn.close();
			
		}
//		conn.close();
		return customer;
	}


	@Override
	public Event getEventDetailsById(int eventid) throws SQLException, EventNotFoundException {
		conn = DBUtil.getDBConn();
		String query = "select * from event where event_id =?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, eventid);
		rs = ps.executeQuery();
		Event event = null;
		if (rs.next()) {
			int eventId = rs.getInt("event_id");
			String eventName = rs.getString("event_name");
			LocalDate date = rs.getDate("event_date").toLocalDate();
			LocalTime time = rs.getTime("event_time").toLocalTime();
			int totalSeats = rs.getInt("total_seats");
			int availableSeats = rs.getInt("available_seats");
			double ticketPrice = rs.getDouble("ticket_price");
			String eventType = rs.getString("event_type");
			int venueId = rs.getInt("venue_id");

			Venue venue = getVenueById(venueId);

			event = new Event(eventId, eventName, date, time, venue, totalSeats, availableSeats, ticketPrice, 
					EventType.valueOf(eventType.toUpperCase()));
			
			
		}
//		conn.close();
		if(event == null) throw new EventNotFoundException();
		return event;
	}


	@Override
	public void updateAvailableSeats(int eventId, int numTickets, int updatedAvailableSeats) throws SQLException {

		conn = DBUtil.getDBConn();

		ps = conn.prepareStatement("update event set available_seats=? where event_id=?;");
		ps.setInt(1, updatedAvailableSeats);
		ps.setInt(2, eventId);

		ps.executeUpdate();

//		conn.close();
	}

	@Override
	public List<Revenue> getTotalRevenue() throws SQLException {
		conn = DBUtil.getDBConn();
		String query = "select event_name, sum(((total_seats - available_seats)*ticket_price)) as TotalRevenue from event group by event_name";
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);

		List<Revenue> revenueList = new ArrayList<>();
		while (rs.next()) {
			String eventName = rs.getString(1);
			double totalRevenue = rs.getDouble(2);
			Revenue dto = new Revenue(eventName, totalRevenue);
			revenueList.add(dto);
		}
//		conn.close();
		return revenueList;
	}
	
}
