/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.material;

import com.mycompany.mavenproject3.entity.vehicle.*;
import com.mycompany.mavenproject3.entity.*;
import com.mycompany.mavenproject3.entity.genericdefination.DefinationClass;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author fatih
 */
@Entity
@Table(name = "materials")
@NamedQueries({
@NamedQuery(name="Material.findAll", query="SELECT e FROM Material e where 1=1"),
@NamedQuery(name="Material.countAll", query="SELECT COUNT(e) FROM Material e where 1=1"),
@NamedQuery(name="Material.findAllByCompany", query="SELECT e FROM Material e where e.company = :company")
})
@NamedNativeQuery(name="Material.findAllWithTranslation",query = "SELECT s.id,s.code, case when ?languageid = 1 then translation1 when "
        + "?languageid = 2 then translation2 else code end as name ,s.translation1,translation2,version FROM customertypes s",resultClass = Material.class)



public class Material extends AbstractCompanyEntity implements Serializable {
            
            private String code;
            private String gcode;
            @OneToOne
            @JoinColumn(name = "materialclassid")
            private MaterialClass materialClass;
            @OneToOne
            @JoinColumn(name = "materialgroupid")
            private MaterialGroup materialGroup;
            private int taxrate;
            private float netweight;
            private float grossweight;
            @OneToOne
            @JoinColumn(name = "unitid")
            private Unit unit;
            private float minOrderQuantity;
            private String hierarchy;
            private String previousCode;
            private boolean dfx;
            private int dfxdiscount;
            private float dfxMaxOrderQuantity;
            private float dfxStockQuantity;
            private float dfxMinStockQuantity;

    public int getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(int taxrate) {
        this.taxrate = taxrate;
    }

    public float getNetweight() {
        return netweight;
    }

    public void setNetweight(float netweight) {
        this.netweight = netweight;
    }

    public float getGrossweight() {
        return grossweight;
    }

    public void setGrossweight(float grossweight) {
        this.grossweight = grossweight;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public float getMinOrderQuantity() {
        return minOrderQuantity;
    }

    public void setMinOrderQuantity(float minOrderQuantity) {
        this.minOrderQuantity = minOrderQuantity;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getPreviousCode() {
        return previousCode;
    }

    public void setPreviousCode(String previousCode) {
        this.previousCode = previousCode;
    }

    public boolean isDfx() {
        return dfx;
    }

    public void setDfx(boolean dfx) {
        this.dfx = dfx;
    }

    public int getDfxdiscount() {
        return dfxdiscount;
    }

    public void setDfxdiscount(int dfxdiscount) {
        this.dfxdiscount = dfxdiscount;
    }

    public float getDfxMaxOrderQuantity() {
        return dfxMaxOrderQuantity;
    }

    public void setDfxMaxOrderQuantity(float dfxMaxOrderQuantity) {
        this.dfxMaxOrderQuantity = dfxMaxOrderQuantity;
    }

    public float getDfxStockQuantity() {
        return dfxStockQuantity;
    }

    public void setDfxStockQuantity(float dfxStockQuantity) {
        this.dfxStockQuantity = dfxStockQuantity;
    }

    public float getDfxMinStockQuantity() {
        return dfxMinStockQuantity;
    }

    public void setDfxMinStockQuantity(float dfxMinStockQuantity) {
        this.dfxMinStockQuantity = dfxMinStockQuantity;
    }
            
    public Material() {
        
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGcode() {
        return gcode;
    }

    public void setGcode(String gcode) {
        this.gcode = gcode;
    }

    public MaterialClass getMaterialClass() {
        return materialClass;
    }

    public void setMaterialClass(MaterialClass materialClass) {
        this.materialClass = materialClass;
    }

    public MaterialGroup getMaterialGroup() {
        return materialGroup;
    }

    public void setMaterialGroup(MaterialGroup materialGroup) {
        this.materialGroup = materialGroup;
    }
    
}
