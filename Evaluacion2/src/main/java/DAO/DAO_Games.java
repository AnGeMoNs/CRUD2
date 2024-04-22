/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Date;
/**
 *
 * @author angem
 */
public class DAO_Games {

    private int _ID;
    private String _NOMBRE;
    private String _DESARROLLADOR;
    private String _PLATAFORMA;
    private Date _FECHA_LANZAMIENTO;
    private boolean resultado;

    public DAO_Games() {
    }

    public DAO_Games(int _ID, String _NOMBRE, String _DESARROLLADOR, String _PLATAFORMA, Date _FECHA_LANZAMIENTO) {
        this._ID = _ID;
        this._NOMBRE = _NOMBRE;
        this._DESARROLLADOR = _DESARROLLADOR;
        this._PLATAFORMA = _PLATAFORMA;
        this._FECHA_LANZAMIENTO = _FECHA_LANZAMIENTO;
    }

    public int getID() {
        return _ID;
    }

    public void setID(int _ID) {
        this._ID = _ID;
    }

    public String getNOMBRE() {
        return _NOMBRE;
    }

    public void setNOMBRE(String _NOMBRE) {
        this._NOMBRE = _NOMBRE;
    }

    public String getDESARROLLADOR() {
        return _DESARROLLADOR;
    }

    public void setDESARROLLADOR(String _DESARROLLADOR) {
        this._DESARROLLADOR = _DESARROLLADOR;
    }

    public String getPLATAFORMA() {
        return _PLATAFORMA;
    }

    public void setPLATAFORMA(String _PLATAFORMA) {
        this._PLATAFORMA = _PLATAFORMA;
    }

    public Date getFECHA_LANZAMIENTO() {
        return _FECHA_LANZAMIENTO;
    }

    public void setFECHA_LANZAMIENTO(Date _FECHA_LANZAMIENTO) {
        this._FECHA_LANZAMIENTO = _FECHA_LANZAMIENTO;
    }
    public boolean ValidacionSet(String nombre, String desarrollador, String plataforma, Date fechaLanzamiento) {
        if (!nombre.isEmpty() && !desarrollador.isEmpty() && !plataforma.isEmpty() && fechaLanzamiento != null) {
            this.setNOMBRE(nombre);
            this.setDESARROLLADOR(desarrollador);
            this.setPLATAFORMA(plataforma);
            this.setFECHA_LANZAMIENTO(fechaLanzamiento);
            this.resultado = true;
        } else {
            this.resultado = false;
        }
        return this.resultado;
    }
}

