/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.seguridad;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import seminario.entidades.ModuloRol;
import seminario.entidades.Usuario;
import seminario.utils.SesionUtil;

/**
 *
 * @author Sergio
 */
@ManagedBean
@ViewScoped
public class MenuManagedBean implements Serializable {

    private Usuario usuario;
    
    /**
     * Creates a new instance of HeaderManagedBean
     */
    @PostConstruct
    public void init() {
        
    }

    public MenuManagedBean() {

        usuario = SesionUtil.getUsuario();
        
    }

    public boolean verMenu(String modulo) {
        System.out.println("verMenu: "+modulo);
        
        boolean verMenu = false;

        for (ModuloRol moduloRol : usuario.getIdRol().getModuloRolCollection()) {

            if (moduloRol.getIdModulo().getNombre().toUpperCase().equals(modulo.toUpperCase())) {
                verMenu = true;
                break;
            }

        }

         System.out.println("verMenu: "+modulo+":"+verMenu);
        return verMenu;

    }

    /**/
 /*Get and Set*/
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    }
