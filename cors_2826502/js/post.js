const guardarProducto = async (e) => {
  //Anuñar comportamiento por defecto
  e.preventDefault();
  //Extraer los datos del formulario
  const producto = {
    name: document.querySelector("#nombreProducto").value,
    description: document.querySelector("#descripcionProducto").value,
    price: document.querySelector("#precioProducto").value,
  };

  //Haciendo la transmision ************
  const url = "http://localhost:8081/api/products";

  //Definiendo credenciasles
  const username = "admin";
  const password = "admin";

  //Definimos un objeto para contener las opciones de conexion
  //Se utilizara para el metodo fetch
  const options = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Basic " + btoa(`${username}:${password}`),
    },
    body: JSON.stringify(producto),
  };

  try {
    //fetch para conectarnos
    const respuesta = await fetch(url, options);

    //Verificando si la respuesta fue exitosa
    if (respuesta.ok) {
      //Respuesta exitosa
      const productoGuardado = await respuesta.json();
      console.log("producto Guardado con exito: ", productoGuardado);

      //Mostrando mensaje de exito
      document.querySelector("#mensaje").style.display = "block";
    } else {
      //Respuesta con errores
      console.log("Error al crear el producto: ", respuesta.status);
      const errorMensaje = (document.querySelector("#mensaje").style.display =
        "block");
      const error = errorMensaje.classList.add("mensajeError");
    }
  } catch (error) {
    //Manejando los errores de red o del fetch
    console.log("Error al enviar el producto: ", error);
  }
};

const formulario = document.querySelector("#formulario");
const submit = formulario.addEventListener("submit", guardarProducto);
