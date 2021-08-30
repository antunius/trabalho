function setArrayStorage(arr) {
    localStorage.setItem('listaProd', JSON.stringify(arr));
}

function getArrayStorage() {
    return JSON.parse(localStorage.getItem('listaProd')) || [];
}


$(function () {
    let items = getArrayStorage();
    items.forEach(function (item, index) {
        $('#carrinho-produtos tbody').append(novaTr(item.produto));
        calcularTotal();
    });
});

function novaTr(produto) {
    // let produto = novoProduto();
    return `<tr>
    <td>
        <figure class="itemside">
            <div class="aside">
                <img width="80px" height="80px" src="${produto.img}" class="img-sm" alt="${produto.nome}">
            </div>
            <figcaption class="info">
                <a href="#" class="title text-dark">${produto.nome}</a>
            </figcaption>
        </figure>
    </td>
    <td>
        <input class="sc-bGbJRg ePKBGA" style="width: 25px" value="1" id="${produto.id}" onchange="atualizarQuantidade(this);calcularTotal()">
    </td>
    <td id="td-preco">
        <div class="price-wrap">
            <var id="preco">${parseFloat(produto.valor).toBrl()}</var>
        </div>
    </td>
    <td class="text-right">
        <a href="" onclick="excluirProduto(event, this); class="btn btn-light"> Remover</a>
    </td>
</tr>`;
}

function excluirProduto(e, element) {
    e.preventDefault();
    //a função parents retorna o elemento pai da td, que é tr
    let tr = $(element).parents('tr');
    $(tr).remove();
    calcularTotal();
    //carregarProdutoTable();
}

function atualizarQuantidade(produtoQuantidade) {
    console.log(produtoQuantidade.value);
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
Number.prototype.toBrl = function () {
    return 'R$ ' + this.toFixed(2).replace('.', ',');
};
function finalizarCompra() {
    pedido = getArrayStorage();

    $.ajax({
        type: 'post',
        url: 'compra/finalizar',
        contentType: 'application/json',
        data: JSON.stringify(pedido),
        success: function () {
            alert("Pedido salvo com sucesso!");
            setArrayStorage([]);
        },
        error: function () {
            alert("Falha ao salvar o pedido");
        }
    });
}

Number.prototype.toBrl = function () {
    return 'R$ ' + this.toFixed(2).replace('.', ',');
};

