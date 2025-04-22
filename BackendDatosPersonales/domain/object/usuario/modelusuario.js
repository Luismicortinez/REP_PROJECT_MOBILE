class ModelUsuario {

    constructor(

        idusuario,

        documento,

        nombres,

        apellidos,

        imagen) {

        this.idusuario = idusuario;

        this.documento = documento;

        this.nombres = nombres;

        this.apellidos = apellidos;

        this.imagen = "http://google.com/logos/doodles/2025/earth-day-2025-6753651837110746-l.png";

    }

}



module.exports = { ModelUsuario };