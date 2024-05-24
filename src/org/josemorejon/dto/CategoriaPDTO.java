/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.josemorejon.dto;

import org.josemorejon.model.CategoriaP;

/**
 *
 * @author josec
 */
public class CategoriaPDTO {
    private static CategoriaPDTO instance;
    private CategoriaP categoriaP;
    
    private CategoriaPDTO(){
    
    }
    
    public static CategoriaPDTO getCategoriaPDTO(){
        if(instance == null){
            instance = new CategoriaPDTO();
        }
        
        return instance;
    }

    public CategoriaP getCategoriaP() {
        return categoriaP;
    }

    public void setCategoriaP(CategoriaP categoriaP) {
        this.categoriaP = categoriaP;
    }  
}
