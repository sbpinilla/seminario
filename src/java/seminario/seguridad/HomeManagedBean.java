/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seminario.seguridad;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import seminario.entidades.Acudiente;
import seminario.entidades.CursoMateriaProfesor;
import seminario.entidades.DiaHoraCursoMateriaProf;
import seminario.entidades.Estudiante;
import seminario.entidades.EstudianteAcudiente;
import seminario.entidades.Usuario;
import seminario.generate.facade.AcudienteFacade;
import seminario.generate.facade.EstudianteFacade;
import seminario.utils.ExternalContextUtil;
import seminario.utils.HorarioDTO;
import seminario.utils.SesionUtil;

/**
 *
 * @author Sergio
 */
@ManagedBean
@ViewScoped
public class HomeManagedBean implements Serializable {

    @EJB
    private AcudienteFacade acudienteFacade;

    @EJB
    private EstudianteFacade estudianteFacade;

    private Usuario usuario;
    private ScheduleModel eventModel;

    /**
     * Creates a new instance of HeaderManagedBean
     */
    @PostConstruct
    public void init() {

        boolean esAcudiente = false;

        List<Estudiante> listEstudiante = new ArrayList<>();

        if (usuario.getIdRol().getNombre().equals("ESTUDIANTE")) {
            listEstudiante.add(estudianteFacade.findDocumento(usuario.getDocumento()));

        } else if (usuario.getIdRol().getNombre().equals("ACUDIENTE")) {
            esAcudiente = true;
            Acudiente acudiente = acudienteFacade.findDocumento(usuario.getDocumento());

            for (EstudianteAcudiente estudianteAcudiente : acudiente.getEstudianteAcudienteCollection()) {

                listEstudiante.add(estudianteAcudiente.getIdEstudiante());
            }

        }

        eventModel = new DefaultScheduleModel();

        for (Estudiante estudiante : listEstudiante) {

            Calendar calInicio = Calendar.getInstance();
            Calendar calFin = Calendar.getInstance();

            calInicio.set(Calendar.MINUTE, 0);
            calFin.set(Calendar.MINUTE, 0);

            calInicio.set(Calendar.DAY_OF_MONTH, 1);
            calFin.set(Calendar.DAY_OF_MONTH, 1);

            calInicio.set(Calendar.MONTH, Calendar.JANUARY);
            calFin.set(Calendar.MONTH, Calendar.JANUARY);

            List<HorarioDTO> horarioDTOs = estudianteFacade.callSP(
                    estudiante.getDocumento(),
                    estudiante.getIdTipoDocumento().getIdTipoDocumento().toPlainString());

            for (HorarioDTO horarioDTO : horarioDTOs) {

                switch (horarioDTO.getDia().toUpperCase()) {

                    case "LUNES":

                        calInicio.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        calFin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

                        break;
                    case "MARTES":

                        calInicio.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                        calFin.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

                        break;
                    case "MIÉRCOLES":
                        calInicio.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                        calFin.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

                        break;
                    case "JUEVES":
                        calInicio.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                        calFin.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);

                        break;
                    case "VIERNES":
                        calInicio.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        calFin.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                        break;

                }

                String horaParcial = horarioDTO.getHora().split(":")[0];
                int horaInt = Integer.parseInt(horaParcial);

                calInicio.set(Calendar.HOUR_OF_DAY, horaInt);
                Date dateInicio = calInicio.getTime();

                calFin.set(Calendar.HOUR_OF_DAY, horaInt + 1);
                Date dateFin = calFin.getTime();

                String lblAcudiente = horarioDTO.getMateria() + " - " + estudiante.getNombres() + " " + estudiante.getApellidos();

                DefaultScheduleEvent event = new DefaultScheduleEvent(
                        esAcudiente ? lblAcudiente : horarioDTO.getMateria(), dateInicio, dateFin);
                eventModel.addEvent(event);

                // Semanas adelante
                for (int x = 1; x <= 55; x++) {

                    DefaultScheduleEvent event2 = new DefaultScheduleEvent(
                            esAcudiente ? lblAcudiente : horarioDTO.getMateria(),
                            sumarDiasAFecha(dateInicio, x * 7),
                            sumarDiasAFecha(dateFin, x * 7));
                    eventModel.addEvent(event2);

                }

            }

        }

    }

    public HomeManagedBean() {

        usuario = SesionUtil.getUsuario();

    }

    public Date sumarDiasAFecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }


    /**/
 /*Get and Set*/
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

}
