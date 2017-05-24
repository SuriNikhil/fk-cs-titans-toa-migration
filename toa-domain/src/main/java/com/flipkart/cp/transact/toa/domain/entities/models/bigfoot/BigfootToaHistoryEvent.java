package com.flipkart.cp.transact.toa.domain.entities.models.bigfoot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.seraph.exceptions.EmptyStringException;
import com.flipkart.seraph.exceptions.NullAttributeException;
import com.flipkart.seraph.schema.BaseSchema;
import com.flipkart.seraph.schema.validator.DateTimeValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by padmanabh.kulkarni on 22/02/16.
 */
public class BigfootToaHistoryEvent extends BaseSchema {
    @JsonProperty("change_data")
    private String changeData;

    @JsonProperty("change_reason")
    private String changeReason;

    @JsonProperty("toa_history_id")
    private Long toaHistoryId;

    @JsonProperty("user_login")
    private String userLogin;

    @JsonProperty("new_status")
    private String newStatus;

    @JsonProperty("event")
    private String event;

    @JsonProperty("change_sub_reason")
    private String changeSubReason;

    @JsonProperty("change_time")
    private String changeTime;

    @JsonProperty("changed_attribute")
    private String changedAttribute;

    @JsonProperty("toa_id")
    private Long toaId;

    @JsonIgnore
    private String schemaVersion = "1.0";


    public String getChangeData() {
        return this.changeData;
    }

    public String getChangeReason() {
        return this.changeReason;
    }

    public Long getToaHistoryId() {
        return this.toaHistoryId;
    }

    public String getUserLogin() {
        return this.userLogin;
    }

    public String getNewStatus() {
        return this.newStatus;
    }

    public String getEvent() {
        return this.event;
    }

    public String getChangeSubReason() {
        return this.changeSubReason;
    }

    public String getChangeTime() {
        return this.changeTime;
    }

    public String getChangedAttribute() {
        return this.changedAttribute;
    }

    public Long getToaId() {
        return this.toaId;
    }

    public void setChangeData(String changeData) {
        this.changeData = changeData;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public void setToaHistoryId(Long toaHistoryId) {
        this.toaHistoryId = toaHistoryId;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setChangeSubReason(String changeSubReason) {
        this.changeSubReason = changeSubReason;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public void setChangedAttribute(String changedAttribute) {
        this.changedAttribute = changedAttribute;
    }

    public void setToaId(Long toaId) {
        this.toaId = toaId;
    }

    @JsonIgnore
    public String getSchemaVersion() {
        return this.schemaVersion;
    }

    @Override
    public void unSetSchemaVersion() {

    }


    private void validateToa_history_id() throws Exception {
        if (this.toaHistoryId == null) {
            throw new NullAttributeException("toaHistoryId");
        }
    }

    private void validateNew_status() throws Exception {
        if (this.newStatus == null) {
            throw new NullAttributeException("new_status");
        }
        if (newStatus.isEmpty()) {
            throw new EmptyStringException("new_status");
        }
    }

    private void validateChange_time() throws Exception {
        if (this.changeTime == null) {
            throw new NullAttributeException("change_time");
        }
        Map<String, Object> dateTimeValidatorParams = new HashMap<String, Object>();
        DateTimeValidator dateTimeValidator = new DateTimeValidator(dateTimeValidatorParams);
        dateTimeValidator.validate("change_time", this.changeTime);

    }

    private void validateChanged_attribute() throws Exception {
        if (this.changedAttribute == null) {
            throw new NullAttributeException("changed_attribute");
        }
        if (changedAttribute.isEmpty()) {
            throw new EmptyStringException("changed_attribute");
        }
    }

    private void validateToa_id() throws Exception {
        if (this.toaId == null) {
            throw new NullAttributeException("toa_id");
        }
    }


    public void validate() throws Exception {
        validateToa_history_id();
        validateNew_status();
        validateChange_time();
        validateChanged_attribute();
        validateToa_id();

    }

}
