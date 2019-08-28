package com.caiya.serialization;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlType(propOrder = {"name"})
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name = "user")
//@XmlSeeAlso
public class User implements Serializable {

    private static final long serialVersionUID = 5446326261249843062L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}