package com.flipkart.cp.transact.toa.domain.entities.models.egv;

/**
 * Created by padmanabh.kulkarni on 26/01/16.
 */
public class DeliveryParameters {
    private String senderName;
    private String senderEmail;
    private String beneficiaryName;
    private String greetingMessage;
    private String imageURL;

    public String toString(){
        if(this == null){
            return null;
        }

        return "{\n" +
                "    \"senderName\" : "+ this.senderName +",\n" +
                "    \"senderEmail\" : "+ this.senderEmail +",\n" +
                "    \"beneficiaryName\" : "+ this.beneficiaryName +",\n" +
                "    \"greetingMessage\" : "+ this.greetingMessage +",\n" +
                "    \"imageURL\" :  "+ this.imageURL +"\n" +
                "}";
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
