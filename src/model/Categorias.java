package model;

public class Categorias {

    private int    idCategoria;
    private String cat_descricao;    

    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the cat_descricao
     */
    public String getCat_descricao() {
        return cat_descricao;
    }

    /**
     * @param cat_descricao the cat_descricao to set
     */
    public void setCat_descricao(String cat_descricao) {
        this.cat_descricao = cat_descricao;
    }

    @Override
    public String toString() {
        return getCat_descricao();
    }    

}
