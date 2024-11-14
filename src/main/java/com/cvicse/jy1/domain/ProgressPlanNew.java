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

    @Column(name = "plantype")
    private String plantype;

    @Column(name = "planlevel")
    private String planlevel;

    @Column(name = "time")
    private Date time;

    @Column(name = "planendtime")
    private Date planendtime;

    @Column(name = "plancontent")
    private String plancontent;

    @Column(name = "preplanname")
    private String preplanname;

    @Column(name = "deliverable")
    private String deliverable;

    // 与WBS关联
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wbsid")
    private Projectwbs projectwbs;

    // 与人员关联-责任人
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsiblepersonid")
    private Officers responsibleperson;

    // 与部门关联-责任部门
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "responsibledepartmentid")
    private Department responsibledepartment;

    // 与人员关联-配合人
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coordinatorid")
    private Officers coordinator;

    // 与部门关联-配合部门
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coordinatedepartmentid")
    private Department coordinatedepartment;
    

    public String toString() {
        return "ProgressPlanNew{" +
                "id=" + id +
                ", planname='" + planname + '\'' +
                ", plantype='" + plantype + '\'' +
                ", planlevel=" + planlevel +
                ", time=" + time +'\'' +
                ", planendtime=" + planendtime +
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

    public String getPlancontent() {
        return plancontent;
    }

    public void setPlancontent(String plancontent) {
        this.plancontent = plancontent;
    }

    public String getPreplanname() {
        return preplanname;
    }

    public void setPreplanname(String preplanname) {
        this.preplanname = preplanname;
    }
    public String getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(String deliverable) {
        this.deliverable = deliverable;
    }

    public Projectwbs getProjectwbs() {
        return projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }
    public Officers getResponsibleperson() {
        return responsibleperson;
    }

    public void setResponsibleperson(Officers responsibleperson) {
        this.responsibleperson = responsibleperson;
    }

    public Department getResponsibledepartment() {
        return responsibledepartment;
    }

    public void setResponsibledepartment(Department responsibledepartment) {
        this.responsibledepartment = responsibledepartment;
    }

    public Officers getCoordinator() {
        return coordinator;
    }
    public void setCoordinator(Officers coordinator) {
        this.coordinator = coordinator;
    }

    public Department getCoordinatedepartment() {
        return coordinatedepartment;
    }

    public void setCoordinatedepartment(Department coordinatedepartment) {
        this.coordinatedepartment = coordinatedepartment;
    }
    
    
}