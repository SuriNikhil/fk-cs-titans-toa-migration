package com.flipkart.cp.transact.toa.domain.entities.models.toa;

import com.flipkart.cp.transact.toa.domain.enums.ChangeReason;
import com.flipkart.cp.transact.toa.domain.enums.ChangedAttribute;
import com.flipkart.cp.transact.toa.domain.enums.Event;
import com.flipkart.cp.transact.toa.domain.enums.Status;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 26/05/15.
 */
@Entity
@Table(name = "toa_history")
public class TOAHistory {

    private Integer id;
    private Integer toaId;
    private ChangedAttribute changedAttribute;
    private Event event;
    private Status newStatus;
    private String userLogin;
    private ChangeReason changeReason;
    private String changeSubReason;
    private Date changeTime;
    private String changeData;
    private Integer isBigfootProcessed;

    public TOAHistory(){}

    public TOAHistory(
            Integer id,
            Integer toaId,
            ChangedAttribute changedAttribute,
            Event event,
            Status newStatus,
            String userLogin,
            ChangeReason changeReason,
            String changeSubReason,
            Date changeTime,
            String changeData,
            Integer isBigfootProcessed){

        this.id = id;
        this.toaId = toaId;
        this.changedAttribute = changedAttribute;
        this.event =  event;
        this.newStatus = newStatus;
        this.userLogin = userLogin;
        this.changeReason = changeReason;
        this.changeSubReason = changeSubReason;
        this.changeTime = changeTime;
        this.changeData = changeData;
        this.isBigfootProcessed = isBigfootProcessed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToaId() {
        return toaId;
    }

    public void setToaId(Integer toaId) {
        this.toaId = toaId;
    }

    public ChangedAttribute getChangedAttribute() {
        return changedAttribute;
    }

    public void setChangedAttribute(ChangedAttribute changedAttribute) {
        this.changedAttribute = changedAttribute;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Status getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Status newStatus) {
        this.newStatus = newStatus;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public ChangeReason getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(ChangeReason changeReason) {
        this.changeReason = changeReason;
    }

    public String getChangeSubReason() {
        return changeSubReason;
    }

    public void setChangeSubReason(String changeSubReason) {
        this.changeSubReason = changeSubReason;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeData() {
        return changeData;
    }

    public void setChangeData(String changeData) {
        this.changeData = changeData;
    }

    public Integer getIsBigfootProcessed() {
        return isBigfootProcessed;
    }

    public void setIsBigfootProcessed(Integer isBigfootProcessed) {
        this.isBigfootProcessed = isBigfootProcessed;
    }
}
