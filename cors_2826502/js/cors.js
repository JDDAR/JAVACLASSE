const contenedorProductos = document.querySelector("#cards");

const mostrarProductosCards = (productos) => {
  // Limpiar el contenedor antes de agregar nuevas tarjetas
  contenedorProductos.innerHTML = "";

  // Iterar sobre los productos para crear y mostrar las tarjetas
  for (const producto of productos) {
    const card = document.createElement("div");
    card.classList.add("card");

    // Crear y añadir elementos dentro de la tarjeta
    const cardImg = document.createElement("div");
    cardImg.classList.add("card-img");

    // Crear el `span` para el desenfoque
    const cardBlur = document.createElement("span");
    cardBlur.classList.add("cardBlur");

    //Creando el src para la imagen
    const urlImg = document.createElement("img");
    urlImg.src = producto.url_img;

    cardImg.appendChild(urlImg);
    cardImg.appendChild(cardBlur);
    cardImg.style.backgroundImage = `url(${urlImg.src})`;

    // Crear componente para la informacion
    const cardInfo = document.createElement("div");
    cardInfo.classList.add("card-info");

    cardInfo.innerHTML = `
            <h3><span>${producto.name}</span></h3>
            <p><span>${producto.description || "descripcion sin datos"}</span></p>
            <h4>$ ${producto.price || "Precio no proporcionado"}</h4>
        `;

    // Añadir los elementos (Imagen e info ) a la tarjeta
    card.appendChild(cardImg);
    card.appendChild(cardInfo);

    // Añadir la card al contenedor
    contenedorProductos.appendChild(card);
  }
};

const obtener = async () => {
  //Definiendo url del endpoint a consumir
  const url = "http://localhost:8081/api/products";

  //Definiendo credenciasles
  const username = "custom";
  const password = "custom";

  //Definimos un objeto para contener las opciones de conexion
  //Se utilizara para el metodo fetch
  const options = {
    method: "GET",
    headers: {
      Authorization: "Basic " + btoa(`${username}:${password}`),
    },
  };
  //Utilizaos el fetch para conectarnos
  const respuesta = await fetch(url, options);
  const productosData = await respuesta.json();

  mostrarProductosCards(productosData);
  console.log(productosData);
};
obtener();
