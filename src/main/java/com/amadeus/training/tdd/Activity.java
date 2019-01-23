package com.amadeus.training.tdd;

import java.util.Date;

public class Activity {
    long id;
    Date insertDate;

    public Activity(long id, Date insertDate) {
        this.id = id;
        this.insertDate = insertDate;
    }

    public long getId() {
        return id;
    }

    public Date getInsertDate() {
        return insertDate;
    }
}
