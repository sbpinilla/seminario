/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.seguridad;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import seminario.entidades.Modulo;
import seminario.entidades.ModuloRol;
import seminario.entidades.Usuario;
import seminario.utils.ExternalContextUtil;
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
