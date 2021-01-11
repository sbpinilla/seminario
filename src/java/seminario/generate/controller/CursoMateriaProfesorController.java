package seminario.generate.controller;

import seminario.entidades.CursoMateriaProfesor;
import seminario.entidades.DiaHoraCursoMateriaProf;
import java.util.Collection;
import seminario.generate.facade.CursoMateriaProfesorFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "cursoMateriaProfesorController")
@ViewScoped
public class CursoMateriaProfesorController extends AbstractController<CursoMateriaProfesor> {

    @Inject
    private CursoController idCursoController;
    @Inject
    private MateriaController idMateriaController;
    @Inject
    private ProfesorController idProfesorController;

    // Flags to indicate if child collections are empty
    private boolean isDiaHoraCursoMateriaProfCollectionEmpty;

    public CursoMateriaProfesorController() {
        // Inform the Abstract parent controller of the concrete CursoMateriaProfesor Entity
        super(CursoMateriaProfesor.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idCursoController.setSelected(null);
        idMateriaController.setSelected(null);
        idProfesorController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDiaHoraCursoMateriaProfCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Curso controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCurso(ActionEvent event) {
        CursoMateriaProfesor selected = this.getSelected();
        if (selected != null && idCursoController.getSelected() == null) {
            idCursoController.setSelected(selected.getIdCurso());
        }
    }

    /**
     * Sets the "selected" attribute of the Materia controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdMateria(ActionEvent event) {
        CursoMateriaProfesor selected = this.getSelected();
        if (selected != null && idMateriaController.getSelected() == null) {
            idMateriaController.setSelected(selected.getIdMateria());
        }
    }

    /**
     * Sets the "selected" attribute of the Profesor controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdProfesor(ActionEvent event) {
        CursoMateriaProfesor selected = this.getSelected();
        if (selected != null && idProfesorController.getSelected() == null) {
            idProfesorController.setSelected(selected.getIdProfesor());
        }
    }

    public boolean getIsDiaHoraCursoMateriaProfCollectionEmpty() {
        return this.isDiaHoraCursoMateriaProfCollectionEmpty;
    }

    private void setIsDiaHoraCursoMateriaProfCollectionEmpty() {
        CursoMateriaProfesor selected = this.getSelected();
        if (selected != null) {
            CursoMateriaProfesorFacade ejbFacade = (CursoMateriaProfesorFacade) this.getFacade();
            this.isDiaHoraCursoMateriaProfCollectionEmpty = ejbFacade.isDiaHoraCursoMateriaProfCollectionEmpty(selected);
        } else {
            this.isDiaHoraCursoMateriaProfCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DiaHoraCursoMateriaProf
     * entities that are retrieved from CursoMateriaProfesor and returns the
     * navigation outcome.
     *
     * @return navigation outcome for DiaHoraCursoMateriaProf page
     */
    public String navigateDiaHoraCursoMateriaProfCollection() {
        CursoMateriaProfesor selected = this.getSelected();
        if (selected != null) {
            CursoMateriaProfesorFacade ejbFacade = (CursoMateriaProfesorFacade) this.getFacade();
            Collection<DiaHoraCursoMateriaProf> selectedDiaHoraCursoMateriaProfCollection = ejbFacade.findDiaHoraCursoMateriaProfCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DiaHoraCursoMateriaProf_items", selectedDiaHoraCursoMateriaProfCollection);
        }
        return "/app/diaHoraCursoMateriaProf/index";
    }

}
