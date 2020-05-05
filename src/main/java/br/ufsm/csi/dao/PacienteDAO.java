package br.ufsm.csi.dao;

import br.ufsm.csi.model.Paciente;
import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {


    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;


    public ArrayList<Paciente> getPacientes(){
        ArrayList<Paciente> pacientes = new ArrayList<>();

        try(Connection connection = new ConectaDB().getConexao()){


            this.sql = "SELECT * FROM paciente pa, usuario u, usuario_permissao up, permissao pe " +
                    " WHERE pa.id_usuario = u.id_usuario and u.id_usuario = up.id_usuario and up.id_permissao = pe.id_permissao ";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);

            while (this.resultSet.next()){

                Usuario usuario = new Usuario();
                usuario.setId( this.resultSet.getInt("id_usuario"));
                usuario.setNome( this.resultSet.getString("nome"));
                usuario.setEmail( this.resultSet.getString("email"));
                usuario.setAtivo( this.resultSet.getBoolean("ativo"));

                Permissao permissao = new Permissao();
                permissao.setId(this.resultSet.getInt("id_permissao"));
                permissao.setNome(this.resultSet.getString("nome_permissao"));
                usuario.setPermissao(permissao);
                Paciente p = new Paciente(usuario);
                p.setCartaoSus(this.resultSet.getString("cartaosus"));
                p.setId(this.resultSet.getInt("id_paciente"));
                pacientes.add(p);

            }


        }catch (SQLException e){
            e.printStackTrace();
            this.status = "error";
        }

        return pacientes;
    }


    // refatorar para somente trabalhar com a tabela paciente
    // colocar codigo q trabalha com a tabela usuario em UsuarioDAO
    public String cadastrar(Paciente paciente){

        try(Connection connection = new ConectaDB().getConexao()){

            //BENGIN
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO usuario(nome, email, senha, data_cadastro, ativo) " +
                    " VALUES (?, ?, ?, CURRENT_DATE, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, paciente.getUsuario().getNome());
            this.preparedStatement.setString(2, paciente.getUsuario().getEmail());
            this.preparedStatement.setString(3, paciente.getUsuario().getSenha());
            this.preparedStatement.setBoolean(4, paciente.getUsuario().isAtivo());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                paciente.getUsuario().setId(this.resultSet.getInt(1));
                //paciente.setId(this.resultSet.getInt(1));
                this.status = "OK";
                System.out.println("dentro do if insert usuario");
            }

            if(this.status.equals("OK")){

                this.sql = " INSERT INTO usuario_permissao (id_usuario, id_permissao) " +
                        " VALUES (?,?)";
                this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, paciente.getUsuario().getId());
                this.preparedStatement.setInt(2, paciente.getUsuario().getPermissao().getId());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    //connection.commit();
                    this.status = "OK";
                    System.out.println("dentro do if insert usuario_permissao");
                }


                if(this.status.equals("OK")){
                    this.sql = " INSERT INTO paciente (id_usuario, cartaosus) " +
                            " VALUES (?, ?)";
                    this.preparedStatement = connection.prepareStatement(this.sql);
                    this.preparedStatement.setInt(1, paciente.getUsuario().getId());
                    this.preparedStatement.setString(2, paciente.getCartaoSus());
                    boolean retorno = this.preparedStatement.execute();
                    connection.commit();
                }



            }

        }catch(SQLException e){
            e.printStackTrace();
            this.status = "erro";
        }

        return this.status;
    }

    public String editar(Paciente p){

        try(Connection connection = new ConectaDB().getConexao()){

            connection.setAutoCommit(false);

            String retorno = new UsuarioDAO().editar(p.getUsuario(), connection);
            if(retorno.equals("OK")){

                this.sql = "UPDATE paciente SET cartaosus = ? WHERE id_paciente = ?";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.preparedStatement.setString(1, p.getCartaoSus());
                this.preparedStatement.setInt(2, p.getId());
                this.preparedStatement.executeUpdate();

                if(this.preparedStatement.getUpdateCount() > 0){
                    this.status = "OK";
                    connection.commit();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            this.status = "OK";
        }

        return this.status;
    }


    public String excluir(Paciente p){
        return "";
    }

    public Paciente getPaciente(int id){
        Paciente p = null;

        try (Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM paciente WHERE id_paciente = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){

                String cartaoSus = this.resultSet.getString("cartaosus");
                int id_usuario = this.resultSet.getInt("id_usuario");

                p = new Paciente(id, new UsuarioDAO().getUsuario(id_usuario), cartaoSus);
            }



        }catch (SQLException e){
            e.printStackTrace();
            this.status = "erro";
        }

        return p;
    }

}
