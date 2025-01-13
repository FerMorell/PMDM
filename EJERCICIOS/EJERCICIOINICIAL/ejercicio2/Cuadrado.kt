package ejercicios.ejercicio2

 class Cuadrado(nombre:String,figuras: Figuras, val lado:Double):Area, Forma(nombre,figuras) {

    override fun calcularArea(): Double {
        return Math.pow(lado, 2.0)
    }
     override fun toString(): String {
         return super.toString()+ " y el area es ${calcularArea()}"
     }
}