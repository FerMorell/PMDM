package ejercicio4

class Revolver {
//	Si pongo el constructor asi, si puedo simplemente crearlo de chill, sin poner parametros en el otro lado
	var  posicionBala: Int =0
	var posicionTambor : Int=0;
	fun disparar() : 	Boolean{

		val disparo=(posicionBala == posicionTambor);

		posicionTambor=(posicionTambor%6)+1
		return disparo

	}

	fun colocarBala(){
		posicionBala = (Math.random()*(6-1)+1).toInt();
		posicionTambor = (Math.random()*(6-1)+1).toInt();
	}
}