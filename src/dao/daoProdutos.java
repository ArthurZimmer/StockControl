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
import model.Produtos;


public class daoProdutos {
    
        // variavel de datasource para a conexão
    private DataSource dataSource;
    
    // método construtor para passar o dataSource
    public daoProdutos(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //Falta parte do public Arraylist
     public ArrayList<Produtos> readAll(){

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM PRODUTOS";
            
            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);

            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();
            
            // cria a lista de resultados trazidos da tabela
            ArrayList<Produtos> lista = new ArrayList<Produtos>();
            
            // enquanto tiverem registros no ResultSet (rs), 
            // vai adicionando na lista
            while(rs.next()){
                // cria objeto de municipio
                // cada cidade é um objeto
                Produtos prod = new Produtos();

                prod.setIdProduto(rs.getInt("idProduto"));
                prod.setProd_categoria(rs.getString("prod_categoria"));
                prod.setProd_descricao(rs.getString(" prod_descricao"));
                prod.setProd_validade(rs.getString("prod_validade"));
                prod.setProd_preco_custo(rs.getDouble("prod_preco_custo"));
                prod.setProd_preco_venda(rs.getDouble("prod_preco_venda"));
                prod.setIdFornecedor(rs.getInt("idFornecedor"));
                prod.setProd_quantidade(rs.getInt("prod_quantidade"));
                prod.setProd_minimo(rs.getInt("prod_minimo"));

                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(prod);
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
    
  public void alterar(Produtos prod){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            // monta SQL
            String SQL = "update fornecedores set prod_categoria=?,prod_descricao=?,prod_validade=?,prod_preco_custo=?,prod_preco_venda=?,idFornecedor=?,prod_quantidade=?,prod_minimo=?, where idProduto=?";

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
            
            JOptionPane.showMessageDialog(null,"Alterado com sucesso!");
        }
        catch (SQLException ex){
            //System.err.println("Erro ao salvar os dados "+ex.getMessage());
            JOptionPane.showMessageDialog(null,"Erro ao alterar!\n"+ex);
        }
        finally{
            // fecha o statement e o datasource
            dataSource.closeDataSource();
        }
    }
  
  
 public ArrayList<Produtos> pesquisar(String campo){

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM produtos WHERE (idProdutos LIKE ?)";

            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,"%"+campo+"%");
            
            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();
            
            // cria a lista de resultados trazidos da tabela
            ArrayList<Produtos> lista = new ArrayList<Produtos>();
            
            while(rs.next()){
                // instancia um objeto
                Produtos prod = new Produtos();
                
                // joga dados da lista para o objeto
                prod.setIdProduto(rs.getInt("idProduto"));
                prod.setProd_categoria(rs.getString("prod_categoria"));
                prod.setProd_descricao(rs.getString(" prod_descricao"));
                prod.setProd_validade(rs.getString("prod_validade"));
                prod.setProd_preco_custo(rs.getDouble("prod_preco_custo"));
                prod.setProd_preco_venda(rs.getDouble("prod_preco_venda"));
                prod.setIdFornecedor(rs.getInt("idFornecedor"));
                prod.setProd_quantidade(rs.getInt("prod_quantidade"));
                prod.setProd_minimo(rs.getInt("prod_minimo"));


                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(prod);
            }
            
            // fecha o statement e o datasource
            ps.close();
            dataSource.closeDataSource();
            
            // retorna os dados consultados
            return lista;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao recuperar dados "+ex.getMessage());
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null,"Erro geral "+ex.getMessage());
        }
        
        // fecha o statement e o datasource
        dataSource.closeDataSource();
            
        // caso aconteça alguma coisa
        return null;
    }
}
