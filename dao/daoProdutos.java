/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Fornecedores;
import model.Produtos;


public class daoProdutos {
    
    // variavel de datasource para a conexão
    private DataSource dataSource;
    
    // método construtor para passar o dataSource
    public daoProdutos(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //Falta parte do public Arraylist
    public ArrayList<Produtos> readAll() {
        ArrayList<Produtos> lista = new ArrayList<>();

        String SQL = "SELECT * FROM produtos";
        try (PreparedStatement ps = dataSource.getConnection().prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produtos prod = new Produtos();
                prod.setIdProduto(rs.getInt("idProduto"));
                prod.setProd_categoria(rs.getString("prod_categoria"));
                prod.setProd_descricao(rs.getString("prod_descricao"));
                prod.setProd_validade(rs.getString("prod_validade"));
                prod.setProd_preco_custo(rs.getDouble("prod_preco_custo"));
                prod.setProd_preco_venda(rs.getDouble("prod_preco_venda"));
                prod.setIdFornecedor(rs.getInt("idFornecedor"));
                prod.setProd_quantidade(rs.getInt("prod_quantidade"));
                prod.setProd_minimo(rs.getInt("prod_minimo"));

                lista.add(prod);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar dados: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro geral: " + ex.getMessage());
        }

        return lista; // Retorna a lista, que pode estar vazia, mas nunca null
    }


    
        // insere dados
        public void create(Produtos prod){
           Connection con = dataSource.getConnection();
           PreparedStatement ps = null;

           try{
               String SQL = "INSERT INTO produtos (prod_categoria, prod_descricao, prod_validade, prod_preco_custo, prod_preco_venda, idFornecedor, prod_quantidade, prod_minimo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

               // para mandar como uma instrução, precisa usar o PreparedStatement
               // traduz o comando SQL para execução
               ps = con.prepareStatement(SQL);
               ps.setString(1,prod.getProd_categoria());
               ps.setString(2,prod.getProd_descricao());
               ps.setString(3,prod.getProd_validade());
               ps.setDouble(4,prod.getProd_preco_custo());
               ps.setDouble(5,prod.getProd_preco_venda());
               ps.setInt(6,prod.getIdFornecedor());
               ps.setInt(7,prod.getProd_quantidade());
               ps.setInt(8,prod.getProd_minimo());


               // executa a inserção no banco
               ps.executeUpdate();
               ps.close();

               JOptionPane.showMessageDialog(null,"Salvo com sucesso!");
           }
           catch (SQLException ex){
               //System.err.println("Erro ao salvar os dados "+ex.getMessage());
               JOptionPane.showMessageDialog(null,"Erro ao salvar!\n"+ex);
           }
           finally{
               // fecha o statement e o datasource
               //ps.close();
               dataSource.closeDataSource();
           }
        }

     
    
    
     
     
    public void excluir(int codigo){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "DELETE FROM produtos WHERE (idProduto= ?)";
            
            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = con.prepareStatement(SQL);
            ps.setInt(1,codigo);

            // executa a inserção no banco
            ps.executeUpdate();
            ps.close();
            
            JOptionPane.showMessageDialog(null,"Excluído com sucesso!");
        }
        catch (SQLException ex){
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao excluir!\n"+ex);
        }
        finally{
            // fecha o statement e o datasource
            dataSource.closeDataSource();
        }
    }
    
}
