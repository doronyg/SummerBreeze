
package com.example.yakovlev_golani.summerbreeze.models.historicalweather;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Temp {

    @Expose
    private Double v;
    @Expose
    private Integer c;
    @Expose
    private Double mi;
    @Expose
    private Double ma;

    /**
     * 
     * @return
     *     The v
     */
    public Double getV() {
        return v;
    }

    /**
     * 
     * @param v
     *     The v
     */
    public void setV(Double v) {
        this.v = v;
    }

    /**
     * 
     * @return
     *     The c
     */
    public Integer getC() {
        return c;
    }

    /**
     * 
     * @param c
     *     The c
     */
    public void setC(Integer c) {
        this.c = c;
    }

    /**
     * 
     * @return
     *     The mi
     */
    public Double getMi() {
        return mi;
    }

    /**
     * 
     * @param mi
     *     The mi
     */
    public void setMi(Double mi) {
        this.mi = mi;
    }

    /**
     * 
     * @return
     *     The ma
     */
    public Double getMa() {
        return ma;
    }

    /**
     * 
     * @param ma
     *     The ma
     */
    public void setMa(Double ma) {
        this.ma = ma;
    }

}
