let productCartList = localStorage.getItem('productCartList') ? JSON.parse(localStorage.getItem('productCartList')) : [];

export function addToCart(product) {
    const resp = productCartList.findIndex((prod) => prod.productId == product.productId)
    console.log(resp);
    console.log(resp !== -1);
    console.log("--------");
    console.log(`id: {product.productId}`);
    console.log(`nombre: {product.productName}`);
    console.log(`precio: {product.productPrice}`);

    if (resp == -1) {
        product.cant = 1;
        productCartList.push(product);
    } else {
        productCartList[resp].cant++;
        console.log(productCartList[resp]);
    }
    localStorage.setItem("productCartList", JSON.stringify(productCartList));
    loadCart();
}

export function loadCart() {
    const order = document.getElementById('order')
    order.innerHTML = "";
    productCartList.forEach(e => {
        const article = document.createElement("article");
        article.classList.add("order__productcard");
        article.innerHTML = ` 
        <div><img src="https://www.lareinaonline.com.ar/Fotos/Articulos/NoImagen.jpg" alt=""></div>
        <div><p>${e.productName}</p></div>
        <div><p>$${e.productPrice}</p></div>
        <div class="productcard__cant">
            <button data-id="${e.productId}" class="del-button btn-secondary">-</button>
            <p>(${e.cant})</p>
            <button data-id="${e.productId}" class="add-button btn-secondary">+</button>
        </div>`;
        order.appendChild(article);
    });
    // Botones para modificar cantidad de productos + o -
    document.querySelectorAll(".add-button").forEach(button => {
        button.addEventListener('click', (e) => {
            const productId = e.target.getAttribute('data-id');
            addProduct(productId);
        })
    })

    document.querySelectorAll(".del-button").forEach(button => {
        button.addEventListener('click', (e) => {
            const productId = e.target.getAttribute('data-id');
            delProduct(productId);
        })
    })
    cantProduct();
    totalPrice();
}

function addProduct(productId) {
    const resp = productCartList.findIndex((prod) => prod.productId == productId)
    if (resp != -1) {
        productCartList[resp].cant++;
        console.log(productCartList[resp]);
    }
    localStorage.setItem("productCartList", JSON.stringify(productCartList));
    console.log(resp);
    loadCart();
}

function delProduct(productId) {
    const resp = productCartList.findIndex((prod) => prod.productId == productId)
    if (resp != -1) {
        if (productCartList[resp].cant > 1) {
            productCartList[resp].cant--;
            console.log(productCartList[resp]);
        } else {
            productCartList.splice(resp, 1);
        }
    }
    localStorage.setItem("productCartList", JSON.stringify(productCartList));
    loadCart();
}

function cantProduct() {
    let cantProduct = 0;
    productCartList.forEach(e => {
        cantProduct += e.cant;
    });
    const placesToReplace = document.querySelectorAll(".cantProduct");
    placesToReplace.forEach(e => {
        e.innerHTML = cantProduct;
    });
}

function totalPrice() {
    let totalPrice = 0;
    productCartList.forEach(e => {
        totalPrice += e.cant * e.productPrice;
    });
    document.querySelector(".total__price-price").innerHTML = (totalPrice!=0)?`$ ${totalPrice}`:`$ 0,00`;
}