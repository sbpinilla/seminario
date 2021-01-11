/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.seguridad;

import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import seminario.entidades.Usuario;
import seminario.generate.facade.CursoFacade;
import seminario.generate.facade.UsuarioFacade;
import seminario.utils.ExternalContextUtil;
import seminario.utils.SesionUtil;

/**
 *
 * @author sergio
 */
@ManagedBean(name = "loginManagedBean")
@ViewScoped
public class LoginManagedBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;

    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
    }

    public void iniciarSesion() throws IOException {

        Usuario usuarioR = usuarioFacade.consultarUsuario(usuario);
        if (usuarioR != null) {
            
            HttpSession session = SesionUtil.getSession();
            session.setAttribute("USUARIO", usuarioR);
            ExternalContextUtil.getExternalContext().redirect(ExternalContextUtil.getExternalContextPath() + "/faces/app.xhtml");

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no existe"));
        }

        //CursoFacade ejbFacade = new CursoFacade();
        //System.out.println(" ejbFacade.count()" + ejbFacade.count());
    }

    ///////////
    public Usuario getUsuario() {

        if (usuario == null) {
            usuario = new Usuario();
        }

        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
