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
import seminario.entidades.Usuario;
import seminario.utils.ExternalContextUtil;
import seminario.utils.SesionUtil;

/**
 *
 * @author Sergio
 */
@ManagedBean
@ViewScoped
public class HeaderManagedBean implements Serializable {

    private Usuario usuario;

    /**
     * Creates a new instance of HeaderManagedBean
     */
    @PostConstruct
    public void init() {

    }

    public HeaderManagedBean() {

        usuario = SesionUtil.getUsuario();

    }

    public void cerrarSesion() throws IOException {

        HttpSession session = SesionUtil.getSession();
        session.setAttribute("USUARIO", null);
        session.invalidate();
        ExternalContextUtil.getExternalContext().redirect(ExternalContextUtil.getExternalContextPath());

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
