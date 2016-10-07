/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosFacadeLocal;
import com.sv.udb.modelo.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Owner
 */
@Named(value = "usuariosBean")
@ViewScoped
public class UsuariosBean implements Serializable {

    @EJB
    private UsuariosFacadeLocal FCDEUsuarios;
    private Usuarios objeUsua;
    private List<Usuarios> listUsua;

    public UsuariosFacadeLocal getFCDEUsuarios() {
        return FCDEUsuarios;
    }

    public void setFCDEUsuarios(UsuariosFacadeLocal FCDEUsuarios) {
        this.FCDEUsuarios = FCDEUsuarios;
    }

    public Usuarios getObjeUsua() {
        return objeUsua;
    }

    public void setObjeUsua(Usuarios objeUsua) {
        this.objeUsua = objeUsua;
    }

    public List<Usuarios> getListUsua() {
        return listUsua;
    }

    public void setListUsua(List<Usuarios> listUsua) {
        this.listUsua = listUsua;
    }
    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.consTodo();
    }
    
    public void consTodo()
    {
        try
        {
            this.listUsua = FCDEUsuarios.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
}
