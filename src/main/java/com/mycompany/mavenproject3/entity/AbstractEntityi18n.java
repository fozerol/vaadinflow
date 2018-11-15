/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import static com.mycompany.mavenproject3.AuthService.getLanguage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fatih
 */
@MappedSuperclass
public abstract class AbstractEntityi18n implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    

    public AbstractEntityi18n() {
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    
    @Version
    private int version;

    @NotNull
    @Size(min = 1, max = 25)
    @Column(unique = true)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        
        
        if (text.get(getLanguage().getLanguage()) == null)
            return code;
        return  text.get(getLanguage().getLanguage());
    }

    public void setName(String name) {
       text.put(getLanguage().getLanguage(), name);
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }
    
    
    
    
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "i18n", foreignKey = @ForeignKey(name = "none"), joinColumns = @JoinColumn(name = "code",referencedColumnName = "code") )
    @MapKeyColumn(name = "locale")
    @Column(name = "text")
    public Map<String, String> text = new HashMap<>();

    
    public Long getId() {
        return id;
    }
    
    

    protected void setId(Long id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(this.id == null) {
            return false;
        }

        if (obj instanceof AbstractEntityi18n && obj.getClass().equals(getClass())) {
            return this.id.equals(((AbstractEntityi18n) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
}
