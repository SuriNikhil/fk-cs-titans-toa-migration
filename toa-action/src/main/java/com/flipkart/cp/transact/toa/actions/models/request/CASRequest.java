package com.flipkart.cp.transact.toa.actions.models.request;

/**
 * Created by padmanabh.kulkarni on 28/07/15.
 */
public class CASRequest {
    private String userLogin;

    public CASRequest(String userLogin){
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
