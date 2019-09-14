package com.space.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.annotations.Target;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ship")
@JsonPropertyOrder({ "id", "name", "planet", "shipType", "prodDate", "isUsed", "speed", "crewSize", "rating" })
public class Ship {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude
    private Long id;

    @Column(name = "name")
    @JsonInclude
    private String name;

    @Column(name = "planet")
    @JsonInclude
    private String planet;

    @Column(name = "shipType")
    @Enumerated(EnumType.STRING)
    @JsonInclude
    private ShipType shipType;

    @Column(name = "prodDate")
    @Temporal(TemporalType.DATE)
    @JsonInclude
    private Date prodDate;

    @Column(name = "isUsed")
    @JsonInclude
    private Boolean isUsed;

    @Column(name = "speed")
    @JsonInclude
    private Double speed;

    @Column(name = "crewSize")
    @JsonInclude
    private Integer crewSize;

    @Column(name = "rating")
    @JsonInclude
    private Double rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating() {
        double k = 1;
        if (this.isUsed) k = 0.5;
        this.rating = Math.round(((80 * this.speed * k) / (3020 - (this.getProdDate().getYear() + 1900))) * 100.0) / 100.0 ;
    }
}
