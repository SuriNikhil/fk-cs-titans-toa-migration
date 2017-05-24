package com.flipkart.cp.transact.toa.domain.entities.models.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flipkart.cp.transact.toa.domain.enums.EntityReferenceType;
import com.flipkart.cp.transact.toa.domain.enums.SubEntityReferenceType;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by padmanabh.kulkarni on 09/02/16.
 */
public class RemainingToaRequest {
    @NotNull
    @JsonProperty("reference_entity_type")
    private EntityReferenceType referenceEntityType;

    @NotNull
    @JsonProperty("reference_entity_id")
    private String referenceEntityId;

    @NotNull
    @JsonProperty("sub_entity_reference_type")
    private SubEntityReferenceType subEntityReferenceType;

    @NotNull
    @JsonProperty("sub_entity_reference_id_list")
    private List<String> subEntityReferenceIdList;

    public EntityReferenceType getReferenceEntityType() {
        return referenceEntityType;
    }

    public void setReferenceEntityType(EntityReferenceType referenceEntityType) {
        this.referenceEntityType = referenceEntityType;
    }

    public String getReferenceEntityId() {
        return referenceEntityId;
    }

    public void setReferenceEntityId(String referenceEntityId) {
        this.referenceEntityId = referenceEntityId;
    }

    public SubEntityReferenceType getSubEntityReferenceType() {
        return subEntityReferenceType;
    }

    public void setSubEntityReferenceType(SubEntityReferenceType subEntityReferenceType) {
        this.subEntityReferenceType = subEntityReferenceType;
    }

    public List<String> getSubEntityReferenceIdList() {
        return subEntityReferenceIdList;
    }

    public void setSubEntityReferenceIdList(List<String> subEntityReferenceId) {
        this.subEntityReferenceIdList = subEntityReferenceId;
    }
}
