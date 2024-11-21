/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import dao.DataSource;


public class Produtos {
    
    
    private int     idProduto;
    private String prod_categoria;
    private String prod_descricao;
    private String prod_validade;
    private double prod_preco_custo;
    private double prod_preco_venda;
    private int idFornecedor;
    private int prod_quantidade;
    private int prod_minimo;
    // variavel de datasource para a conexão
    private DataSource dataSource;
    
    
    
    public ArrayList<Produtos> readAll() {
    PreparedStatement ps = null;
    ResultSet rs = null;
    
        try {
            String SQL = "SELECT * FROM produtos";
            ps = dataSource.getConnection().prepareStatement(SQL);
            rs = ps.executeQuery();

            ArrayList<Produtos> lista = new ArrayList<>();

            while (rs.next()) {
                Produtos prod = new Produtos();
                prod.setIdProduto(rs.getInt("idProduto"));
                prod.setProd_categoria(rs.getString("prod_categoria"));
                prod.setProd_descricao(rs.getString("prod_descricao"));  // Corrigido o espaço extra
                prod.setProd_validade(rs.getString("prod_validade"));
                prod.setProd_preco_custo(rs.getDouble("prod_preco_custo"));
                prod.setProd_preco_venda(rs.getDouble("prod_preco_venda"));
                prod.setIdFornecedor(rs.getInt("idFornecedor"));
                prod.setProd_quantidade(rs.getInt("prod_quantidade"));
                prod.setProd_minimo(rs.getInt("prod_minimo"));

                lista.add(prod);
            }

            return lista;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar dados: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro geral: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                dataSource.closeDataSource();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return null;
    }
    
    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the prod_categoria
     */
    public String getProd_categoria() {
        return prod_categoria;
    }

    /**
     * @param prod_categoria the prod_categoria to set
     */
    public void setProd_categoria(String prod_categoria) {
        this.prod_categoria = prod_categoria;
    }

    /**
     * @return the prod_descricao
     */
    public String getProd_descricao() {
        return prod_descricao;
    }

    /**
     * @param prod_descricao the prod_descricao to set
     */
    public void setProd_descricao(String prod_descricao) {
        this.prod_descricao = prod_descricao;
    }

    /**
     * @return the prod_validade
     */
    public String getProd_validade() {
        return prod_validade;
    }

    /**
     * @param prod_validade the prod_validade to set
     */
    public void setProd_validade(String prod_validade) {
        this.prod_validade = prod_validade;
    }

    /**
     * @return the prod_preco_custo
     */
    public double getProd_preco_custo() {
        return prod_preco_custo;
    }

    /**
     * @param prod_preco_custo the prod_preco_custo to set
     */
    public void setProd_preco_custo(double prod_preco_custo) {
        this.prod_preco_custo = prod_preco_custo;
    }

    /**
     * @return the prod_preco_venda
     */
    public double getProd_preco_venda() {
        return prod_preco_venda;
    }

    /**
     * @param prod_preco_venda the prod_preco_venda to set
     */
    public void setProd_preco_venda(double prod_preco_venda) {
        this.prod_preco_venda = prod_preco_venda;
    }

    /**
     * @return the idFornecedor
     */
    public int getIdFornecedor() {
        return idFornecedor;
    }

    /**
     * @param idFornecedor the idFornecedor to set
     */
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    /**
     * @return the prod_quantidade
     */
    public int getProd_quantidade() {
        return prod_quantidade;
    }

    /**
     * @param prod_quantidade the prod_quantidade to set
     */
    public void setProd_quantidade(int prod_quantidade) {
        this.prod_quantidade = prod_quantidade;
    }

    /**
     * @return the prod_minimo
     */
    public int getProd_minimo() {
        return prod_minimo;
    }

    /**
     * @param prod_minimo the prod_minimo to set
     */
    public void setProd_minimo(int prod_minimo) {
        this.prod_minimo = prod_minimo;
    }

   
     


    
    
}
