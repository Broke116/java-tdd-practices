package com.amadeus.training.tdd;

import java.util.Objects;

public class MemberDao {
    private final String flyerID;
    private final String name;
    private volatile int miles; // for concurrent applications. it informs all other threads as soon as the value changes

    MemberDao(String flyerID, String name, int miles) {
        this.flyerID = flyerID;
        this.name = name;
        this.miles = miles;
    }

    String getFlyerID() {
        return flyerID;
    }

    String getName() {
        return name;
    }

    int getMiles() {
        return miles;
    }

    void setMiles(int miles) { this.miles = miles; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDao memberDao = (MemberDao) o;
        return getMiles() == memberDao.getMiles() &&
                Objects.equals(getFlyerID(), memberDao.getFlyerID()) &&
                Objects.equals(getName(), memberDao.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFlyerID(), getName(), getMiles());
    }
}
