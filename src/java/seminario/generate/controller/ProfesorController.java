package seminario.generate.controller;

import seminario.entidades.Profesor;
import seminario.entidades.CursoMateriaProfesor;
import java.util.Collection;
import seminario.generate.facade.ProfesorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "profesorController")
@ViewScoped
public class ProfesorController extends AbstractController<Profesor> {

    @Inject
    private TipoDocumentoController idTipoDocumentoController;

    // Flags to indicate if child collections are empty
    private boolean isCursoMateriaProfesorCollectionEmpty;

    public ProfesorController() {
        // Inform the Abstract parent controller of the concrete Profesor Entity
        super(Profesor.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idTipoDocumentoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCursoMateriaProfesorCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the TipoDocumento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoDocumento(ActionEvent event) {
        Profesor selected = this.getSelected();
        if (selected != null && idTipoDocumentoController.getSelected() == null) {
            idTipoDocumentoController.setSelected(selected.getIdTipoDocumento());
        }
    }

    public boolean getIsCursoMateriaProfesorCollectionEmpty() {
        return this.isCursoMateriaProfesorCollectionEmpty;
    }

    private void setIsCursoMateriaProfesorCollectionEmpty() {
        Profesor selected = this.getSelected();
        if (selected != null) {
            ProfesorFacade ejbFacade = (ProfesorFacade) this.getFacade();
            this.isCursoMateriaProfesorCollectionEmpty = ejbFacade.isCursoMateriaProfesorCollectionEmpty(selected);
        } else {
            this.isCursoMateriaProfesorCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CursoMateriaProfesor
     * entities that are retrieved from Profesor and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CursoMateriaProfesor page
     */
    public String navigateCursoMateriaProfesorCollection() {
        Profesor selected = this.getSelected();
        if (selected != null) {
            ProfesorFacade ejbFacade = (ProfesorFacade) this.getFacade();
            Collection<CursoMateriaProfesor> selectedCursoMateriaProfesorCollection = ejbFacade.findCursoMateriaProfesorCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CursoMateriaProfesor_items", selectedCursoMateriaProfesorCollection);
        }
        return "/app/cursoMateriaProfesor/index";
    }

}
