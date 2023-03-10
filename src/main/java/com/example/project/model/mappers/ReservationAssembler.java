package com.example.project.model.mappers;

import com.example.project.controller.service.ClientService;
import com.example.project.controller.service.CosmetologistService;
import com.example.project.model.dto.ReservationDTO;
import com.example.project.model.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReservationAssembler {

    @Autowired
    private ClientService clientService;
    @Autowired
    private CosmetologistService cosmetologisttService;

    public ReservationDTO toReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setAppointmentTime(reservation.getAppointmentTime());
        reservationDTO.setClientId(reservation.getClient().getId());
        reservationDTO.setCosmetologistId(reservation.getCosmetologist().getId());
        reservationDTO.setProcedure(reservation.getProcedure());
        return reservationDTO;
    }

    public Reservation toReservationEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setAppointmentTime(reservationDTO.getAppointmentTime());
        reservation.setClient(clientService.getClientById(reservationDTO.getClientId()));
        reservation.setCosmetologist(cosmetologisttService.getCosmetologistById(reservationDTO.getCosmetologistId()));
        reservation.setProcedure(reservationDTO.getProcedure());
        return reservation;
    }


    public List<ReservationDTO> toReservationDTOList(List<Reservation> reservations) {
        return reservations.stream()
                .map(this::toReservationDTO)
                .collect(Collectors.toList());
    }

    public List<Reservation> toReservationEntityList(List<ReservationDTO> reservationDTOs) {
        return reservationDTOs.stream()
                .map(this::toReservationEntity)
                .collect(Collectors.toList());
    }
}

