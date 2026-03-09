package com.vcube.vmsapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import com.vcube.vmsapp.model.ServiceBooking;

public interface ServiceBookingService {

	Page<ServiceBooking> getBookingsPaginated(int pageNo, int pageSize);

	Page<ServiceBooking> searchBookings(String vehicleNumber, int pageNo, int pageSize);

	Page<ServiceBooking> filterBookings(String status, int pageNo, int pageSize);

	Page<ServiceBooking> searchAndFilter(String vehicleNumber, String status, int pageNo, int pageSize);

	List<ServiceBooking> getAllBookings();

	ServiceBooking saveBooking(ServiceBooking booking);

	ServiceBooking getBookingById(int id);

	void deleteBooking(int id);
	
	 Page<ServiceBooking> getBookings(int page);

}
