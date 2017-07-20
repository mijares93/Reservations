package com.alejandro.reservations.data.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.alejandro.reservations.data.entity.Guest;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {

}