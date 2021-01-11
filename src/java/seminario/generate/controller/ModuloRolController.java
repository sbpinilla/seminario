package seminario.generate.controller;

import seminario.entidades.ModuloRol;
import seminario.generate.facade.ModuloRolFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "moduloRolController")
@ViewScoped
public class ModuloRolController extends AbstractController<ModuloRol> {

    @Inject
    private ModuloController idModuloController;
    @Inject
    private RolController idRolController;

    public ModuloRolController() {
        // Inform the Abstract parent controller of the concrete ModuloRol Entity
        super(ModuloRol.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idModuloController.setSelected(null);
        idRolController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Modulo controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdModulo(ActionEvent event) {
        ModuloRol selected = this.getSelected();
        if (selected != null && idModuloController.getSelected() == null) {
            idModuloController.setSelected(selected.getIdModulo());
        }
    }

    /**
     * Sets the "selected" attribute of the Rol controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdRol(ActionEvent event) {
        ModuloRol selected = this.getSelected();
        if (selected != null && idRolController.getSelected() == null) {
            idRolController.setSelected(selected.getIdRol());
        }
    }

}
