package br.edu.ifsuldeminas.mch.trabalhocinemapdi.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CinemaBranch implements Serializable {

    private String id;
    private String name;
    private String location;
    private String group;


    public CinemaBranch() {
    }

    public CinemaBranch(String id, String name, String location, String group) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String address) {
        this.location = address;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @NonNull
    @Override
    public String toString() {return name + " - " + location + " - " + group;}
}
