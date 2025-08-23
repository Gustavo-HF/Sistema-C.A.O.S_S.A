public class Usuario {
    private String nome;
    private int idade;
    private String telefone;
    private String sexo;
    private String senha;

    public Usuario(String nome, int idade, String telefone, String sexo, String senha) {
        this.nome = nome;
        this.idade = idade;
        this.telefone = telefone;
        this.sexo = sexo;
        this.senha = senha;
    }

    public Usuario(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
}
