package br.ufsm.csi.model;

public class Paciente {

    private int id;
    private String cartaoSus;
    private Usuario usuario;
    private int idade;


    public Paciente(int id, Usuario usuario, String cartaoSus, int idade) {
      this.setId(id);
      this.setUsuario(usuario);
      this.setCartaoSus(cartaoSus);
      this.setIdade(idade);
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
