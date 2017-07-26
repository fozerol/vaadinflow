/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3.appdao;

import com.mycompany.mavenproject3.entity.TreeViewConfig;
import genericdao.GenericDaoImp;
import java.util.List;
import javax.ejb.Stateless;


/**
 *
 * @author fatih
 */
@Stateless
public class TreeViewDao extends GenericDaoImp<TreeViewConfig> {
    public TreeViewDao()
    {
        super.setType(TreeViewConfig.class);
    }
    public List<TreeViewConfig> findByNode(Integer node){
         List<TreeViewConfig> result = super.em.createNamedQuery("TreeViewConfig.findByNode").setParameter("hierarchy", node.intValue()).getResultList();
        return result;
    }
}
