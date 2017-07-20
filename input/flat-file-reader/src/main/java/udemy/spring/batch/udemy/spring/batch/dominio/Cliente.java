package udemy.spring.batch.udemy.spring.batch.dominio;

import java.util.Date;

public class Cliente {
    private long id;
    private String primeiroNome;
    private String ultimoNome;
    private Date dataNascimento;

    public Cliente(long id, String primeiroNome, String ultimoNome, Date dataNascimento) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", primeiroNome='" + primeiroNome + '\'' +
                ", ultimoNome='" + ultimoNome + '\'' +
                ", dataNascimento=" + dataNascimento + '}';
    }
}
