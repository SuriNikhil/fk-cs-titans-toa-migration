package com.flipkart.cp.transact.toa.domain.entities.models.oms;

public class PolicyGroup {

	private String entity;
	private String entity_id;
	private String entity_id_string;
	private int policy_id;
	private String policy_type; //TODO make this enum
    private String group_id;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public String getEntity_id_string() {
		return entity_id_string;
	}

	public void setEntity_id_string(String entity_id_string) {
		this.entity_id_string = entity_id_string;
	}

	public int getPolicy_id() {
		return policy_id;
	}

	public void setPolicy_id(int policy_id) {
		this.policy_id = policy_id;
	}

	public String getPolicy_type() {
		return policy_type;
	}

	public void setPolicy_type(String policy_type) {
		this.policy_type = policy_type;
	}

	public String getGroup_id() {
		return group_id;
	}

	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
}
