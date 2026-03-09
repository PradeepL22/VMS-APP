package com.vcube.vmsapp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcube.vmsapp.model.ServiceBooking;
import com.vcube.vmsapp.service.ServiceBookingService;

@Controller
public class ServiceBookingController {

	@Autowired
	ServiceBookingService service;

//	// Default Home with Pagination
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		return findPaginated(0, model, "", "");
//	}

	// Pagination + Search + Filter
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable int pageNo, Model model,
			@RequestParam(defaultValue = "") String vehicleNumber, @RequestParam(defaultValue = "") String status) {

		int pageSize = 5;
		Page<ServiceBooking> page;

		if (!vehicleNumber.isEmpty() && !status.isEmpty()) {
			page = service.searchAndFilter(vehicleNumber, status, pageNo, pageSize);
		} else if (!vehicleNumber.isEmpty()) {
			page = service.searchBookings(vehicleNumber, pageNo, pageSize);
		} else if (!status.isEmpty()) {
			page = service.filterBookings(status, pageNo, pageSize);
		} else {
			page = service.getBookingsPaginated(pageNo, pageSize);
		}

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("listBookings", page.getContent());

		model.addAttribute("vehicleNumber", vehicleNumber);
		model.addAttribute("status", status);

		return "index";
	}

	@GetMapping("/")
	public String viewHomePage(Model model, @RequestParam(defaultValue = "0") int page) {

		Page<ServiceBooking> bookingPage = service.getBookings(page);

		model.addAttribute("listBookings", bookingPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", bookingPage.getTotalPages());

		return "index";
	}

//	// Home Page -> List Bookings
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		model.addAttribute("listBookings", service.getAllBookings());
//		return "index";
//	}

	// Show New Booking Form
	@GetMapping("/showNewBookingForm")
	public String showNewBookingForm(Model model) {

		ServiceBooking booking = new ServiceBooking();

		booking.setBookingDate(LocalDate.now());
		booking.setStatus("Pending");

		model.addAttribute("booking", booking);
		return "new_booking";
	}

	// Save Booking
	@PostMapping("/saveBooking")
	public String saveBooking(@ModelAttribute("booking") ServiceBooking booking) {
		service.saveBooking(booking);
		return "redirect:/";
	}

	// Update Booking Form
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {

		ServiceBooking booking = service.getBookingById(id);
		model.addAttribute("booking", booking);

		return "update_booking";
	}

	// Delete Booking
	@GetMapping("/deleteBooking/{id}")
	public String deleteBooking(@PathVariable(value = "id") int id) {

		service.deleteBooking(id);
		return "redirect:/";
	}
}
