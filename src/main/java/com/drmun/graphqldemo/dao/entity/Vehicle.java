package com.drmun.graphqldemo.dao.entity;

import com.drmun.graphqldemo.error.exception.DateForVehicleParsingException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Сущность траспорного средства.
 */
@Data
@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "model_code", nullable = false)
    private String modelCode;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "launch_date")
    private LocalDate launchDate;

    @JsonIgnore
    private transient String formattedDate;

    public void setLaunchDate(String date) {
        try {
            launchDate = date.isBlank()
                    ? null
                    : LocalDate.parse(date);
        } catch (DateTimeParseException exception) {
            throw new DateForVehicleParsingException(date);
        }
    }

    public String getFormattedDate() {
        return launchDate.toString();
    }
}
