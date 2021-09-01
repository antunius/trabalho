package br.edu.utfpr.pb.trabalho.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest implements Serializable {

    private String para;
    private String titulo;
    private String conteudo;
    private byte[] file;
    private String fileName;
    private List<String> paraList = new ArrayList();

    public EmailRequest(String para, String titulo, String conteudo) {
        this.para = para;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public EmailRequest(String titulo, String conteudo, List<String> paraList) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.paraList = paraList;
    }
}
