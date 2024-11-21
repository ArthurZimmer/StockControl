
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Fornecedores;

public class daoFornecedores {
    
    // variavel de datasource para a conexão
    private final DataSource dataSource;
    
    // método construtor para passar o dataSource
    public daoFornecedores(DataSource dataSource){
        this.dataSource = dataSource;
    }

    //Falta parte do public Arraylist
     public ArrayList<Fornecedores> readAll(){

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM fornecedores";
            
            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);

            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();
            
            // cria a lista de resultados trazidos da tabela
            ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
            
            // enquanto tiverem registros no ResultSet (rs), 
            // vai adicionando na lista
            while(rs.next()){
                // cria objeto de municipio
                // cada cidade é um objeto
                Fornecedores forn = new Fornecedores();

                forn.setIdFornecedor(rs.getInt("idFornecedor"));
                forn.setFor_nome(rs.getString("for_nome"));
                forn.setFor_tipo(rs.getInt("for_tipo"));
                forn.setFor_endereco(rs.getString("for_endereco"));
                forn.setFor_complemento(rs.getString("for_complemento"));
                forn.setFor_bairro(rs.getString("for_bairro"));
                forn.setFor_cep(rs.getString("for_cep"));
                forn.setFor_cidade(rs.getString("for_cidade"));
                forn.setFor_uf(rs.getString("for_uf"));
                forn.setFor_telefone(rs.getString("for_telefone"));
                forn.setFor_cnpj(rs.getString("for_cnpj"));
                forn.setFor_email(rs.getString("for_email"));
                forn.setFor_representante(rs.getString("for_representante"));
                forn.setFor_fone_representante(rs.getString("for_fone_representante"));

                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(forn);
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
    public void create(Fornecedores forn){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "INSERT INTO fornecedores (for_nome, for_tipo, for_endereco, for_complemento, for_bairro, for_cep, for_cidade, for_uf, for_telefone, for_cnpj, for_email, for_representante, for_fone_representante) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = con.prepareStatement(SQL);
            ps.setString(1,forn.getFor_nome());
            ps.setInt(2,forn.getFor_tipo());
            ps.setString(3,forn.getFor_endereco());
            ps.setString(4,forn.getFor_complemento());
            ps.setString(5,forn.getFor_bairro());
            ps.setString(6,forn.getFor_cep());
            ps.setString(7,forn.getFor_cidade());
            ps.setString(8,forn.getFor_uf());
            ps.setString(9,forn.getFor_telefone());
            ps.setString(10,forn.getFor_cnpj());
            ps.setString(11,forn.getFor_email());
            ps.setString(12,forn.getFor_representante());
            ps.setString(13,forn.getFor_fone_representante());
            
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
    
    // excluir registro
    public void excluir(int codigo){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "DELETE FROM fornecedores WHERE (idFornecedor= ?)";
            
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
    
    public void alterar(Fornecedores forn){
        Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            // monta SQL
            String SQL = "update fornecedores set for_nome=?,for_tipo=?,for_endereco=?,for_complemento=?,for_bairro=?,for_cep=?,for_cidade=?,for_uf=?,for_telefone=?,for_cnpj=?,for_email=?,for_representante=?,for_fone_representante=? where idFornecedor=?";

            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = con.prepareStatement(SQL);
            ps.setString(1,forn.getFor_nome());
            ps.setInt(2,forn.getFor_tipo());
            ps.setString(3,forn.getFor_endereco());
            ps.setString(4,forn.getFor_complemento());
            ps.setString(5,forn.getFor_bairro());
            ps.setString(6,forn.getFor_cep());
            ps.setString(7,forn.getFor_cidade());
            ps.setString(8,forn.getFor_uf());
            ps.setString(9,forn.getFor_telefone());
            ps.setString(10,forn.getFor_cnpj());
            ps.setString(11,forn.getFor_email());
            ps.setString(12,forn.getFor_representante());
            ps.setString(13,forn.getFor_fone_representante());
            ps.setInt(14,forn.getIdFornecedor());
            
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
    
    // para consultar um registros na tabela de usuarios
    public ArrayList<Fornecedores> pesquisar(String campo){

        //Connection con = dataSource.getConnection();
        PreparedStatement ps = null;

        try{
            String SQL = "SELECT * FROM fornecedores WHERE (for_nome LIKE ?)";

            // para mandar como uma instrução, precisa usar o PreparedStatement
            // traduz o comando SQL para execução
            ps = dataSource.getConnection().prepareStatement(SQL);
            ps.setString(1,"%"+campo+"%");
            
            // executa a consulta no banco
            ResultSet rs = ps.executeQuery();
            
            // cria a lista de resultados trazidos da tabela
            ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
            
            while(rs.next()){
                // instancia um objeto
                Fornecedores forn = new Fornecedores();
                
                // joga dados da lista para o objeto
               forn.setIdFornecedor(rs.getInt("idFornecedor"));
               forn.setFor_nome(rs.getString("for_nome"));
               forn.setFor_tipo(rs.getInt("for_tipo"));
               forn.setFor_endereco(rs.getString("for_endereco"));
               forn.setFor_complemento(rs.getString("for_complemento"));
               forn.setFor_bairro(rs.getString("for_bairro"));
               forn.setFor_cep(rs.getString("for_cep"));
               forn.setFor_cidade(rs.getString("for_cidade"));
               forn.setFor_uf(rs.getString("for_uf"));
               forn.setFor_telefone(rs.getString("for_telefone"));
               forn.setFor_cnpj(rs.getString("for_cnpj"));
               forn.setFor_email(rs.getString("for_email"));
               forn.setFor_representante(rs.getString("for_representante"));
               forn.setFor_fone_representante(rs.getString("for_fone_representante"));

                // adiciona o objeto (registro) na lista (arraylist)
                lista.add(forn);
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
