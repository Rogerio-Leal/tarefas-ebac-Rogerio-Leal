public class Pessoas {

    private String nome;

    private String sexo;

    public Pessoas (String nome, String sexo) {
        this.nome = nome;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public String toString(){
        return "Nome: " + nome + "\nSexo: " + sexo;
    }
}
