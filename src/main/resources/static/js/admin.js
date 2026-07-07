const API_URL = 'http://localhost:8080/productos';

document.addEventListener('DOMContentLoaded', () => {
    listarProductosAdmin();

    // Escuchar el formulario de agregar
    const form = document.getElementById("formAgregarProducto");
    if (form) {
        form.addEventListener("submit", agregarProducto);
    }
});

// 1. FUNCIÓN PARA MOSTRAR LOS PRODUCTOS
async function listarProductosAdmin() {
    const tabla = document.getElementById("tablaAdminProductos");
    if (!tabla) return;

    try {
        const respuesta = await fetch(API_URL);
        const productos = await respuesta.json();
        tabla.innerHTML = "";

        productos.forEach(producto => {
            tabla.innerHTML += `
                <tr>
                    <td>${producto.id}</td>
                    <td><strong>${producto.nombre}</strong></td>
                    <td>$${producto.precio}</td>
                    <td>
                        <button class="btn-quitar" onclick="eliminarProducto(${producto.id})">
                            <i class="fas fa-trash"></i> Quitar
                        </button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error("Error cargando tabla de admin:", error);
    }
}

// 2. FUNCIÓN POST: ENVIAR NUEVO PRODUCTO A JAVA
async function agregarProducto(e) {
    e.preventDefault();

    const nuevoProd = {
        nombre: document.getElementById("adminNombre").value,
        precio: parseFloat(document.getElementById("adminPrecio").value),
        stock: parseInt(document.getElementById("adminStock").value),
        imagenUrl: document.getElementById("adminImagen").value,
        categoria: {
        id: parseInt(document.getElementById("adminCategoria").value)
        }
    };

    try {
        const respuesta = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nuevoProd)
        });

        if (respuesta.ok) {
            alert("¡Producto guardado con éxito en MySQL!");
            document.getElementById("formAgregarProducto").reset(); 
            listarProductosAdmin(); 
        } else {
            alert("Error al guardar el producto. Revisá las validaciones en Java.");
        }
    } catch (error) {
        console.error("Error en el POST:", error);
    }
}

// 3. FUNCIÓN DELETE: ELIMINAR PRODUCTO
async function eliminarProducto(id) {
    if (!confirm(`¿Estás seguro de que querés eliminar el producto con ID ${id}?`)) return;

    try {
        const respuesta = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (respuesta.ok) {
            alert(`Producto ${id} eliminado de la base de datos.`);
            listarProductosAdmin(); 
        } else {
            alert("No se pudo eliminar el producto.");
        }
    } catch (error) {
        console.error("Error en el DELETE:", error);
    }
}