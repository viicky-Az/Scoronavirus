package edu.cecar.modelo;

/**
 *Clase que modela el objeto Dato
 * 
 */
public class Dato {
    private String fecha;
    private String nombrePais;
    private String casosConfirmados;
    private String casosConfirmadosNuevos;
    private String totalMuertes;
    private String totalNuevasMuertes;

    public Dato() {
    }    

    public String getFecha() {
        return fecha.trim();
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
        
    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public int getCasosConfirmados() {
        int casos = Integer.valueOf(casosConfirmados);
        return casos;
    }

    public void setCasosConfirmados(String casosConfirmados) {
        this.casosConfirmados = casosConfirmados;
    }

    public int getCasosConfirmadosNuevos() {
        int casosNuevos = Integer.valueOf(casosConfirmadosNuevos);
        return casosNuevos;
    }

    public void setCasosConfirmadosNuevos(String casosConfirmadosNuevos) {
        this.casosConfirmadosNuevos = casosConfirmadosNuevos;
    }

    public int getTotalMuertes() {
        int muertes = Integer.valueOf(totalMuertes);
        return muertes;
    }

    public void setTotalMuertes(String totalMuertes) {
        this.totalMuertes = totalMuertes;
    }

    public int getTotalNuevasMuertes() {
        int muertesNuevas = Integer.valueOf(totalNuevasMuertes);
        return muertesNuevas;
    }

    public void setTotalNuevasMuertes(String totalNuevasMuertes) {
        this.totalNuevasMuertes = totalNuevasMuertes;
    }
    
    

    

}
