import ejercicio3.Persona
import java.lang.System
import java.util.Scanner


fun  main(){

	val sc= Scanner(System.`in`)
	println("Indicame si quieres introducir más datos de personas(S/N)")
	var respuesta:String = sc.nextLine().trim().uppercase();
	var listaPersona= mutableListOf<Persona>()
	while (respuesta!="N"){
		println("Dime el nombre de la persona ")
	val nombre:String = sc.nextLine().uppercase();
		println("Dime el peso de la persona")
val peso:Int= sc.nextInt()
		sc.nextLine()
		println("Dime la altura de la persona")
	val altura:Double = sc.nextDouble()
		sc.nextLine()
		val p= Persona(nombre,peso,altura)
		listaPersona.add(p)
		println("Indicame si quieres introducir más datos de personas(S/N)")
		 respuesta= sc.nextLine().trim().uppercase();
	}

for(p in listaPersona) {
	println(p.toString())
}
}