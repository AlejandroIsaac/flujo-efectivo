package com.api.flujoefectivo.service;

import com.api.flujoefectivo.persistence.entity.Seat;

import java.util.List;

public interface SeatService {


    public Seat add(Seat seat);
    public List<Seat> getAll();
    public Seat getById(Long idSeat);
    public Seat updateById(Seat seat);
}
