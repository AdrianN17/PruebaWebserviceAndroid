package com.example.modelo;

public class objetoValidador {
    private String campo;
    private Object caja;
    private Class clase;

    public objetoValidador(String campo, Object caja, Class clase) {
        this.setCampo(campo);
        this.setCaja(caja);
        this.setClase(clase);
    }


    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Object getCaja() {
        return caja;
    }

    public void setCaja(Object caja) {
        this.caja = caja;
    }

    public Class getClase() {
        return clase;
    }

    public void setClase(Class clase) {
        this.clase = clase;
    }
}
