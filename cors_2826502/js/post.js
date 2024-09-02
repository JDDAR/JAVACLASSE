//**********************************************************************************
//PARA previsualizar
//**********************************************************************************

//Elementos de previsualizar
const previewImg = document.querySelector(".card-img");
const previewName = document.querySelector("#info-nombreProducto");
const previewDescription = document.querySelector("#info-descripcion");
const previewPrecio = document.querySelector("#info-precio");

//Creamos los elementos para la imagenes
const createImg = document.createElement("img");
const createBlur = document.createElement("span");
createBlur.classList.add("cardBlur");

//agrego los elementos al contenedor de previsualizacion
previewImg.appendChild(createImg);
previewImg.appendChild(createBlur);

//Funciion para actualizar la previsualizacion

const actualizarPreview = () => {
  const urlImg = document.querySelector("#urlImg").value;
  const nombre = document.querySelector("#nombreProducto").value;
  const descripcion = document.querySelector("#descripcionProducto").value;
  const precio = document.querySelector("#precioProducto").value;

  //actualizo la funete de la imagen
  createImg.src = urlImg;
  previewImg.style.backgroundImage = `url(${createImg.src})`;

  //Actualizar los elementos
  previewName.textContent = nombre || "Nombre del producto";
  previewDescription.textContent =
    descripcion || " Agregue la descripcion del producto";
  previewPrecio.textContent = precio || "Agregue el precio";
  console.log(previewImg);
};

document
  .querySelector("#nombreProducto")
  .addEventListener("input", actualizarPreview);
document.querySelector("#urlImg").addEventListener("input", actualizarPreview);
document
  .querySelector("#descripcionProducto")
  .addEventListener("input", actualizarPreview);
document
  .querySelector("#precioProducto")
  .addEventListener("input", actualizarPreview);

//************************************************************************************
//Funcion para guardar el producto nuevo
//***********************************************************************************
const guardarProducto = async (e) => {
  //Anuñar comportamiento por defecto
  e.preventDefault();
  //Extraer los datos del formulario
  const producto = {
    url_img: document.querySelector("#urlImg").value,
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
