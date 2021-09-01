package br.edu.utfpr.pb.trabalho.email;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmailFiltro implements Serializable {

    private Long id;
    private String host;
    private String username;
    private String porta;
}
