package com.cvicse.jy1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Documentmenu.
 */
@Entity
@Table(name = "documentmenu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Documentmenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "menuid", nullable = false)
    private String menuid;

    @Column(name = "belongtype")
    private String belongtype;

    @Column(name = "menuname")
    private String menuname;

    @Column(name = "parentmenuid")
    private String parentmenuid;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorid")
    private String creatorid;

    @Column(name = "creatorname")
    private String creatorname;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "filenum")
    private Integer filenum;

    @Column(name = "fileurl")
    private String fileurl;

    @Column(name = "departmentid")
    private String departmentid;

    @Column(name = "departmentname")
    private String departmentname;

    @Column(name = "spare_1")
    private LocalDate spare1;

    @Column(name = "spare_2")
    private Integer spare2;

    @Column(name = "spare_3")
    private String spare3;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Documentmenu id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuid() {
        return this.menuid;
    }

    public Documentmenu menuid(String menuid) {
        this.setMenuid(menuid);
        return this;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    public String getBelongtype() {
        return this.belongtype;
    }

    public Documentmenu belongtype(String belongtype) {
        this.setBelongtype(belongtype);
        return this;
    }

    public void setBelongtype(String belongtype) {
        this.belongtype = belongtype;
    }

    public String getMenuname() {
        return this.menuname;
    }

    public Documentmenu menuname(String menuname) {
        this.setMenuname(menuname);
        return this;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getParentmenuid() {
        return this.parentmenuid;
    }

    public Documentmenu parentmenuid(String parentmenuid) {
        this.setParentmenuid(parentmenuid);
        return this;
    }

    public void setParentmenuid(String parentmenuid) {
        this.parentmenuid = parentmenuid;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Documentmenu createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorid() {
        return this.creatorid;
    }

    public Documentmenu creatorid(String creatorid) {
        this.setCreatorid(creatorid);
        return this;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Documentmenu creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Integer getType() {
        return this.type;
    }

    public Documentmenu type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFilenum() {
        return this.filenum;
    }

    public Documentmenu filenum(Integer filenum) {
        this.setFilenum(filenum);
        return this;
    }

    public void setFilenum(Integer filenum) {
        this.filenum = filenum;
    }

    public String getFileurl() {
        return this.fileurl;
    }

    public Documentmenu fileurl(String fileurl) {
        this.setFileurl(fileurl);
        return this;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
    }

    public String getDepartmentid() {
        return this.departmentid;
    }

    public Documentmenu departmentid(String departmentid) {
        this.setDepartmentid(departmentid);
        return this;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return this.departmentname;
    }

    public Documentmenu departmentname(String departmentname) {
        this.setDepartmentname(departmentname);
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public LocalDate getSpare1() {
        return this.spare1;
    }

    public Documentmenu spare1(LocalDate spare1) {
        this.setSpare1(spare1);
        return this;
    }

    public void setSpare1(LocalDate spare1) {
        this.spare1 = spare1;
    }

    public Integer getSpare2() {
        return this.spare2;
    }

    public Documentmenu spare2(Integer spare2) {
        this.setSpare2(spare2);
        return this;
    }

    public void setSpare2(Integer spare2) {
        this.spare2 = spare2;
    }

    public String getSpare3() {
        return this.spare3;
    }

    public Documentmenu spare3(String spare3) {
        this.setSpare3(spare3);
        return this;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Documentmenu)) {
            return false;
        }
        return getId() != null && getId().equals(((Documentmenu) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Documentmenu{" +
            "id=" + getId() +
            ", menuid='" + getMenuid() + "'" +
            ", belongtype='" + getBelongtype() + "'" +
            ", menuname='" + getMenuname() + "'" +
            ", parentmenuid='" + getParentmenuid() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorid='" + getCreatorid() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", type=" + getType() +
            ", filenum=" + getFilenum() +
            ", fileurl='" + getFileurl() + "'" +
            ", departmentid='" + getDepartmentid() + "'" +
            ", departmentname='" + getDepartmentname() + "'" +
            ", spare1='" + getSpare1() + "'" +
            ", spare2=" + getSpare2() +
            ", spare3='" + getSpare3() + "'" +
            "}";
    }
}
