class Produto {

    constructor(id, name, valor, img) {
        this.id = id;
        this.nome = name;
        this.valor = valor;
        this.img = img;
    }
}

class CompraProduto {
    constructor(produto, quantidade, valor) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
    }

}

function adicionarProduto() {
    compraProdutos = getArrayStorage();
    let produto = new Produto(
        $('p[name=produto-id]').text(),
        $('h2[name=produto-nome]').text(),
        $('div[name=produto-valor]').text(),
        $('.img-grande img').attr('src')
    );
    let found = false;
    compraProdutos.forEach(compraProduto => {
        if (compraProduto.produto.id === produto.id) {
            compraProduto.quantidade++;
            found = true;
        }
    });
    if (!found) {
        compraProdutos.push(new CompraProduto(produto, 1, produto.valor));
    }
    setArrayStorage(compraProdutos);
}

function setArrayStorage(arr) {
    localStorage.setItem('listaProd', JSON.stringify(arr));
}

function getArrayStorage() {
    return JSON.parse(localStorage.getItem('listaProd')) || [];
}
