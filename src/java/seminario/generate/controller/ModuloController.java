package seminario.generate.controller;

import seminario.entidades.Modulo;
import seminario.entidades.ModuloRol;
import java.util.Collection;
import seminario.generate.facade.ModuloFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "moduloController")
@ViewScoped
public class ModuloController extends AbstractController<Modulo> {

    // Flags to indicate if child collections are empty
    private boolean isModuloRolCollectionEmpty;

    public ModuloController() {
        // Inform the Abstract parent controller of the concrete Modulo Entity
        super(Modulo.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsModuloRolCollectionEmpty();
    }

    public boolean getIsModuloRolCollectionEmpty() {
        return this.isModuloRolCollectionEmpty;
    }

    private void setIsModuloRolCollectionEmpty() {
        Modulo selected = this.getSelected();
        if (selected != null) {
            ModuloFacade ejbFacade = (ModuloFacade) this.getFacade();
            this.isModuloRolCollectionEmpty = ejbFacade.isModuloRolCollectionEmpty(selected);
        } else {
            this.isModuloRolCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of ModuloRol entities that
     * are retrieved from Modulo and returns the navigation outcome.
     *
     * @return navigation outcome for ModuloRol page
     */
    public String navigateModuloRolCollection() {
        Modulo selected = this.getSelected();
        if (selected != null) {
            ModuloFacade ejbFacade = (ModuloFacade) this.getFacade();
            Collection<ModuloRol> selectedModuloRolCollection = ejbFacade.findModuloRolCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("ModuloRol_items", selectedModuloRolCollection);
        }
        return "/app/moduloRol/index";
    }

}
