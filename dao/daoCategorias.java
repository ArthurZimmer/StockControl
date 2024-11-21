package dao;

import java.util.ArrayList;
import model.Categorias;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class daoCategorias {

    // variavel de datasource para a conexão
    private DataSource dataSource;
    
    // método construtor para passar o dataSource
    public daoCategorias(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Categorias> montaCombo(){

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT idCategoria,cat_descricao FROM categorias";
            
            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);

            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();
            
            // cria a lista de resultados trazidos da tabela
            ArrayList<Categorias> lista = new ArrayList<Categorias>();
            
            // enquanto tiverem registros no ResultSet (rs), 
            // vai adicionando na lista
            while(rs.next()){
                // cria objeto de municipio
                // cada cidade é um objeto
                Categorias cat = new Categorias();

                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setCat_descricao(rs.getString("cat_descricao"));
                
                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(cat);
            }
            
            // fecha o statement e o datasource
            ps.close();
            dataSource.closeDataSource();
            
            // retorna os dados consultados
            return lista;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao recuperar dados "+ex.getMessage());
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Erro geral "+ex.getMessage());
        }
        
        // fecha o statement e o datasource
        dataSource.closeDataSource();
            
        // caso aconteça alguma coisa
        return null;
    }
    
}
