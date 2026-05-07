package model;

public class Usuario {
    private final String nome;
    private final int idade;
    private final PerfilCinefilo perfil;
    private boolean notificacoesAtivas;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.perfil = new PerfilCinefilo();
        this.notificacoesAtivas = true;
    }

    public String getNome() { 
        return nome; 
    }

    public int getIdade() { 
        return idade; 
    }

    public PerfilCinefilo getPerfil() { 
        return perfil; 
    }

    public boolean isNotificacoesAtivas() { 
        return notificacoesAtivas; 
    }

    public void setNotificacoesAtivas(boolean ativas) { 
        this.notificacoesAtivas = ativas; 
    }
}
