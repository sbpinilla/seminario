package seminario.generate.controller;

import seminario.entidades.Curso;
import seminario.entidades.Estudiante;
import seminario.entidades.CursoMateriaProfesor;
import java.util.Collection;
import seminario.generate.facade.CursoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cursoController")
@ViewScoped
public class CursoController extends AbstractController<Curso> {

    @Inject
    private GradoController idGradoController;

    // Flags to indicate if child collections are empty
    private boolean isEstudianteCollectionEmpty;
    private boolean isCursoMateriaProfesorCollectionEmpty;

    public CursoController() {
        // Inform the Abstract parent controller of the concrete Curso Entity
        super(Curso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idGradoController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsEstudianteCollectionEmpty();
        this.setIsCursoMateriaProfesorCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Grado controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdGrado(ActionEvent event) {
        Curso selected = this.getSelected();
        if (selected != null && idGradoController.getSelected() == null) {
            idGradoController.setSelected(selected.getIdGrado());
        }
    }

    public boolean getIsEstudianteCollectionEmpty() {
        return this.isEstudianteCollectionEmpty;
    }

    private void setIsEstudianteCollectionEmpty() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            this.isEstudianteCollectionEmpty = ejbFacade.isEstudianteCollectionEmpty(selected);
        } else {
            this.isEstudianteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Estudiante entities that
     * are retrieved from Curso and returns the navigation outcome.
     *
     * @return navigation outcome for Estudiante page
     */
    public String navigateEstudianteCollection() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            Collection<Estudiante> selectedEstudianteCollection = ejbFacade.findEstudianteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Estudiante_items", selectedEstudianteCollection);
        }
        return "/app/estudiante/index";
    }

    public boolean getIsCursoMateriaProfesorCollectionEmpty() {
        return this.isCursoMateriaProfesorCollectionEmpty;
    }

    private void setIsCursoMateriaProfesorCollectionEmpty() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            this.isCursoMateriaProfesorCollectionEmpty = ejbFacade.isCursoMateriaProfesorCollectionEmpty(selected);
        } else {
            this.isCursoMateriaProfesorCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CursoMateriaProfesor
     * entities that are retrieved from Curso and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CursoMateriaProfesor page
     */
    public String navigateCursoMateriaProfesorCollection() {
        Curso selected = this.getSelected();
        if (selected != null) {
            CursoFacade ejbFacade = (CursoFacade) this.getFacade();
            Collection<CursoMateriaProfesor> selectedCursoMateriaProfesorCollection = ejbFacade.findCursoMateriaProfesorCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CursoMateriaProfesor_items", selectedCursoMateriaProfesorCollection);
        }
        return "/app/cursoMateriaProfesor/index";
    }

}
