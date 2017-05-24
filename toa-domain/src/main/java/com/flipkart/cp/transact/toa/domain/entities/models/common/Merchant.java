package com.flipkart.cp.transact.toa.domain.entities.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by padmanabh.kulkarni on 28/01/16.
 */
@Entity
@Table(name = "merchant")
public class Merchant {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @JsonProperty("name")
    private String name;

    @NotNull
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("description")
    private String description;

    private Integer status;
    @JsonProperty("created_date")
    @Column(name="created_date")
    private Date createdDate;

    @JsonProperty("updated_date")
    @Column(name="updated_date")
    private Date updatedDate;


    public Merchant(){
        super();
    }
    public String toString(){
        if(this == null){
            return null;
        }

        return "{\n" +
                "    \"id\" : "+ this.id +",\n" +
                "    \"name\": "+ this.name +" ,\n" +
                "    \"username\" : "+ null +",\n" +
                "    \"password\" : "+ null +",\n" +
                "    \"description\" : "+ this.description+" ,\n" +
                "    \"status\" : "+ this.status +" ,\n" +
                "    \"created_date\" : "+ this.createdDate +" ,\n" +
                "    \"updated_date\" : "+ this.updatedDate+"  \n" +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
