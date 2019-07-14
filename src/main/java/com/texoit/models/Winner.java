package com.texoit.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Pedro
 */
public class Winner implements Serializable {

    private String producer;
    private Integer previousWin;
    private Integer followingWin;
    private Integer interval;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getInterval() {
        return interval != null
                ? interval
                : calcularInterval();
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }

    private Integer calcularInterval() {
        return getFollowingWin() != null && getPreviousWin() != null
                ? getFollowingWin() - getPreviousWin()
                : null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.producer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Winner)) {
            return false;
        }
        final Winner other = (Winner) obj;
        return Objects.equals(this.producer, other.producer);
    }

}
