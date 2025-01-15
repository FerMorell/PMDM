package ejercicio4

import java.util.*

fun main(){
	val RESET = "\u001B[0m"  // Restablece el color
	val RED = "\u001B[31m"    // Color rojo
	val GREEN = "\u001B[32m"  // Color verde
	val YELLOW = "\u001B[33m" // Color amarillo
	val BLUE = "\u001B[34m"   // Color azul
	val scanner = Scanner(System.`in`)
println("Dime cuantos jugadores van a jugar (entre 2 y 6)")
	var respuesta=scanner.nextInt();
	val listaJugadores= mutableListOf<Persona>()
	if(respuesta<2 || respuesta>6){
		println("Por favor, elige un numero valido")
		respuesta=scanner.nextInt();
	}


	for(i in 1..respuesta){
		println("Dime el nombre del jugador")
		var nombre=scanner.next()
		listaJugadores.add(Persona(nombre));
	}
	println("Empieza la partida!!")

	println("****************************************")
	var r= Revolver();
	r.colocarBala()

	var jugadoresVivitos=respuesta;
	var turno=0;

	while(jugadoresVivitos>1){
		val jugador= listaJugadores[turno]

		if(jugador.estaVivo){
	println("Le toca el turno al jugador ${jugador.nombre}")
	println("¿Quires jugar?(S/N, aviso todo lo que sea S, sera N)")
	var juega=scanner.next().uppercase();

			if(juega.equals("S")){
				println("${YELLOW}Bien hecho. Eres un/a valiente, aunque de valientes estan llenos los cementerios${RESET} ")
					if(r.disparar()){
						println("${RED}PUUUUM el jugador ${jugador.nombre} ha muerto${RESET}");
						jugador.estaVivo=false
						jugadoresVivitos--
						r.colocarBala()
					}else{
						println("${GREEN}CLICK :O el jugador ${jugador.nombre} ha sobrevivido${RESET}");
					}
			}else{
				println("${RED}El jugador ${jugador.nombre} ha decidido no jugar. Asi que igualmente muere >:D${RESET}");
				jugador.estaVivo=false
				jugadoresVivitos--;
			}

    	 }
		turno++;
		if(turno>= listaJugadores.size){
			turno=0;
		}

	}
	for (jugador in listaJugadores){
		if(jugador.estaVivo){
			println("${GREEN}El juego ha terminado con el ganador/a ${jugador.nombre}${RESET}")
			break
		}
	}
	println("${YELLOW}Fin del juego${RESET}")
println("*************************************")

}
