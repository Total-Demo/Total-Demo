package com.barry.activiti.pojo;

import java.io.Serializable;
import java.util.Date;


public class Evection implements Serializable {

    private Long id;

    private String evectionName;

    private Double num;

    private Date beginDate;
    private Date endDate;

    //目的地
    private String destination;

    private String reson;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEvectionName(String evectionName) {
        this.evectionName = evectionName;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public Long getId() {
        return id;
    }

    public String getEvectionName() {
        return evectionName;
    }

    public Double getNum() {
        return num;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDestination() {
        return destination;
    }

    public String getReson() {
        return reson;
    }
}
