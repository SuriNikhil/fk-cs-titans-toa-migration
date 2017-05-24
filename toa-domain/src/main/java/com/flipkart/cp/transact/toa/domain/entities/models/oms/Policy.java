package com.flipkart.cp.transact.toa.domain.entities.models.oms;

public class Policy {

    public static enum Type{
        RETURN_POLICY,
        CANCELLATION_POLICY;
    }

	public int id;
	public String name;
    public Type type;
}
