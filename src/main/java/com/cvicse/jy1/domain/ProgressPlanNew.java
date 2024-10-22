package com.cvicse.jy1.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "progress_plan_new")
public class ProgressPlanNew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "progress_plan_new_seq")
    @SequenceGenerator(name = "progress_plan_new_seq", sequenceName = "progress_plan_new_seq", allocationSize = 1)
    private Long id;

    @Column(name = "planname", nullable = false)
    private String planname;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "plantype")
    private String plantype;

    @Column(name = "planlevel")
    private String planlevel;

    @Column(name = "time")
    private Date time;

    @Column(name = "planendtime")
    private Date planendtime;

    @Column(name = "responsiblepersonname")
    private String responsiblepersonname;

    @Column(name = "responsiblepersonid")
    private String responsiblepersonid;

    @Column(name = "responsibledpartmentname")
    private String responsibledpartmentname;

    @Column(name = "responsibledpartmentid")
    private String responsibledpartmentid;


    public String toString() {
        return "ProgressPlanNew{" +
                "id=" + id +
                ", planname='" + planname + '\'' +
                ", wbsname='" + wbsname + '\'' +
                ", wbsid='" + wbsid + '\'' +
                ", plantype='" + plantype + '\'' +
                ", planlevel=" + planlevel +
                ", time=" + time +'\'' +
                ", planendtime=" + planendtime +
                ", responsiblepersonname='" + responsiblepersonname + '\'' +
                ", responsiblepersonid='" + responsiblepersonid + '\'' +
                ", responsibledpartmentname='" + responsibledpartmentname + '\'' +
                ", responsibledpartmentid='" + responsibledpartmentid + '\'' +
                '}';
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanname() {
        return planname;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public String getWbsname() {
        return wbsname;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getWbsid() {
        return wbsid;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getPlantype() {
        return plantype;
    }

    public void setPlantype(String plantype) {
        this.plantype = plantype;
    }

    public String getPlanlevel() {
        return planlevel;
    }

    public void setPlanlevel(String planlevel) {
        this.planlevel = planlevel;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getPlanendtime() {
        return planendtime;
    }

    public void setPlanendtime(Date planendtime) {
        this.planendtime = planendtime;
    }

    public String getResponsiblepersonname() {
        return responsiblepersonname;
    }

    public void setResponsiblepersonname(String responsiblepersonname) {
        this.responsiblepersonname = responsiblepersonname;
    }

    public String getResponsiblepersonid() {
        return responsiblepersonid;
    }

    public void setResponsiblepersonid(String responsiblepersonid) {
        this.responsiblepersonid = responsiblepersonid;
    }

    public String getResponsibledpartmentname() {
        return responsibledpartmentname;
    }

    public void setResponsibledpartmentname(String responsibledpartmentname) {
        this.responsibledpartmentname = responsibledpartmentname;
    }

    public String getResponsibledpartmentid() {
        return responsibledpartmentid;
    }

    public void setResponsibledpartmentid(String responsibledpartmentid) {
        this.responsibledpartmentid = responsibledpartmentid;
    }
}