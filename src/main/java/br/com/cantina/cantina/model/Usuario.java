package br.com.cantina.cantina.model;
import br.com.cantina.cantina.model.enums.Papel;
import jakarta.persistence.*;
import java.time.Year;

@Entity
@Table(name = "usuario")

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(length = 20)
    private String turma;
    private Integer anoConclusao;
    @Column(unique = true, nullable = false, length = 50)
    private String login;
    @Column(nullable = false, length = 255)
    private String senhaHash;
    @Enumerated(EnumType.STRING)
    private Papel papel;
    private boolean ativo;

    public Usuario(String nome, String turma, Integer anoConclusao, String login, String senhaHash, Papel papel, boolean ativo){

        this.nome = validarNome(nome);
        this.turma = turma;
        this.anoConclusao = validarAnoConclusao(anoConclusao);
        this.login = validarLogin(login);
        this.senhaHash = senhaHash;
        this.papel = validarPapel(papel);
        this.ativo = ativo;

    }

    public Usuario(){}

    //SET

    public void setNome(String nome){
        this.nome = validarNome(nome);
    }

    public void setTurma(String turma){
        this.turma = turma;
    }

    public void setAnoConclusao(Integer anoConclusao){
        this.anoConclusao = validarAnoConclusao(anoConclusao);
    }

    public void setLogin(String login){
        this.login = validarLogin(login);
    }

    public void setSenhaHash(String senhaHash){
        this.senhaHash = senhaHash;
    }

    public void setPapel(Papel papel){
        this.papel = validarPapel(papel);
    }

    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }

    // GET

    public Long getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getTurma(){
        return turma;
    }

    public Integer getAnoConclusao (){
        return anoConclusao;
    }

    public Papel getPapel(){
        return papel;
    }

    public boolean isAtivo (){
        return ativo;
    }

    public String getLogin (){
        return login;
    }

    public String getSenhaHash (){
        return senhaHash;
    }

    // Verificação

    private String validarNome (String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("ERRO: Preencha o campo nome!");
        }
        return nome;
    }

    private String validarLogin (String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("ERRO: Preencha o campo Login!");
        }
        return login;
    }

    private Papel validarPapel (Papel papel){
        if (papel == null){
            throw new IllegalArgumentException("ERRO: Usuário precisa ter um papel definido!");
        }
        return papel;
    }

    private Integer validarAnoConclusao (Integer anoConclusao){
        int anoAtual = Year.now().getValue();

        if(anoConclusao == null){
            return null;
        }

        if (anoConclusao < anoAtual || anoConclusao > anoAtual + 5 ){
            throw new IllegalArgumentException("ERRO: Ano de conclusão inválido!");
        }
        return anoConclusao;
    }

}
