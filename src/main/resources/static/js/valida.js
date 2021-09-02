$(function () {
    $('#form').validate({
        rules: {
            nome: {
                required: true,
                minlength: 2
            },
            sobrenome: {
                required: true,
                minlength: 2
            },
            email: {
                required: true,
                email: true
            },
            cpf: {
                required: true,
                cpf: true
            },
            cep: {
                required: true,
                cep: true
            },

            bairro: {
                required: true
            },
            rua: {
                required: true
            },
            cidade: {
                required: true
            },
            estado: {
                required: true
            },
            password: {
                required: true
            },
            cPassword: {
                required: true,
                equalTo: "#password"
            }
        },
        messages: {
            nome: {
                required: "O campo nome é obrigatório.",
                minlength: "O campo nome deve ter no mínimo dois caracteres"
            },
            sobrenome: {
                required: "O campo sobrenome é obrigatório.",
                minlength: "O campo nome deve ter no mínimo dois caracteres"
            },
            cpf: {
                required: "O campo CPF é obrigatório",
                cpf: "Informe um CPF válido"
            },
            rua: {
                required: "O campo rua é obrigatório",
            },
            cep: {
                required: "O campo CEP é obrigatório",
            },
            email: {
                required: "O campo email é obrigatório",
                email: "Informe um email válido"
            },
            bairro: {
                required: "O campo bairro é obrigatório"
            },
            cidade: {
                required: "O campo cidade é obrigatório"
            },
            estado: {
                required: "O campo cidade é obrigatório"
            },
            password: {
                required: "O campo senha é  obrigatório."
            },
            cPassword: {
                required: "O campo senha é  obrigatório.",
                equalTo: "Senhas não conferem."
            }
        },

    });
});

