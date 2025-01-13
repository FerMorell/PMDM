package ejercicios.ejercicio2

open class Forma{
   var nombre: String
   var figuras: Figuras

   constructor(nombre: String, figuras: Figuras) {
       this.nombre = nombre
       this.figuras = figuras

   }


    override fun toString(): String {
        return "La forma se llama $nombre y es del tipo $figuras"
    }
}