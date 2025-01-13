package ejercicios.ejercicio2

class Triangulo(nombre:String, figuras: Figuras, val lado:Double, val altura:Double):Forma(nombre,figuras),Area {
    override fun calcularArea(): Double {
return (lado*altura /2)    }
    override fun toString(): String {
        return super.toString()+" y el area es ${calcularArea()}"
    }
}