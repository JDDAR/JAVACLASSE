const obtener = async() => {

    //Definiendo url del endpoint a consumir 
    const url = "http://localhost:8081/api/products"
    
    //Definiendo credenciasles 
    const username = "user"
    const password = "a4c651cc-5cab-491b-bbbf-5b9a353aff0c"

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
    
    console.log(productos);
}
obtener();