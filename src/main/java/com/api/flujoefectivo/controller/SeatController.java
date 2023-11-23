package com.api.flujoefectivo.controller;

import com.api.flujoefectivo.persistence.entity.Seat;
import com.api.flujoefectivo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seat")
public class SeatController {
    @Autowired
    SeatService seatService;

    @GetMapping
    ResponseEntity<List<Seat>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getAll());
    }
    @GetMapping("/{idSeat}")
    ResponseEntity<Seat> getById(@PathVariable Long idSeat){
        return ResponseEntity.status(HttpStatus.OK).body(seatService.getById(idSeat));
    }
    @PostMapping
    ResponseEntity<Seat> save(@RequestBody Seat seat){
        return ResponseEntity.status(HttpStatus.OK).body(seatService.add(seat));

    }
}

/*save new seat*/
/*
*
*{
    "date": "15-12-18",
    "number": 1,
    "description": "compra a credito",
    "accountID": {"id": 9},
    "debe": 0.0,
    "haber": 1000.0,
    "transferAccount": {"id": 13}
}*/
