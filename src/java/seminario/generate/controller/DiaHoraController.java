package seminario.generate.controller;

import seminario.entidades.DiaHora;
import seminario.entidades.DiaHoraCursoMateriaProf;
import java.util.Collection;
import seminario.generate.facade.DiaHoraFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "diaHoraController")
@ViewScoped
public class DiaHoraController extends AbstractController<DiaHora> {

    @Inject
    private DiaController idDiaController;
    @Inject
    private HoraController idHoraController;

    // Flags to indicate if child collections are empty
    private boolean isDiaHoraCursoMateriaProfCollectionEmpty;

    public DiaHoraController() {
        // Inform the Abstract parent controller of the concrete DiaHora Entity
        super(DiaHora.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idDiaController.setSelected(null);
        idHoraController.setSelected(null);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDiaHoraCursoMateriaProfCollectionEmpty();
    }

    /**
     * Sets the "selected" attribute of the Dia controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdDia(ActionEvent event) {
        DiaHora selected = this.getSelected();
        if (selected != null && idDiaController.getSelected() == null) {
            idDiaController.setSelected(selected.getIdDia());
        }
    }

    /**
     * Sets the "selected" attribute of the Hora controller in order to display
     * its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdHora(ActionEvent event) {
        DiaHora selected = this.getSelected();
        if (selected != null && idHoraController.getSelected() == null) {
            idHoraController.setSelected(selected.getIdHora());
        }
    }

    public boolean getIsDiaHoraCursoMateriaProfCollectionEmpty() {
        return this.isDiaHoraCursoMateriaProfCollectionEmpty;
    }

    private void setIsDiaHoraCursoMateriaProfCollectionEmpty() {
        DiaHora selected = this.getSelected();
        if (selected != null) {
            DiaHoraFacade ejbFacade = (DiaHoraFacade) this.getFacade();
            this.isDiaHoraCursoMateriaProfCollectionEmpty = ejbFacade.isDiaHoraCursoMateriaProfCollectionEmpty(selected);
        } else {
            this.isDiaHoraCursoMateriaProfCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DiaHoraCursoMateriaProf
     * entities that are retrieved from DiaHora and returns the navigation
     * outcome.
     *
     * @return navigation outcome for DiaHoraCursoMateriaProf page
     */
    public String navigateDiaHoraCursoMateriaProfCollection() {
        DiaHora selected = this.getSelected();
        if (selected != null) {
            DiaHoraFacade ejbFacade = (DiaHoraFacade) this.getFacade();
            Collection<DiaHoraCursoMateriaProf> selectedDiaHoraCursoMateriaProfCollection = ejbFacade.findDiaHoraCursoMateriaProfCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DiaHoraCursoMateriaProf_items", selectedDiaHoraCursoMateriaProfCollection);
        }
        return "/app/diaHoraCursoMateriaProf/index";
    }

}
