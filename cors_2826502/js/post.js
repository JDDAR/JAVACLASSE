const guardarProducto = async (e) => {
  //Anuñar comportamiento por defecto
  e.preventDefault();
  //Extraer los datos del formulario
  const producto = {
    name: document.querySelector("#nombreProducto").value,
    description: document.querySelector("#descripcionProducto").value,
    price: document.querySelector("#precioProducto").value,
  };

  //Haciendo la transmicion ************
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

  //Utilizaos el fetch para conectarnos
  const respuesta = await fetch(url, options);
  const productoGuardado = await respuesta.json();

  if (producto) {
    const mensaje = (document.querySelector("#mensaje").style.display =
      "block");
    console.log(mensaje);
  }
};

const formulario = document.querySelector("#formulario");
const submit = formulario.addEventListener("submit", guardarProducto);
