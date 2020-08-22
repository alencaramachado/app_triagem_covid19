package br.ufsm.csi.dao;

import br.ufsm.csi.model.CheckSintomas;
import br.ufsm.csi.service.CheckSintomasService;

import java.sql.*;
import java.util.ArrayList;

public class CheckSintomasDAO {

    private String sql;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public String registrarCheckSintomas(CheckSintomas sintomas){

        try(Connection connection = new ConectaDB().getConexao()){

            this.sql = "INSERT INTO check_sintomas (id_paciente, id_agente_saude, temperatura, tosse, catarro, rouquidao, dor_garganta, " +
                    " nariz_entupido, data_avaliacao, dias_sintomas ) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?) ";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, sintomas.getPaciente().getId());
            this.preparedStatement.setInt(2, sintomas.getAgenteSaude().getId());
            this.preparedStatement.setFloat(3, sintomas.getTemperatura());
            this.preparedStatement.setBoolean(4, sintomas.isTosse());
            this.preparedStatement.setBoolean(5, sintomas.isCatarro());
            this.preparedStatement.setBoolean(6, sintomas.isRouquidao());
            this.preparedStatement.setBoolean(7, sintomas.isDorGarganta());
            this.preparedStatement.setBoolean(8, sintomas.isNarizEntupido());
            this.preparedStatement.setInt(9, sintomas.getQtdDiasSintomas());

            int retorno = this.preparedStatement.executeUpdate();
            if(retorno > 0){
                return this.status = "OK";
            }

        }catch (SQLException e){
            e.printStackTrace();
            return  "erro";
        }


        return "erro";
    }


        public ArrayList<CheckSintomas> getCheckSintomas(){
                ArrayList<CheckSintomas> sintomas = new ArrayList<>();

            try(Connection connection = new ConectaDB().getConexao()){

                this.sql = "SELECT * FROM check_sintomas";
                this.preparedStatement = connection.prepareStatement(this.sql);
                this.resultSet = this.preparedStatement.executeQuery();

                while (this.resultSet.next()){

                    CheckSintomas sintoma = new CheckSintomas(
                            new PacienteDAO().getPaciente(this.resultSet.getInt("id_paciente")),
                            new AgenteSaudeDAO().getAgenteSaude(this.resultSet.getInt("id_agente_saude")),
                            this.resultSet.getFloat("temperatura"),
                            this.resultSet.getBoolean("tosse"),
                            this.resultSet.getBoolean("catarro"),
                            this.resultSet.getBoolean("rouquidao"),
                            this.resultSet.getBoolean("dor_garganta"),
                            this.resultSet.getBoolean("nariz_entupido"),
                            this.resultSet.getInt("dias_sintomas"),
                            this.resultSet.getDate("data_avaliacao")
                    );

                    sintoma.setCasoSuspeito(new CheckSintomasService().suspeito(sintoma));

                    sintomas.add(sintoma);
                }


            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }


                return sintomas;
        }

}














