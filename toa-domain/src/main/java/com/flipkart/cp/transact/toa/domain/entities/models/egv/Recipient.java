package com.flipkart.cp.transact.toa.domain.entities.models.egv;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public class Recipient {
    private String medium;
    private String format;
    private String status;
    private String email;
    private String mobile;
    private String type;
    private boolean walletInfoRequired;
    private DeliveryParameters deliveryParameters;

    public String toString(){
        if(this == null){
            return null;
        }

        String params = null;
        if(this.deliveryParameters != null){
             params = deliveryParameters.toString();
        }

        return "{\n" +
                "    \"medium\" : "+ this.medium +",\n" +
                "    \"format\" : "+ this.format +",\n" +
                "    \"status\" : "+ this.status +",\n" +
                "    \"email\" : "+ this.email +",\n" +
                "    \"mobile\" : "+ this.mobile +",\n" +
                "    \"type\" : "+ this.type +",\n" +
                "    \"walletInfoRequired\" : "+ this.walletInfoRequired +" ,\n" +
                "    \"deliveryParameters\" : "+ params +" \n" +
                "}";
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DeliveryParameters getDeliveryParameters() {
        return deliveryParameters;
    }

    public void setDeliveryParameters(DeliveryParameters deliveryParameters) {
        this.deliveryParameters = deliveryParameters;
    }

    public boolean isWalletInfoRequired() {
        return walletInfoRequired;
    }

    public void setWalletInfoRequired(boolean walletInfoRequired) {
        this.walletInfoRequired = walletInfoRequired;
    }
}
