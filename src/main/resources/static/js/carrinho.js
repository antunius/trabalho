function setArrayStorage(arr) {
    localStorage.setItem('listaProd', JSON.stringify(arr));
}

function getArrayStorage() {
    return JSON.parse(localStorage.getItem('listaProd')) || [];
}


$(function () {
    let items = getArrayStorage();
    items.forEach(function (item, index) {
        $('#carrinho-produtos tbody').append(novaTr(item));
        calcularTotal();
    });
});

function novaTr(item) {
    // let produto = novoProduto();
    return `<tr>
    <td>
        <figure class="itemside">
            <div class="aside">
                <img width="80px" height="80px" src="${item.produto.img}" class="img-sm" alt="${item.produto.nome}">
            </div>
            <figcaption class="info">
                <a href="#" class="title text-dark">${item.produto.nome}</a>
            </figcaption>
        </figure>
    </td>
    <td>
        <input class="sc-bGbJRg ePKBGA" style="width: 25px" value="${item.quantidade}" id="${item.produto.id}" onchange="atualizarQuantidade(this);calcularTotal()">
    </td>
    <td id="td-preco">
        <div class="price-wrap">
            <var id="preco">${parseFloat(item.produto.valor).toBrl()}</var>
        </div>
    </td>
    <td class="text-right">
        <a onclick="excluirProduto(event, this,${item.produto.id});" href="#" class="btn btn-light">Remover</a>
    </td>
</tr>`;
}

function excluirProduto(e, element, produtoId) {
    console.log(produtoId);
    e.preventDefault();
    //a função parents retorna o elemento pai da td, que é tr
    let tr = $(element).parents('tr');
    $(tr).remove();
    let produtos = getArrayStorage();
    for (let i = 0; i < produtos.length; i++) {
        if (produtos[i].produto.id == produtoId) {
            produtos.splice(i, 1);
        }
    }
    setArrayStorage(produtos);
    calcularTotal();
    //carregarProdutoTable();
}

function atualizarQuantidade(produtoQuantidade) {
    let arrayStorage = getArrayStorage();
    for (let i = 0; i < arrayStorage.length; i++) {
        if (arrayStorage[i].produto.id === produtoQuantidade.id) {
            arrayStorage[i].quantidade = parseInt(produtoQuantidade.value);
        }
    }
    setArrayStorage(arrayStorage);
}

function calcularTotal() {
    let subtotal = 0;
    let storage = getArrayStorage();
    for (let i = 0; i < storage.length; i++) {
        let pc = storage[i];
        let preco = Number(pc.quantidade) * pc.produto.valor;
        pc.preco = preco;
        subtotal += preco;
    }
    setArrayStorage(storage)
    $('#total').text(subtotal.toBrl());
    console.log(subtotal.toBrl());
}

function finalizarCompra() {
    pedido = getArrayStorage();
    $.ajax({
        type: 'post',
        url: 'compra/finalizar',
        contentType: 'application/json',
        data: JSON.stringify(pedido),
        error: function () {
            alert("Falha ao salvar o pedido");
        }
    });
    alert("Pedido salvo com sucesso!");
    setArrayStorage([]);
}

Number.prototype.toBrl = function () {
    return 'R$ ' + this.toFixed(2).replace('.', ',');
};

