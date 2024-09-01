const productos = {} 
const contenedorProductos = document.querySelector('#cards');

const mostrarProductosCards = () => {
    
    //Escribir instrucciones ara manipular el dom de tal manera que en el contenedor se muestre la informacion
    for(const producto in productos){
        const productoInfo = productos[producto];
        const card = document.createElement("section").classList.add("card");

        card.innerHTML = ` <h3>${productoInfo.name} </h3>`;
        contenedorProductos.appendChild(card)
     }
}

const obtener = async() => {

    //Definiendo url del endpoint a consumir 
    const url = "http://localhost:8081/api/products"
    
    //Definiendo credenciasles 
    const username = "custom"
    const password = "custom"

    //Definimos un objeto para contener las opciones de conexion 
    //Se utilizara para el metodo fetch
    const options    = {
        method: "GET",
        headers: {
            "Authorization" : "Basic " + btoa(`${username}:${password}`),
        }
    }
    //Utilizaos el fetch para conectarnos 
    const respuesta = await fetch(url, options)
    const productos = await respuesta.json()
    
    mostrarProductosCards(productos)
    console.log(productos)
    
}
obtener();