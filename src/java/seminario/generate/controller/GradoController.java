package seminario.generate.controller;

import seminario.entidades.Grado;
import seminario.entidades.Curso;
import java.util.Collection;
import seminario.generate.facade.GradoFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "gradoController")
@ViewScoped
public class GradoController extends AbstractController<Grado> {

    // Flags to indicate if child collections are empty
    private boolean isCursoCollectionEmpty;

    public GradoController() {
        // Inform the Abstract parent controller of the concrete Grado Entity
        super(Grado.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCursoCollectionEmpty();
    }

    public boolean getIsCursoCollectionEmpty() {
        return this.isCursoCollectionEmpty;
    }

    private void setIsCursoCollectionEmpty() {
        Grado selected = this.getSelected();
        if (selected != null) {
            GradoFacade ejbFacade = (GradoFacade) this.getFacade();
            this.isCursoCollectionEmpty = ejbFacade.isCursoCollectionEmpty(selected);
        } else {
            this.isCursoCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Curso entities that are
     * retrieved from Grado and returns the navigation outcome.
     *
     * @return navigation outcome for Curso page
     */
    public String navigateCursoCollection() {
        Grado selected = this.getSelected();
        if (selected != null) {
            GradoFacade ejbFacade = (GradoFacade) this.getFacade();
            Collection<Curso> selectedCursoCollection = ejbFacade.findCursoCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Curso_items", selectedCursoCollection);
        }
        return "/app/curso/index";
    }

}
