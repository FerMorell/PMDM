package ejercicios.ejercicio2

class Circulo(nome:String, val radio: Double, figuras: Figuras ):Area, Forma(nome, figuras) {
    override fun calcularArea(): Double {
return Math.PI* Math.sqrt(radio)
    }

    override fun toString(): String {
        return super.toString()+ " y el area es ${calcularArea()}"
    }

}