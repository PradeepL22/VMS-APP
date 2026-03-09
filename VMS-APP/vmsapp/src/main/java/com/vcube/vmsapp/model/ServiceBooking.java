package com.vcube.vmsapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "service_bookings")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;

	@NotBlank(message = "Customer Name is required")
	private String customerName;

	@NotBlank(message = "Vehicle Number is required")
	private String vehicleNumber;

	@NotBlank(message = "Vehicle Type is required")
	private String vehicleType;

	@NotBlank(message = "Service Type is required")
	private String serviceType;

	@NotNull(message = "Booking Date is required")
	private LocalDate bookingDate;

	@NotBlank(message = "Status is required")
	private String status;

}
