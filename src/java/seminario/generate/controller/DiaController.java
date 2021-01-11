package seminario.generate.controller;

import seminario.entidades.Dia;
import seminario.entidades.DiaHora;
import java.util.Collection;
import seminario.generate.facade.DiaFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "diaController")
@ViewScoped
public class DiaController extends AbstractController<Dia> {

    // Flags to indicate if child collections are empty
    private boolean isDiaHoraCollectionEmpty;

    public DiaController() {
        // Inform the Abstract parent controller of the concrete Dia Entity
        super(Dia.class);
    }

    /**
     * Set the "is[ChildCollection]Empty" property for OneToMany fields.
     */
    @Override
    protected void setChildrenEmptyFlags() {
        this.setIsDiaHoraCollectionEmpty();
    }

    public boolean getIsDiaHoraCollectionEmpty() {
        return this.isDiaHoraCollectionEmpty;
    }

    private void setIsDiaHoraCollectionEmpty() {
        Dia selected = this.getSelected();
        if (selected != null) {
            DiaFacade ejbFacade = (DiaFacade) this.getFacade();
            this.isDiaHoraCollectionEmpty = ejbFacade.isDiaHoraCollectionEmpty(selected);
        } else {
            this.isDiaHoraCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DiaHora entities that are
     * retrieved from Dia and returns the navigation outcome.
     *
     * @return navigation outcome for DiaHora page
     */
    public String navigateDiaHoraCollection() {
        Dia selected = this.getSelected();
        if (selected != null) {
            DiaFacade ejbFacade = (DiaFacade) this.getFacade();
            Collection<DiaHora> selectedDiaHoraCollection = ejbFacade.findDiaHoraCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DiaHora_items", selectedDiaHoraCollection);
        }
        return "/app/diaHora/index";
    }

}
