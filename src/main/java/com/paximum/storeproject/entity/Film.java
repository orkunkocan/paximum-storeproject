package com.paximum.storeproject.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("film")
public class Film extends Product{

    private String directorName;
    private float IMDBScore;

    public Film() {
    }

    public Film(String name, String genre, LocalDate releaseDate, float basePrice, String directorName, float IMDBScore) {
        super(name, genre, releaseDate, basePrice);
        this.directorName = directorName;
        this.IMDBScore = IMDBScore;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public float getIMDBScore() {
        return IMDBScore;
    }

    public void setIMDBScore(float IMDBScore) {
        this.IMDBScore = IMDBScore;
    }

    @Override
    public float pricing(float basePrice) {
        return super.pricing(basePrice) * 1.15f;
    }
}
