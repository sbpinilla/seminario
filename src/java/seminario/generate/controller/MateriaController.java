package seminario.generate.controller;

import seminario.entidades.Materia;
import seminario.entidades.CursoMateriaProfesor;
import java.util.Collection;
import seminario.generate.facade.MateriaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "materiaController")
@ViewScoped
public class MateriaController extends AbstractController<Materia> {

    // Flags to indicate if child collections are empty
    private boolean isCursoMateriaProfesorCollectionEmpty;

    public MateriaController() {
        // Inform the Abstract parent controller of the concrete Materia Entity
        super(Materia.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsCursoMateriaProfesorCollectionEmpty();
    }

    public boolean getIsCursoMateriaProfesorCollectionEmpty() {
        return this.isCursoMateriaProfesorCollectionEmpty;
    }

    private void setIsCursoMateriaProfesorCollectionEmpty() {
        Materia selected = this.getSelected();
        if (selected != null) {
            MateriaFacade ejbFacade = (MateriaFacade) this.getFacade();
            this.isCursoMateriaProfesorCollectionEmpty = ejbFacade.isCursoMateriaProfesorCollectionEmpty(selected);
        } else {
            this.isCursoMateriaProfesorCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of CursoMateriaProfesor
     * entities that are retrieved from Materia and returns the navigation
     * outcome.
     *
     * @return navigation outcome for CursoMateriaProfesor page
     */
    public String navigateCursoMateriaProfesorCollection() {
        Materia selected = this.getSelected();
        if (selected != null) {
            MateriaFacade ejbFacade = (MateriaFacade) this.getFacade();
            Collection<CursoMateriaProfesor> selectedCursoMateriaProfesorCollection = ejbFacade.findCursoMateriaProfesorCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("CursoMateriaProfesor_items", selectedCursoMateriaProfesorCollection);
        }
        return "/app/cursoMateriaProfesor/index";
    }

}
