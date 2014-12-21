
package com.example.yakovlev_golani.summerbreeze.models.currentweatherforlocation;

import java.util.ArrayList;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class CurrentWeatherForLocation {

    @Expose
    private String message;
    @Expose
    private String cod;
    @Expose
    private Integer count;
    @Expose
    private java.util.List<WeatherLocations> list = new ArrayList<WeatherLocations>();

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
     *     The count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<WeatherLocations> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<WeatherLocations> list) {
        this.list = list;
    }

}
