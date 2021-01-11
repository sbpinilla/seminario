package seminario.generate.controller;

import seminario.entidades.Usuario;
import seminario.generate.facade.UsuarioFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController extends AbstractController<Usuario> {

    @Inject
    private RolController idRolController;
    @Inject
    private TipoDocumentoController idTipoDocumentoController;

    public UsuarioController() {
        // Inform the Abstract parent controller of the concrete Usuario Entity
        super(Usuario.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idRolController.setSelected(null);
        idTipoDocumentoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRol(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idRolController.getSelected() == null) {
            idRolController.setSelected(selected.getIdRol());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDocumento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoDocumento(ActionEvent event) {
        Usuario selected = this.getSelected();
        if (selected != null && idTipoDocumentoController.getSelected() == null) {
            idTipoDocumentoController.setSelected(selected.getIdTipoDocumento());
        }
    }

}
