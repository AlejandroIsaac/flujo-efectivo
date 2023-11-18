package com.api.flujoefectivo.persistence.repository;

import com.api.flujoefectivo.persistence.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
