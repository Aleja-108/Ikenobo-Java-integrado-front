console.log("Ikenobo API - Sistema de Carrito Activo");

const API_URL = 'http://localhost:8080/productos'; 
let productosDesdeBD = []; // Acá guardamos los productos reales que vienen de Java
let totalPagar = 0; // El acumulador del precio

// Seleccionamos los elementos del HTML del Carrito
const listCarrito = document.querySelector("#carrito ul");
const totalCarrito = document.querySelector("#carrito p");
const mensajePagarCarrito = document.getElementById("mensajeCarrito");
const botonBorrar = document.querySelector("#btn_borrar");
const botonPagar = document.querySelector("#btn_pagar");

document.addEventListener('DOMContentLoaded', () => {
    cargarIkebanasDesdeBackend();
    
    // Configuramos los botones de Borrar y Pagar una sola vez al cargar la página
    if (botonBorrar) botonBorrar.addEventListener("click", borrarCarrito);
    if (botonPagar) botonPagar.addEventListener("click", irPagar);
});

async function cargarIkebanasDesdeBackend() {
    const ikebanaContainer = document.getElementById("ikebanaContainer");
    if (!ikebanaContainer) return;
    
    try {
        const respuesta = await fetch(API_URL);
        if (!respuesta.ok) throw new Error('Error al conectar con la base de datos');
        
        productosDesdeBD = await respuesta.json();
        ikebanaContainer.innerHTML = "";

        if (productosDesdeBD.length === 0) {
            ikebanaContainer.innerHTML = "<p>No hay ikebanas disponibles en este momento.</p>";
            return;
        }

        productosDesdeBD.forEach((producto, indice) => {
            const imgUrl = producto.imagenUrl;
            const descripcionBack = producto.categoria && producto.categoria.descripcion 
                                    ? producto.categoria.descripcion 
                                    : 'Diseño exclusivo';
            
            // IMPORTANTE: El botón tiene type="button" (para que no recargue) y data-index con su posición
            ikebanaContainer.innerHTML += `
                <div class="card">
                    <div class="card-img-wrapper">
                        <img src="${imgUrl}" alt="${producto.nombre}">
                    </div>
                    <h3 class="card-title">${producto.nombre}</h3>
                    <p class="card-text">${descripcionBack}</p>
                    <p class="product-price">$${producto.precio}</p>
                    <div>
                        <button type="button" class="btn-full btn-agregar" data-index="${indice}">
                            <i class="fas fa-cart-plus"></i>
                        </button>
                    </div>
                </div>
            `;
        });

        // Una vez que se dibujaron las cards, les activamos el click
        activarBotonesAgregar();

    } catch (error) {
        console.error("Error cargando productos:", error);
        //ikebanaContainer.innerHTML = `<p style="color: #e53e3e; text-align: center;">Error al cargar las tarjetas.</p>`;
    }
}

// LÓGICA DEL CARRITO

function activarBotonesAgregar() {
    const botonesAgregar = document.querySelectorAll(".btn-agregar");
    
    botonesAgregar.forEach(boton => {
        boton.addEventListener("click", (e) => {
            // Buscamos el botón exacto que se presionó
            const botonClickeado = e.target.closest('.btn-agregar');
            const indice = botonClickeado.getAttribute("data-index");
            
            // Obtenemos los datos del producto usando el índice
            const productoSeleccionado = productosDesdeBD[indice];

            // 1. Creamos el elemento visual (un <li>) para meterlo dentro del <ul> del carrito
            const nuevoItem = document.createElement("li");
            nuevoItem.innerText = `${productoSeleccionado.nombre} - $${productoSeleccionado.precio}`;
            listCarrito.appendChild(nuevoItem);

            // 2. Sumamos el precio al total
            totalPagar += Number(productoSeleccionado.precio);
            totalCarrito.innerText = `Total a pagar $${totalPagar}`;
            
            if (mensajePagarCarrito) mensajePagarCarrito.innerText = ""; 
        });
    });
}

function borrarCarrito(e) {
    if (e) e.preventDefault();
    listCarrito.innerHTML = "";
    totalCarrito.innerText = "Total a pagar $0";
    totalPagar = 0;
    if (mensajePagarCarrito) mensajePagarCarrito.innerText = "";
}

function irPagar(e) {
    if (e) e.preventDefault();
    if (listCarrito.children.length === 0) {
        if (mensajePagarCarrito) mensajePagarCarrito.innerText = "No has seleccionado productos";
    } else {
        // Redirecciona a tu página de pagos si tenés todo listo
        window.location.href = "pages/pagos.html"; 
    }
}
// --- FUNCIONALIDAD DE LOS ÍCONOS DEL HEADER ---

document.addEventListener('DOMContentLoaded', () => {
    // 1. FUNCIÓN DE LA LUPA: Buscar por ID usando un prompt
    const iconoBuscar = document.getElementById("iconoBuscar");
    if (iconoBuscar) {
        iconoBuscar.addEventListener("click", async () => {
            const idBuscado = prompt("Ingrese el ID del Ikebana que desea buscar:");
            
            // Si cancela o no escribe nada, no hace nada
            if (!idBuscado) return; 

            const ikebanaContainer = document.getElementById("ikebanaContainer");
            try {
                // Va a buscar al endpoint de Java por ID
                const respuesta = await fetch(`${API_URL}/${idBuscado}`);
                
                if (!respuesta.ok) {
                    alert(`No se encontró ningún Ikebana con el ID: ${idBuscado}`);
                    return;
                }

                const producto = await respuesta.json();
                ikebanaContainer.innerHTML = ""; // Limpia la grilla

                const imgUrl = producto.imagenUrl;
                const descripcionBack = producto.categoria && producto.categoria.descripcion ? producto.categoria.descripcion : 'Diseño exclusivo';
                const indiceReal = productosDesdeBD.findIndex(p => p.id === producto.id);

                // Dibuja solo el producto encontrado
                ikebanaContainer.innerHTML = `
                    <div class="card" style="grid-column: 1/-1; max-width: 350px; margin: 0 auto;">
                        <div class="card-img-wrapper">
                            <img src="${imgUrl}" alt="${producto.nombre}">
                        </div>
                        <h3 class="card-title">${producto.nombre} <small style="color: #718096;">(ID: ${producto.id})</small></h3>
                        <p class="card-text">${descripcionBack}</p>
                        <p class="product-price">$${producto.precio}</p>
                        <div>
                            <button type="button" class="btn-full btn-agregar" data-index="${indiceReal >= 0 ? indiceReal : 0}">
                                <i class="fas fa-cart-plus"></i>
                            </button>
                        </div>
                        <button type="button" onclick="cargarIkebanasDesdeBackend()" style="margin-top: 15px; background: none; border: none; color: #3182ce; cursor: pointer; text-decoration: underline;">
                            Volver a ver todo el catálogo
                        </button>
                    </div>
                `;
                
                // Activa el botón de agregar al carrito para esta card
                activarBotonesAgregar();

            } catch (error) {
                console.error("Error al buscar:", error);
                alert("Error al conectar con el servidor.");
            }
        });
    }

    // 2. FUNCIÓN Acceso Admin
    const iconoAdmin = document.getElementById("iconoAdmin");
    if (iconoAdmin) {
        iconoAdmin.addEventListener("click", () => {
            const password = prompt("Ingrese la clave de Administrador:");
            if (password === "admin123") {
                alert("¡Acceso concedido!");
                window.location.href = "pages/admin.html";
                // Acá el día de mañana ponés tu redirección: window.location.href = "admin.html";
            } else if (password !== null) {
                alert("Clave incorrecta.");
            }
        });
    }
});