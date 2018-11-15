/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.entity;

import com.mycompany.mavenproject3.Language;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author fatih
 */
//@Entity(name = "i18n")
public class I18n extends AbstractEntityi18n {
    @NotNull
    @Size(min = 1, max = 255)
    private String translation;
    @NotNull
    @Size(min = 1, max = 255)
    private String     code;
    
    @NotNull
    @Size(min = 1, max = 255)
    private Language language;
    

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public I18n(String translation, String code, Language language) {
        this.translation = translation;
        this.code = code;
        this.language = language;
    }

    public I18n() {
    }
}
