package seminario.generate.controller;

import seminario.entidades.EstudianteAcudiente;
import seminario.generate.facade.EstudianteAcudienteFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "estudianteAcudienteController")
@ViewScoped
public class EstudianteAcudienteController extends AbstractController<EstudianteAcudiente> {

    @Inject
    private AcudienteController idAcudienteController;
    @Inject
    private EstudianteController idEstudianteController;

    public EstudianteAcudienteController() {
        // Inform the Abstract parent controller of the concrete EstudianteAcudiente Entity
        super(EstudianteAcudiente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idAcudienteController.setSelected(null);
        idEstudianteController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Acudiente controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdAcudiente(ActionEvent event) {
        EstudianteAcudiente selected = this.getSelected();
        if (selected != null && idAcudienteController.getSelected() == null) {
            idAcudienteController.setSelected(selected.getIdAcudiente());
        }
    }

    /**
     * Sets the "selected" attribute of the Estudiante controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdEstudiante(ActionEvent event) {
        EstudianteAcudiente selected = this.getSelected();
        if (selected != null && idEstudianteController.getSelected() == null) {
            idEstudianteController.setSelected(selected.getIdEstudiante());
        }
    }

}
