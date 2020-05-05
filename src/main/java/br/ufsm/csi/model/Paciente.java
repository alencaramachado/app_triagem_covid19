package br.ufsm.csi.model;

public class Paciente {

    private int id;
    private String cartaoSus;
    private Usuario usuario;



    public Paciente(int id, Usuario usuario, String cartaoSus) {
      this.setId(id);
      this.setUsuario(usuario);
      this.setCartaoSus(cartaoSus);
    }

    public Paciente(Usuario usuario){
            setUsuario(usuario);
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCartaoSus() {
        return cartaoSus;
    }

    public void setCartaoSus(String cartaoSus) {
        this.cartaoSus = cartaoSus;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
