package seminario.generate.controller;

import seminario.entidades.Estudiante;
import seminario.entidades.EstudianteAcudiente;
import java.util.Collection;
import seminario.generate.facade.EstudianteFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estudianteController")
@ViewScoped
public class EstudianteController extends AbstractController<Estudiante> {

    @Inject
    private CursoController idCursoController;
    @Inject
    private TipoDocumentoController idTipoDocumentoController;

    // Flags to indicate if child collections are empty
    private boolean isEstudianteAcudienteCollectionEmpty;

    public EstudianteController() {
        // Inform the Abstract parent controller of the concrete Estudiante Entity
        super(Estudiante.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idCursoController.setSelected(null);
        idTipoDocumentoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEstudianteAcudienteCollectionEmpty();
    }

    public boolean getIsEstudianteAcudienteCollectionEmpty() {
        return this.isEstudianteAcudienteCollectionEmpty;
    }

    private void setIsEstudianteAcudienteCollectionEmpty() {
        Estudiante selected = this.getSelected();
        if (selected != null) {
            EstudianteFacade ejbFacade = (EstudianteFacade) this.getFacade();
            this.isEstudianteAcudienteCollectionEmpty = ejbFacade.isEstudianteAcudienteCollectionEmpty(selected);
        } else {
            this.isEstudianteAcudienteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of EstudianteAcudiente
     * entities that are retrieved from Estudiante and returns the navigation
     * outcome.
     *
     * @return navigation outcome for EstudianteAcudiente page
     */
    public String navigateEstudianteAcudienteCollection() {
        Estudiante selected = this.getSelected();
        if (selected != null) {
            EstudianteFacade ejbFacade = (EstudianteFacade) this.getFacade();
            Collection<EstudianteAcudiente> selectedEstudianteAcudienteCollection = ejbFacade.findEstudianteAcudienteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("EstudianteAcudiente_items", selectedEstudianteAcudienteCollection);
        }
        return "/app/estudianteAcudiente/index";
    }

    /**
     * Sets the "selected" attribute of the Curso controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCurso(ActionEvent event) {
        Estudiante selected = this.getSelected();
        if (selected != null && idCursoController.getSelected() == null) {
            idCursoController.setSelected(selected.getIdCurso());
        }
    }

    /**
     * Sets the "selected" attribute of the TipoDocumento controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdTipoDocumento(ActionEvent event) {
        Estudiante selected = this.getSelected();
        if (selected != null && idTipoDocumentoController.getSelected() == null) {
            idTipoDocumentoController.setSelected(selected.getIdTipoDocumento());
        }
    }

}
