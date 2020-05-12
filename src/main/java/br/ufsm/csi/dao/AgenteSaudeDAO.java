package br.ufsm.csi.dao;


import br.ufsm.csi.model.AgenteSaude;
import br.ufsm.csi.model.Paciente;
import br.ufsm.csi.model.Permissao;
import br.ufsm.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class AgenteSaudeDAO {
    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;



    public ArrayList<AgenteSaude> getAgenteSaude(){
        ArrayList<AgenteSaude> agentes = new ArrayList<>();

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM agente_saude ags, usuario u, usuario_permissao up, permissao pe " +
                    " WHERE ags.id_usuario = u.id_usuario and u.id_usuario = up.id_usuario and up.id_permissao = pe.id_permissao ";
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

                AgenteSaude ags = new AgenteSaude(usuario);
                ags.setId(this.resultSet.getInt("id_agente_saude"));
                ags.setEspecialidade(this.resultSet.getString("especialidade"));

                agentes.add(ags);

            }

        }catch(SQLException e){
            e.printStackTrace();
            this.status = "erro";
        }

        return agentes;
    }

    /*
     SELECT * FROM agente_saude ags, usuario u, usuario_permissao up, permissao pe
    where ags.id_usuario = u.id_usuario and u.id_usuario = up.id_usuario
    and up.id_permissao = pe.id_permissao
    and ags.id_agente_saude = 2
     * */
    public AgenteSaude getAgenteSaude(int id){
        AgenteSaude agente = new AgenteSaude();

        int idUsuario;
        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "SELECT * FROM agente_saude WHERE id_agente_saude = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id);
            this.resultSet = this.preparedStatement.executeQuery();

            while (this.resultSet.next()){
                agente.setEspecialidade(this.resultSet.getString("especialidade"));
                agente.setId(this.resultSet.getInt("id_agente_saude"));
                idUsuario = this.resultSet.getInt("id_usuario");
                agente.setUsuario(new UsuarioDAO().getUsuario(idUsuario));
            }

        }catch (SQLException e){
            this.status = "erro";
            e.printStackTrace();
        }

        return agente;
    }



}
