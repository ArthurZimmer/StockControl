package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DataSource {

    // variáveis para a conexão
    private String hostname;
    private int    porta;
    private String database;
    private String username;
    private String password;

    // variável de conexão
    private Connection connection;

    // pedido/abre de conexão
    public DataSource(){
        try{
            // seta valores nas variáveis de conexão
            hostname = "127.0.0.1";
            porta = 3306;
            database = "estoquealimentos2";
            username = "root";
            password = "";
            
            // string de conexão
            String url = "jdbc:mysql://"+hostname+":"+porta+"/"+database+"?useTimezone=true&serverTimezone=UTC";
            
            // registrar o driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // faz a conexão
            connection = DriverManager.getConnection(url, username, password);
            
            //System.out.println("Conectou!");
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERRO na conexão "+ex.getMessage());
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "ERRO geral "+ex.getMessage());
        }
    }
 
    // pega a conexão ativa
    public Connection getConnection(){
        return this.connection;
    }

    // fechamento da conexão
    public void closeDataSource(){
        try{
            connection.close();
            //System.out.println("Conexão fechada!");
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "ERRO ao desconectar "+ex.getMessage());
        }
    }
}
