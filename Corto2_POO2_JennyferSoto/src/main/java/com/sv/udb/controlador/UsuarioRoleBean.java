/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuariosRolesFacadeLocal;
import com.sv.udb.modelo.UsuariosRoles;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Owner
 */
@Named(value = "usuarioRoleBean")
@ViewScoped
public class UsuarioRoleBean implements Serializable {

    @EJB
    private UsuariosRolesFacadeLocal FCDEUsuarioRole;
    private UsuariosRoles objeUsuaRole;
    private List<UsuariosRoles> listUsuaRole;
    private boolean guardar;

    public UsuariosRoles getObjeUsuaRole() {
        return objeUsuaRole;
    }

    public void setObjeUsuaRole(UsuariosRoles objeUsuaRole) {
        this.objeUsuaRole = objeUsuaRole;
    }

    public List<UsuariosRoles> getListUsuaRole() {
        return listUsuaRole;
    }

    public void setListUsuaRole(List<UsuariosRoles> listUsuaRole) {
        this.listUsuaRole = listUsuaRole;
    }

    public boolean isGuardar() {
        return guardar;
    }
    
    /**
     * Creates a new instance of UsuarioRoleBean
     */
    public UsuarioRoleBean() {
    }
    
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    public void limpForm()
    {
        this.objeUsuaRole = new UsuariosRoles();
        this.guardar = true;        
    }
    
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            objeUsuaRole.setEstaUsuaRole(1);
            FCDEUsuarioRole.create(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listUsuaRole.remove(this.objeUsuaRole); //Limpia el objeto viejo
            FCDEUsuarioRole.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            objeUsuaRole.setEstaUsuaRole(0);
            this.listUsuaRole.remove(this.objeUsuaRole); //Limpia el objeto viejo
            FCDEUsuarioRole.edit(this.objeUsuaRole);
            this.listUsuaRole.add(this.objeUsuaRole); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    
    
    public void consTodo()
    {
        try
        {
            this.listUsuaRole = FCDEUsuarioRole.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }    
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiUsuaRolePara"));
        try
        {
            this.objeUsuaRole = FCDEUsuarioRole.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
    
}
