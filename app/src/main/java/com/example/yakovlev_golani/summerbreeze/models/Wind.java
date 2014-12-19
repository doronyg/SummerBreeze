
package com.example.yakovlev_golani.summerbreeze.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Wind {

    @Expose
    private Double speed;
    @Expose
    private Integer deg;
    @SerializedName("var_beg")
    @Expose
    private Integer varBeg;
    @SerializedName("var_end")
    @Expose
    private Integer varEnd;

    /**
     * 
     * @return
     *     The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public Integer getDeg() {
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    /**
     * 
     * @return
     *     The varBeg
     */
    public Integer getVarBeg() {
        return varBeg;
    }

    /**
     * 
     * @param varBeg
     *     The var_beg
     */
    public void setVarBeg(Integer varBeg) {
        this.varBeg = varBeg;
    }

    /**
     * 
     * @return
     *     The varEnd
     */
    public Integer getVarEnd() {
        return varEnd;
    }

    /**
     * 
     * @param varEnd
     *     The var_end
     */
    public void setVarEnd(Integer varEnd) {
        this.varEnd = varEnd;
    }

}
