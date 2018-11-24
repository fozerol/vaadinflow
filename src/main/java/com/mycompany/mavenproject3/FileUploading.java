/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

import com.mycompany.mavenproject3.entity.FileContainer;

/**
 *
 * @author fatih
 */
public interface FileUploading {
    void setFileContainer(FileContainer filecontainer);
    FileContainer getFileContainer();
}
