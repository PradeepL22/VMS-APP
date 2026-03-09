package com.vcube.vmsapp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.vmsapp.model.ServiceBooking;

@Repository
public interface ServiceBookingRepository extends JpaRepository<ServiceBooking, Integer> {
	
	
	// Search by Vehicle Number
    Page<ServiceBooking> findByVehicleNumberContaining(String vehicleNumber, Pageable pageable);

    // Filter by Status
    Page<ServiceBooking> findByStatus(String status, Pageable pageable);

    // Search + Filter together
    Page<ServiceBooking> findByVehicleNumberContainingAndStatus(String vehicleNumber, String status, Pageable pageable);

	

}
