package com.paximum.storeproject.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("musicAlbum")
public class MusicAlbum extends Product{
    private String singerName;
    private int numberOfSongs;

    public MusicAlbum() {
    }

    public MusicAlbum(String name, String genre, LocalDate releaseDate, float basePrice, String singerName, int numberOfSongs) {
        super(name, genre, releaseDate, basePrice);
        this.singerName = singerName;
        this.numberOfSongs = numberOfSongs;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    @Override
    public float pricing(float basePrice) {
        return super.pricing(basePrice) * 0.95f;
    }
}
