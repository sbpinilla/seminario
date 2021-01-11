package seminario.generate.controller;

import seminario.entidades.Hora;
import seminario.entidades.DiaHora;
import java.util.Collection;
import seminario.generate.facade.HoraFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "horaController")
@ViewScoped
public class HoraController extends AbstractController<Hora> {

    // Flags to indicate if child collections are empty
    private boolean isDiaHoraCollectionEmpty;

    public HoraController() {
        // Inform the Abstract parent controller of the concrete Hora Entity
        super(Hora.class);
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
        Hora selected = this.getSelected();
        if (selected != null) {
            HoraFacade ejbFacade = (HoraFacade) this.getFacade();
            this.isDiaHoraCollectionEmpty = ejbFacade.isDiaHoraCollectionEmpty(selected);
        } else {
            this.isDiaHoraCollectionEmpty = true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of DiaHora entities that are
     * retrieved from Hora and returns the navigation outcome.
     *
     * @return navigation outcome for DiaHora page
     */
    public String navigateDiaHoraCollection() {
        Hora selected = this.getSelected();
        if (selected != null) {
            HoraFacade ejbFacade = (HoraFacade) this.getFacade();
            Collection<DiaHora> selectedDiaHoraCollection = ejbFacade.findDiaHoraCollection(selected);
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("DiaHora_items", selectedDiaHoraCollection);
        }
        return "/app/diaHora/index";
    }

}
