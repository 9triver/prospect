package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Secretlevel;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Events.
 */
@Entity
@Table(name = "events")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "jhi_time")
    private LocalDate time;

    @Column(name = "place")
    private String place;

    @Column(name = "participants")
    private String participants;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "attachment")
    private String attachment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Events id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Events title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public Events content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getTime() {
        return this.time;
    }

    public Events time(LocalDate time) {
        this.setTime(time);
        return this;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getPlace() {
        return this.place;
    }

    public Events place(String place) {
        this.setPlace(place);
        return this;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getParticipants() {
        return this.participants;
    }

    public Events participants(String participants) {
        this.setParticipants(participants);
        return this;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getPicture() {
        return this.picture;
    }

    public Events picture(String picture) {
        this.setPicture(picture);
        return this;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return this.description;
    }

    public Events description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Events secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public Events attachment(String attachment) {
        this.setAttachment(attachment);
        return this;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Events)) {
            return false;
        }
        return getId() != null && getId().equals(((Events) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Events{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", time='" + getTime() + "'" +
            ", place='" + getPlace() + "'" +
            ", participants='" + getParticipants() + "'" +
            ", picture='" + getPicture() + "'" +
            ", description='" + getDescription() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", attachment='" + getAttachment() + "'" +
            "}";
    }
}
