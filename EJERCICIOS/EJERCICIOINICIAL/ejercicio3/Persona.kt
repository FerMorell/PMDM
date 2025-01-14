package ejercicio3

class Persona(var nombre:String,var peso:Int, var altura:Double) {
	fun calcularIMC() : Double { return (peso/(altura*altura))}
	fun valorarIMC(): String {
		var resultado=calcularIMC();
		if (resultado<20){
			return "Esta por debajo de su peso ideal"
		}  else if (resultado>20 && resultado<=25){
			return "Estas en el peso ideal"
		}else if(resultado>25){
			return "Estas por encima del peso ideal"
		}
		return "fallo";
	}


	override fun toString(): String {
		return "La persona ${nombre} y tiene un IMC de ${calcularIMC()} y eso implica ${valorarIMC()}"
	}
}


