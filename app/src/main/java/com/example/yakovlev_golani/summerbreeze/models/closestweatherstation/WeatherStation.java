
package com.example.yakovlev_golani.summerbreeze.models.closestweatherstation;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class WeatherStation {

    @Expose
    private Station station;
    @Expose
    private Double distance;

    /**
     * 
     * @return
     *     The station
     */
    public Station getStation() {
        return station;
    }

    /**
     * 
     * @param station
     *     The station
     */
    public void setStation(Station station) {
        this.station = station;
    }

    /**
     * 
     * @return
     *     The distance
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * 
     * @param distance
     *     The distance
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
