package br.ufsm.csi.dao;

import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

// CRUD - CREATE READ UPDATE DELETE
public class UsuarioDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;


    public Usuario autenticar (String email, String senha){

        System.out.println("autenticar .... ");
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT id_usuario FROM usuario WHERE email = ? AND senha = ? ";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.preparedStatement.setString(2, senha);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println("vai executar a consulta "+this.resultSet);
            while (this.resultSet.next()){
                System.out.println("id_usuario = "+this.resultSet.getInt("id_usuario"));
                return getUsuario(this.resultSet.getInt("id_usuario"));
            }

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public ArrayList<Usuario> getUsuariosSemPermissao(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        //try-with-resources java 7
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM usuario";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while (this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId( this.resultSet.getInt("id_usuario"));
                usuario.setNome( this.resultSet.getString("nome"));
                usuario.setEmail( this.resultSet.getString("email"));
                usuario.setAtivo( this.resultSet.getBoolean("ativo"));

                usuarios.add(usuario);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;
    }

    // usuairos e sua permissao
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        //try-with-resources java 7
        try(Connection connection = new ConectaDB().getConexao()){

            //select * from usuario us, permissao pe, usuario_permissao up
            //where us.id_usuario = up.id_usuario and pe.id_permissao = up.id_permissao

            this.sql = "SELECT * FROM usuario us, permissao pe, usuario_permissao up " +
                    " WHERE us.id_usuario = up.id_usuario and pe.id_permissao = up.id_permissao";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);

            while ( this.resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setId( this.resultSet.getInt("id_usuario"));
                usuario.setNome( this.resultSet.getString("nome"));
                usuario.setEmail( this.resultSet.getString("email"));
                usuario.setAtivo( this.resultSet.getBoolean("ativo"));

                Permissao permissao = new Permissao();
                permissao.setId(this.resultSet.getInt("id_permissao"));
                permissao.setNome(this.resultSet.getString("nome_permissao"));
                usuario.setPermissao(permissao);

                usuarios.add(usuario);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario getUsuario(int id){
       Usuario usuario = new Usuario();

        //try-with-resources java 7
        try(Connection connection = new ConectaDB().getConexao()){

            //select * from usuario us, permissao pe, usuario_permissao up
            //where us.id_usuario = up.id_usuario and pe.id_permissao = up.id_permissao

            this.sql = "SELECT * FROM usuario us, permissao pe, usuario_permissao up " +
                    " WHERE us.id_usuario = up.id_usuario and pe.id_permissao = up.id_permissao and us.id_usuario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);

            this.resultSet = this.preparedStatement.executeQuery();

            while ( this.resultSet.next()){

                usuario.setId( this.resultSet.getInt("id_usuario"));
                usuario.setNome( this.resultSet.getString("nome"));
                usuario.setEmail( this.resultSet.getString("email"));
                usuario.setAtivo( this.resultSet.getBoolean("ativo"));
                usuario.setSenha(this.resultSet.getString("senha"));

                Permissao permissao = new Permissao();
                permissao.setId(this.resultSet.getInt("id_permissao"));
                permissao.setNome(this.resultSet.getString("nome_permissao"));
                usuario.setPermissao(permissao);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return usuario;
    }



    public String cadastrar(Usuario usuario){

        try(Connection connection = new ConectaDB().getConexao()){

            //BENGIN
            connection.setAutoCommit(false);

            this.sql = "INSERT INTO usuario(nome, email, senha, data_cadastro, ativo) " +
                    " VALUES (?, ?, ?, CURRENT_DATE, ?)";

            this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.preparedStatement.setString(1, usuario.getNome());
            this.preparedStatement.setString(2, usuario.getEmail());
            this.preparedStatement.setString(3, usuario.getSenha());
            this.preparedStatement.setBoolean(4, usuario.isAtivo());

            this.preparedStatement.execute();
            this.resultSet = this.preparedStatement.getGeneratedKeys();
            this.resultSet.next();

            if(this.resultSet.getInt(1) > 0){
                usuario.setId(this.resultSet.getInt(1));
                this.status = "OK";
                System.out.println("dentro do if insert usuario");
            }

            if(this.status.equals("OK")){

                this.sql = " INSERT INTO usuario_permissao (id_usuario, id_permissao) " +
                        " VALUES (?,?)";
                this.preparedStatement = connection.prepareStatement(this.sql, PreparedStatement.RETURN_GENERATED_KEYS);
                this.preparedStatement.setInt(1, usuario.getId());
                this.preparedStatement.setInt(2, usuario.getPermissao().getId());
                this.preparedStatement.execute();
                this.resultSet = this.preparedStatement.getGeneratedKeys();
                this.resultSet.next();

                if(this.resultSet.getInt(1) > 0){
                    connection.commit();
                    this.status = "OK";
                    System.out.println("dentro do if insert usuario_permissao");
                }



            }

        }catch(SQLException e){
            e.printStackTrace();
            this.status = "problemas";
        }

        return this.status;
    }


    // TAREFA DA SEMANA

    //Deve trazer um usuario com permissao
    public Usuario getUsuario(String email){
        return null;
    }


    public String editar(Usuario usuario, Connection conexao) throws SQLException{
        System.out.println("update usuario ...1");
        this.sql = " UPDATE usuario SET nome =?, email = ?, senha =? WHERE id_usuario =? ";
        this.preparedStatement = conexao.prepareStatement(this.sql);
        this.preparedStatement.setString(1, usuario.getNome());
        this.preparedStatement.setString(2, usuario.getEmail());
        this.preparedStatement.setString(3, usuario.getSenha());
        this.preparedStatement.setInt(4, usuario.getId());
        this.preparedStatement.executeUpdate();

        if(this.preparedStatement.getUpdateCount() > 0){
            this.status = "OK";
        }
        return this.status;
    }

    public String deletar(Usuario usuario){
        return null;
    }

}
