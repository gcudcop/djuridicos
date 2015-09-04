/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juridico.presentacion.beans;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import juridico.entidades.clases.Docente;
import juridico.entidades.funciones.FDocente;
import master.logica.clases.Escuela;
import master.logica.clases.Facultad;
import master.logica.funciones.FEscuela;
import org.primefaces.context.DefaultRequestContext;
import recursos.Util;

/**
 *
 * @author servidor
 */
@ManagedBean
@RequestScoped
public class DocenteBean {

    /**
     * Creates a new instance of DocenteBean
     */
    private Docente objDocente;
    private Docente docenteSel;
    private ArrayList<Docente> lstDocentes;
    private ArrayList<Escuela> lstEscuelas;
    private ArrayList<Facultad> lstFacultades;
    private boolean mostrarActualizar;
    private Date fechaIngreso;
    private Date fechaSalida;
    private String txtFechaIngreso;
    private String txtFechaSalida;
    private int escuelaSeleccionada;

    public ArrayList<Facultad> getLstFacultades() {
        return lstFacultades;
    }

    public void setLstFacultades(ArrayList<Facultad> lstFacultades) {
        this.lstFacultades = lstFacultades;
    }

    public ArrayList<Escuela> getLstEscuelas() {
        return lstEscuelas;
    }

    public void setLstEscuelas(ArrayList<Escuela> lstEscuelas) {
        this.lstEscuelas = lstEscuelas;
    }

    public int getEscuelaSeleccionada() {
        return escuelaSeleccionada;
    }

    public void setEscuelaSeleccionada(int escuelaSeleccionada) {
        this.escuelaSeleccionada = escuelaSeleccionada;
    }

    public Docente getObjDocente() {
        return objDocente;
    }

    public void setObjDocente(Docente objDocente) {
        this.objDocente = objDocente;
    }

    public Docente getDocenteSel() {
        return docenteSel;
    }

    public void setDocenteSel(Docente docenteSel) {
        this.docenteSel = docenteSel;
    }

    public ArrayList<Docente> getLstDocentes() {
        return lstDocentes;
    }

    public void setLstDocentes(ArrayList<Docente> lstDocentes) {
        this.lstDocentes = lstDocentes;
    }

    public boolean isMostrarActualizar() {
        return mostrarActualizar;
    }

    public void setMostrarActualizar(boolean mostrarActualizar) {
        this.mostrarActualizar = mostrarActualizar;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getTxtFechaIngreso() {
        return txtFechaIngreso;
    }

    public void setTxtFechaIngreso(String txtFechaIngreso) {
        this.txtFechaIngreso = txtFechaIngreso;
    }

    public String getTxtFechaSalida() {
        return txtFechaSalida;
    }

    public void setTxtFechaSalida(String txtFechaSalida) {
        this.txtFechaSalida = txtFechaSalida;
    }

    public DocenteBean() {
    }

    private void reinit() {
        this.objDocente = new Docente();
        this.docenteSel = new Docente();
        this.lstDocentes = new ArrayList<Docente>();

        this.cargarDocente();

    }

    public void cargarDocente() {
        try {
            this.lstDocentes = FDocente.ObtenerDocentes();
            this.docenteSel = lstDocentes.get(0);
            System.out.println(lstDocentes.get(0).getId_docente());
        } catch (Exception e) {
            Util.addErrorMessage("private void cargarDocente dice: " + e.getMessage());
            System.out.println("private void cargarDocente dice: " + e.getMessage());
        }
    }

    public void cargarEscuela() {
        try {
            this.lstEscuelas = FEscuela.ObtenerEscuelas();
            //this.docenteSel = lstDocentes.get(0);
            System.out.println(lstDocentes.get(0).getId_docente());
        } catch (Exception e) {
            Util.addErrorMessage("private void cargarEscuela dice: " + e.getMessage());
            System.out.println("private void cargarEscuela dice: " + e.getMessage());
        }
    }

    public void insertarDocente() {

        try {

            if (FDocente.Insertar(objDocente)) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgNuevoDocente.hide()");
                Util.addSuccessMessage("Información guardada con éxito");
                System.out.println("public void insertarDocente dice: Error al guardar la información");
            } else {
                Util.addSuccessMessage("Error al guardar la información");
                System.out.println("public void insertarDocente dice: Error al guardar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("private void insertarDocente dice: " + e.getMessage());
            System.out.println("private void insertarDocente dice: " + e.getMessage());
        }
    }

    public void cambiarEstadoMostrarActualizar() {
        mostrarActualizar = true;
    }

    public void actualizarDocente() {
        try {

            if (FDocente.actualizar(docenteSel)) {
                docenteSel = new Docente();
                mostrarActualizar = false;
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEditarDocente.hide()");
                Util.addSuccessMessage("Información guardada con éxito");
                System.out.println("public void actualizarDocente dice: Información guardada con éxito!!");
            } else {
                Util.addErrorMessage("Error al guardar la información");
                System.out.println("public void actualizarDocente dice: Error al guardar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("private void actualizarDocente dice: " + e.getMessage());
            System.out.println("private void actualizarDocente dice: " + e.getMessage());
        }
    }

    public void eliminarDocente() {
        try {
            if (FDocente.eliminar((int) docenteSel.getId_docente())) {
                this.reinit();
                DefaultRequestContext.getCurrentInstance().execute("wdlgEliminarDocente.hide()");
                Util.addSuccessMessage("Información eliminada.");
                System.out.println("public void eliminarDocente dice: Información eliminada.");
            } else {
                Util.addErrorMessage("Error al eliminar la información.");
                System.out.println("public void eliminarDocente dice: Error al eliminar la información");
            }
        } catch (Exception e) {
            Util.addErrorMessage("private void eliminarDocente dice: " + e.getMessage());
            System.out.println("private void eliminarDocente dice: " + e.getMessage());
        }

    }

}
