package seminario.generate.controller;

import seminario.entidades.TipoDocumento;
import seminario.entidades.Profesor;
import seminario.entidades.Estudiante;
import seminario.entidades.Usuario;
import seminario.entidades.Acudiente;
import java.util.Collection;
import seminario.generate.facade.TipoDocumentoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "tipoDocumentoController")
@ViewScoped
public class TipoDocumentoController extends AbstractController<TipoDocumento> {

    // Flags to indicate if child collections are empty
    private boolean isProfesorCollectionEmpty;
    private boolean isEstudianteCollectionEmpty;
    private boolean isUsuarioCollectionEmpty;
    private boolean isAcudienteCollectionEmpty;

    public TipoDocumentoController() {
        // Inform the Abstract parent controller of the concrete TipoDocumento Entity
        super(TipoDocumento.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsProfesorCollectionEmpty();
        this.setIsEstudianteCollectionEmpty();
        this.setIsUsuarioCollectionEmpty();
        this.setIsAcudienteCollectionEmpty();
    }

    public boolean getIsProfesorCollectionEmpty() {
        return this.isProfesorCollectionEmpty;
    }

    private void setIsProfesorCollectionEmpty() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            this.isProfesorCollectionEmpty = ejbFacade.isProfesorCollectionEmpty(selected);
        } else {
            this.isProfesorCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Profesor entities that
     * are retrieved from TipoDocumento and returns the navigation outcome.
     *
     * @return navigation outcome for Profesor page
     */
    public String navigateProfesorCollection() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            Collection<Profesor> selectedProfesorCollection = ejbFacade.findProfesorCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Profesor_items", selectedProfesorCollection);
        }
        return "/app/profesor/index";
    }

    public boolean getIsEstudianteCollectionEmpty() {
        return this.isEstudianteCollectionEmpty;
    }

    private void setIsEstudianteCollectionEmpty() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            this.isEstudianteCollectionEmpty = ejbFacade.isEstudianteCollectionEmpty(selected);
        } else {
            this.isEstudianteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Estudiante entities that
     * are retrieved from TipoDocumento and returns the navigation outcome.
     *
     * @return navigation outcome for Estudiante page
     */
    public String navigateEstudianteCollection() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            Collection<Estudiante> selectedEstudianteCollection = ejbFacade.findEstudianteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Estudiante_items", selectedEstudianteCollection);
        }
        return "/app/estudiante/index";
    }

    public boolean getIsUsuarioCollectionEmpty() {
        return this.isUsuarioCollectionEmpty;
    }

    private void setIsUsuarioCollectionEmpty() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            this.isUsuarioCollectionEmpty = ejbFacade.isUsuarioCollectionEmpty(selected);
        } else {
            this.isUsuarioCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Usuario entities that are
     * retrieved from TipoDocumento and returns the navigation outcome.
     *
     * @return navigation outcome for Usuario page
     */
    public String navigateUsuarioCollection() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            Collection<Usuario> selectedUsuarioCollection = ejbFacade.findUsuarioCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Usuario_items", selectedUsuarioCollection);
        }
        return "/app/usuario/index";
    }

    public boolean getIsAcudienteCollectionEmpty() {
        return this.isAcudienteCollectionEmpty;
    }

    private void setIsAcudienteCollectionEmpty() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            this.isAcudienteCollectionEmpty = ejbFacade.isAcudienteCollectionEmpty(selected);
        } else {
            this.isAcudienteCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Acudiente entities that
     * are retrieved from TipoDocumento and returns the navigation outcome.
     *
     * @return navigation outcome for Acudiente page
     */
    public String navigateAcudienteCollection() {
        TipoDocumento selected = this.getSelected();
        if (selected != null) {
            TipoDocumentoFacade ejbFacade = (TipoDocumentoFacade) this.getFacade();
            Collection<Acudiente> selectedAcudienteCollection = ejbFacade.findAcudienteCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Acudiente_items", selectedAcudienteCollection);
        }
        return "/app/acudiente/index";
    }

}
