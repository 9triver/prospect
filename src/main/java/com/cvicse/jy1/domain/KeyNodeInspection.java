package com.cvicse.jy1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A KeyNodeInspection.
 */
@Entity
@Table(name = "key_node_inspection")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class KeyNodeInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "workbagname")
    private String workbagname;

    @Column(name = "belongwbsid")
    private String belongwbsid;

    @Column(name = "projectlevel")
    private String projectlevel;

    @Column(name = "iskey")
    private String iskey;

    @Column(name = "isimplementationplan")
    private String isimplementationplan;

    @Column(name = "isqualityplan")
    private String isqualityplan;

    @Column(name = "istechniqueplan")
    private String istechniqueplan;

    @Column(name = "implementationplanstatus")
    private String implementationplanstatus;

    @Column(name = "isimplementationplanmaterial")
    private String isimplementationplanmaterial;

    @Column(name = "technologyplanstatus")
    private String technologyplanstatus;

    @Column(name = "istechnologymaterial")
    private String istechnologymaterial;

    @Column(name = "firstcheckstatus")
    private String firstcheckstatus;

    @Column(name = "isfirstcheckmaterial")
    private String isfirstcheckmaterial;

    @Column(name = "productioncheckstatus")
    private String productioncheckstatus;

    @Column(name = "isproductioncheckmaterial")
    private String isproductioncheckmaterial;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public KeyNodeInspection id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public KeyNodeInspection name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public KeyNodeInspection workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getWorkbagname() {
        return this.workbagname;
    }

    public KeyNodeInspection workbagname(String workbagname) {
        this.setWorkbagname(workbagname);
        return this;
    }

    public void setWorkbagname(String workbagname) {
        this.workbagname = workbagname;
    }

    public String getBelongwbsid() {
        return this.belongwbsid;
    }

    public KeyNodeInspection belongwbsid(String belongwbsid) {
        this.setBelongwbsid(belongwbsid);
        return this;
    }

    public void setBelongwbsid(String belongwbsid) {
        this.belongwbsid = belongwbsid;
    }

    public String getProjectlevel() {
        return this.projectlevel;
    }

    public KeyNodeInspection projectlevel(String projectlevel) {
        this.setProjectlevel(projectlevel);
        return this;
    }

    public void setProjectlevel(String projectlevel) {
        this.projectlevel = projectlevel;
    }

    public String getIskey() {
        return this.iskey;
    }

    public KeyNodeInspection iskey(String iskey) {
        this.setIskey(iskey);
        return this;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    public String getIsimplementationplan() {
        return this.isimplementationplan;
    }

    public KeyNodeInspection isimplementationplan(String isimplementationplan) {
        this.setIsimplementationplan(isimplementationplan);
        return this;
    }

    public void setIsimplementationplan(String isimplementationplan) {
        this.isimplementationplan = isimplementationplan;
    }

    public String getIsqualityplan() {
        return this.isqualityplan;
    }

    public KeyNodeInspection isqualityplan(String isqualityplan) {
        this.setIsqualityplan(isqualityplan);
        return this;
    }

    public void setIsqualityplan(String isqualityplan) {
        this.isqualityplan = isqualityplan;
    }

    public String getIstechniqueplan() {
        return this.istechniqueplan;
    }

    public KeyNodeInspection istechniqueplan(String istechniqueplan) {
        this.setIstechniqueplan(istechniqueplan);
        return this;
    }

    public void setIstechniqueplan(String istechniqueplan) {
        this.istechniqueplan = istechniqueplan;
    }

    public String getImplementationplanstatus() {
        return this.implementationplanstatus;
    }

    public KeyNodeInspection implementationplanstatus(String implementationplanstatus) {
        this.setImplementationplanstatus(implementationplanstatus);
        return this;
    }

    public void setImplementationplanstatus(String implementationplanstatus) {
        this.implementationplanstatus = implementationplanstatus;
    }

    public String getIsimplementationplanmaterial() {
        return this.isimplementationplanmaterial;
    }

    public KeyNodeInspection isimplementationplanmaterial(String isimplementationplanmaterial) {
        this.setIsimplementationplanmaterial(isimplementationplanmaterial);
        return this;
    }

    public void setIsimplementationplanmaterial(String isimplementationplanmaterial) {
        this.isimplementationplanmaterial = isimplementationplanmaterial;
    }

    public String getTechnologyplanstatus() {
        return this.technologyplanstatus;
    }

    public KeyNodeInspection technologyplanstatus(String technologyplanstatus) {
        this.setTechnologyplanstatus(technologyplanstatus);
        return this;
    }

    public void setTechnologyplanstatus(String technologyplanstatus) {
        this.technologyplanstatus = technologyplanstatus;
    }

    public String getIstechnologymaterial() {
        return this.istechnologymaterial;
    }

    public KeyNodeInspection istechnologymaterial(String istechnologymaterial) {
        this.setIstechnologymaterial(istechnologymaterial);
        return this;
    }

    public void setIstechnologymaterial(String istechnologymaterial) {
        this.istechnologymaterial = istechnologymaterial;
    }

    public String getFirstcheckstatus() {
        return this.firstcheckstatus;
    }

    public KeyNodeInspection firstcheckstatus(String firstcheckstatus) {
        this.setFirstcheckstatus(firstcheckstatus);
        return this;
    }

    public void setFirstcheckstatus(String firstcheckstatus) {
        this.firstcheckstatus = firstcheckstatus;
    }

    public String getIsfirstcheckmaterial() {
        return this.isfirstcheckmaterial;
    }

    public KeyNodeInspection isfirstcheckmaterial(String isfirstcheckmaterial) {
        this.setIsfirstcheckmaterial(isfirstcheckmaterial);
        return this;
    }

    public void setIsfirstcheckmaterial(String isfirstcheckmaterial) {
        this.isfirstcheckmaterial = isfirstcheckmaterial;
    }

    public String getProductioncheckstatus() {
        return this.productioncheckstatus;
    }

    public KeyNodeInspection productioncheckstatus(String productioncheckstatus) {
        this.setProductioncheckstatus(productioncheckstatus);
        return this;
    }

    public void setProductioncheckstatus(String productioncheckstatus) {
        this.productioncheckstatus = productioncheckstatus;
    }

    public String getIsproductioncheckmaterial() {
        return this.isproductioncheckmaterial;
    }

    public KeyNodeInspection isproductioncheckmaterial(String isproductioncheckmaterial) {
        this.setIsproductioncheckmaterial(isproductioncheckmaterial);
        return this;
    }

    public void setIsproductioncheckmaterial(String isproductioncheckmaterial) {
        this.isproductioncheckmaterial = isproductioncheckmaterial;
    }

    public String getStatus() {
        return this.status;
    }

    public KeyNodeInspection status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KeyNodeInspection)) {
            return false;
        }
        return getId() != null && getId().equals(((KeyNodeInspection) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KeyNodeInspection{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", workbagname='" + getWorkbagname() + "'" +
            ", belongwbsid='" + getBelongwbsid() + "'" +
            ", projectlevel='" + getProjectlevel() + "'" +
            ", iskey='" + getIskey() + "'" +
            ", isimplementationplan='" + getIsimplementationplan() + "'" +
            ", isqualityplan='" + getIsqualityplan() + "'" +
            ", istechniqueplan='" + getIstechniqueplan() + "'" +
            ", implementationplanstatus='" + getImplementationplanstatus() + "'" +
            ", isimplementationplanmaterial='" + getIsimplementationplanmaterial() + "'" +
            ", technologyplanstatus='" + getTechnologyplanstatus() + "'" +
            ", istechnologymaterial='" + getIstechnologymaterial() + "'" +
            ", firstcheckstatus='" + getFirstcheckstatus() + "'" +
            ", isfirstcheckmaterial='" + getIsfirstcheckmaterial() + "'" +
            ", productioncheckstatus='" + getProductioncheckstatus() + "'" +
            ", isproductioncheckmaterial='" + getIsproductioncheckmaterial() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
