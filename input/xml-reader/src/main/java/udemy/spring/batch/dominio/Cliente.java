package udemy.spring.batch.dominio;

import java.util.Date;

public class Cliente {
    private long id;
    private String primeiroNome;
    private String ultimoNome;
    private Date dataNascimento;

    public Cliente() {

    }

    public Cliente(long id, String primeiroNome, String ultimoNome, Date dataNascimento) {
        this.setId(id);
        this.setPrimeiroNome(primeiroNome);
        this.setUltimoNome(ultimoNome);
        this.setDataNascimento(dataNascimento);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                ", primeiroNome='" + getPrimeiroNome() + '\'' +
                ", ultimoNome='" + getUltimoNome() + '\'' +
                ", dataNascimento=" + getDataNascimento() + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
