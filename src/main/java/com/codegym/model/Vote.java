package com.codegym.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vote {
    public static List<Integer> availableRate;
    static {
        availableRate = new ArrayList<>();
        availableRate.add(5);
        availableRate.add(4);
        availableRate.add(3);
        availableRate.add(2);
        availableRate.add(1);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rate;

    private String author;

    @Column(columnDefinition = "varchar(1000)")
    private String comment;

    private int likeCount;

    @Column(columnDefinition = "DATE")
    private String date;

    public Vote() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
