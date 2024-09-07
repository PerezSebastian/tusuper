import { addToCart, loadCart} from "./mycart.js";

const navLinks = document.querySelectorAll('.nav__links');
const openButton = document.getElementById('nav-button-open');
const closeButton = document.getElementById('nav-button-close');
let productCartList = localStorage.getItem('productCartList')?JSON.parse(localStorage.getItem('productCartList')):[];
// botones para expandir marcas en los filtros
const brandExpand = document.querySelectorAll('.label-input')
const brandExpandStyles = window.getComputedStyle(document.getElementById('brand-expand'),':after')

// let productToAdd = document.querySelectorAll(".productCard__product-name");


openButton.addEventListener('click',() => {
    navLinks.forEach((nav) => nav.classList.toggle('show'));
    closeButton.classList.toggle('show');
    openButton.classList.toggle('noshow');
})
closeButton.addEventListener('click',() => {
    navLinks.forEach((nav) => nav.classList.toggle('show'));
    openButton.classList.toggle('noshow');
    closeButton.classList.toggle('show');
})

brandExpand.forEach(elemento => {
    elemento.addEventListener('click', () => {
        brandExpandStyles.content="++++"
    })
});

// Agregando funcionamiento de carrito

// cargo carrito
loadCart();

const addToCartButtons = document.querySelectorAll(".productCard__add")

addToCartButtons.forEach(button => {
    button.addEventListener('click',(e)=>{
        e.preventDefault();
        const productId = e.target.getAttribute('data-id');
        const productName = e.target.getAttribute('data-name'); // Obtener el ID del producto
        const productPrice = e.target.getAttribute('data-price'); // Obtener el ID del producto
        addToCart({productId,productName,productPrice});
    });
});