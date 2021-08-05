$(function () {

    let items = [];
    setArrayStorage(items)
    items = getArrayStorage();

    if (!items || items.length === 0) {
        items = [];
        items.push({
            "nome": "Processador AMD Ryzen 5 3600 Cache 32MB",
            "img": "processador",
            "quantidade": 1,
            "preco": 1600
        });
        setArrayStorage(items);
    }
    items.forEach(function (item, index) {
        console.log(item);
        $('#carrinho-produtos tbody').append(novaTr(item.nome, item.preco, item.img));
        calcularTotal();
    });

});

function setArrayStorage(arr) {
    localStorage.setItem('produtos', JSON.stringify(arr));
}

function getArrayStorage() {
    return JSON.parse(localStorage.getItem('produtos')) || [];
}

function novaTr(nome, preco, img) {
    // let produto = novoProduto();
    return `<tr>
    <td>
        <figure class="itemside">
            <div class="aside"><img width="80px" height="80px"
                src="img/${img}.jpg"
                class="img-sm" alt="${nome}"></div>
            <figcaption class="info">
                <a href="#" class="title text-dark">${nome}</a>
            </figcaption>
        </figure>
    </td>
    <td>
        <select class="form-control" id="preco-select" onchange="calcularTotal()">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
        </select>
    </td>
    <td id="td-preco">
        <div class="price-wrap">
            <var id="preco" >${preco}</var>
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
    carregarProdutoTable();
}

function carregarProdutoTable() {
    let items = [];
    $('#carrinho-produtos tbody').each(function () {
        let coluna = $(this).children();
        //console.log(coluna);
        let produto = {
            img: $(coluna[0]).text(),
            nome: $(coluna[1]).text(),
            quantidade: $(coluna[2]).text(),
            preco: $(coluna[3]).text()
        }
        items.push(produto);
        console.log(items);
    })
    calcularTotal();
    util();
    setArrayStorage(items);
}

function calcularTotal() {
    let subtotal = 0;
    $('#carrinho-produtos tbody tr').each(function (index, tr) {//tr é a tr
        console.log(tr);
        let preco = Number($(tr).children('[id="td-preco"]').text()) * $("#preco-select").val();
        subtotal += preco;
        subtotal = subtotal.toBrl();
    })
    $('#total').text(subtotal);

}

function mascaraValor(valor) {
    valor = valor.toString().replace(/\D/g, "");
    valor = valor.toString().replace(/(\d)(\d{8})$/, "$1.$2");
    valor = valor.toString().replace(/(\d)(\d{5})$/, "$1.$2");
    valor = valor.toString().replace(/(\d)(\d{2})$/, "$1,$2");
    return valor
}

Number.prototype.toBrl = function () {
    return 'R$ ' + this.toFixed(2).replace('.', ',');
};
