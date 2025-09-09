package com.hms.appointment.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hms.appointment.dto.AppointmentDTO;
import com.hms.appointment.dto.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private Long doctorId;  
    private LocalDateTime appointmentDateTime;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String reason;
    private String notes;
    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = Status.SCHEDULED; // 👈 default value
        }
    }
    public AppointmentDTO toDTO  () {
        return new AppointmentDTO(id, patientId, doctorId, appointmentDateTime, status, reason, notes);
    }
    public Appointment(Long id){
        this.id=id;
    }
}
/*
 

The difference comes from how JPA + Hibernate handle default values when persisting entities.

Case 1:
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private Status status = Status.SCHEDULED;


This sets the Java field's default value at the time the object is constructed in memory.

But… Hibernate may bypass this default when it creates the entity from the database (or even proxies), because it does not call your Java constructor in all cases.

Also, if you explicitly set status = null in code before saving, Hibernate will persist null (and then you'll hit nullable=false error).

So → this approach doesn’t guarantee a default when Hibernate persists.

Case 2 (works):
@PrePersist
public void prePersist() {
    if (status == null) {
        status = Status.SCHEDULED;
    }
}


@PrePersist is a JPA lifecycle callback.

It runs right before the entity is inserted into the database.

This ensures that if status is still null, it will always be assigned Status.SCHEDULED.

Hibernate respects this hook, so it works reliably.

✅ Best Practice:
Use @PrePersist (or database-level default constraint) for default values in entities, not Java field initialization alone.
 */