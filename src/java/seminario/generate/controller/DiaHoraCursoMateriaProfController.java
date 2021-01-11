package seminario.generate.controller;

import seminario.entidades.DiaHoraCursoMateriaProf;
import seminario.generate.facade.DiaHoraCursoMateriaProfFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "diaHoraCursoMateriaProfController")
@ViewScoped
public class DiaHoraCursoMateriaProfController extends AbstractController<DiaHoraCursoMateriaProf> {

    @Inject
    private CursoMateriaProfesorController idCursoMateriaProfesorController;
    @Inject
    private DiaHoraController idDiaHoraController;

    public DiaHoraCursoMateriaProfController() {
        // Inform the Abstract parent controller of the concrete DiaHoraCursoMateriaProf Entity
        super(DiaHoraCursoMateriaProf.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idCursoMateriaProfesorController.setSelected(null);
        idDiaHoraController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the CursoMateriaProfesor controller in
     * order to display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdCursoMateriaProfesor(ActionEvent event) {
        DiaHoraCursoMateriaProf selected = this.getSelected();
        if (selected != null && idCursoMateriaProfesorController.getSelected() == null) {
            idCursoMateriaProfesorController.setSelected(selected.getIdCursoMateriaProfesor());
        }
    }

    /**
     * Sets the "selected" attribute of the DiaHora controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdDiaHora(ActionEvent event) {
        DiaHoraCursoMateriaProf selected = this.getSelected();
        if (selected != null && idDiaHoraController.getSelected() == null) {
            idDiaHoraController.setSelected(selected.getIdDiaHora());
        }
    }

}
