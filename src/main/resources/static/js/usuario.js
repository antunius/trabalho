class Usuario {
    nome;
    email;
    senha;

    constructor(nome, email, senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}

function adicionarUsuario() {
    usuario = new Usuario($('#nome').val() + ' ' + $('#sobrenome').val(), $('#email').val(), $('#senha').val());
    console.log(JSON.stringify(usuario));
    $.ajax({
        type: 'post',
        url: 'usuario/adicionar/',
        contentType: 'application/json',
        data: JSON.stringify(usuario),
        success: function () {
            alert("Usuario salvo com sucesso!");
        },
        error: function () {
            alert("Falha ao salvar o usuario");
        }
    });
}
