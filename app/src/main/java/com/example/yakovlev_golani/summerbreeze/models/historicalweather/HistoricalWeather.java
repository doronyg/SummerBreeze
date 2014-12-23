
package com.example.yakovlev_golani.summerbreeze.models.historicalweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class HistoricalWeather {

    @Expose
    private String message;
    @Expose
    private String cod;
    @Expose
    private String type;
    @SerializedName("station_id")
    @Expose
    private Integer stationId;
    @Expose
    private Double calctime;
    @Expose
    private Integer cnt;
    @Expose
    private java.util.List<HistoricalWeatherListItem> list = new ArrayList<HistoricalWeatherListItem>();

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * 
     * @param cod
     *     The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The stationId
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * 
     * @param stationId
     *     The station_id
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * 
     * @return
     *     The calctime
     */
    public Double getCalctime() {
        return calctime;
    }

    /**
     * 
     * @param calctime
     *     The calctime
     */
    public void setCalctime(Double calctime) {
        this.calctime = calctime;
    }

    /**
     * 
     * @return
     *     The cnt
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * 
     * @param cnt
     *     The cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<HistoricalWeatherListItem> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<HistoricalWeatherListItem> list) {
        this.list = list;
    }

}
