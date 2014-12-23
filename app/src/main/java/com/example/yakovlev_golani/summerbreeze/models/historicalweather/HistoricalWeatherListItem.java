
package com.example.yakovlev_golani.summerbreeze.models.historicalweather;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class HistoricalWeatherListItem {

    @Expose
    private HistoricalItemMain main;
    @Expose
    private Integer dt;

    /**
     * 
     * @return
     *     The main
     */
    public HistoricalItemMain getMain() {
        return main;
    }

    /**
     * 
     * @param main
     *     The main
     */
    public void setMain(HistoricalItemMain main) {
        this.main = main;
    }

    /**
     * 
     * @return
     *     The dt
     */
    public Integer getDt() {
        return dt;
    }

    /**
     * 
     * @param dt
     *     The dt
     */
    public void setDt(Integer dt) {
        this.dt = dt;
    }

}
