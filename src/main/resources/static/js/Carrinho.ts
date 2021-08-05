import {Produto} from "./Produto";

class Carrinho {
    private _produto: Produto[] = [];

    get produto(): Produto[] {
        return JSON.parse(localStorage.getItem("carrinho")) || [];
    }

    set produto(value: Produto[]) {
        localStorage.setItem("carrinho", JSON.stringify(value));
        this._produto = value;
    }

    calcularTotal(): number {
        return this._produto
            .map(value => value.preco)
            .reduce((previousValue, currentValue) => previousValue + currentValue);
    }


}
