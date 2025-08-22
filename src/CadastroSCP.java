public class CadastroSCP {
    private String nomenclatura;
    private String sexo;
    private String apelido;
    private String classe;
    private String descricao;
    private boolean contencao;

    public CadastroSCP(String nomenclatura, String sexo, String apelido, String classe, String descricao,
        boolean contencao) {
        this.nomenclatura = nomenclatura;
        this.sexo = sexo;
        this.apelido = apelido;
        this.classe = classe;
        this.descricao = descricao;
        this.contencao = contencao;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isContencao() {
        return contencao;
    }

    public void setContencao(boolean contencao) {
        this.contencao = contencao;
    }

}
