package ejercicios

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main(){
    println("Elige una de las siguientes opciones:\n");
    val sc= Scanner(System.`in`);
    println("1 - Saber si un día es laborable o fin de semana\n");
    println("2 - Divisibles entre 3\n")
    println("3 - Siguiente divisible entre 7\n")
    println("4 - Notas del aula\n")
    println("5 - Divisibles entre 11\n")
    println("6 - Calcula el precio del cine\n")
    val eleccion= sc.nextInt();

    if(eleccion == 1){
        hacerEleccionUno(sc);
    }else if(eleccion == 2){
        hacerEleccionDos(sc);
    }else if(eleccion == 3){
        hacerEleccionTres(sc);
    }else if(eleccion == 4){
        hacerEleccionCuatro(sc);
    }else if(eleccion == 5){
        hacerEleccionCinco(sc);
    }else if(eleccion == 6){
        hacerEleccionSeis(sc);

    }else{
    println("La opción elegida no es válida")
    }


}

fun hacerEleccionUno(sc:Scanner) {
    println("Dime el dia:")
    val dia=sc.nextInt();
    println("Dime el mes:")
    var mes=sc.nextInt();
    mes=mes-1;
    println("Dime el año:")
    val anio=sc.nextInt();

    val calendar= Calendar.getInstance();
    calendar.set(anio,mes,dia)
    val diaSemana:Int = calendar.get(Calendar.DAY_OF_WEEK);
    println(diaSemana)
//    println(Calendar.SATURDAY)
    if((diaSemana==Calendar.SUNDAY || diaSemana==Calendar.FRIDAY)){
        println("El dia elegido no Laborable")
    }else {
        println("El dia elegido era Laborable")
        
    }
}

    fun hacerEleccionDos(sc:Scanner) {
        println("Dime el numero del que debo partir")
        val numero=sc.nextInt()
        for (i in 0.. 25){
            val numerito=numero+i;
            if(numerito%3==0){
                println("El numero $numerito es divisible entre 3")
            }
        }
    }
    fun hacerEleccionTres(sc:Scanner) {
    println("Dime el numero del que debo partir")
        val numero=sc.nextInt()
        for (i in 0..10){
            val numerito=numero+i;
            if(numerito%7==0){
                println("El numero $numerito es divisible entre 7")
            return;
            }
        }

    }
fun hacerEleccionCuatro(sc:Scanner) {
    println("Cuantos alumnos son? ")
    val numeroAlumnos=sc.nextInt()
    var notasTotal=0
    val todasLasNotas= arrayOfNulls<Int>(numeroAlumnos)
    val media:Int
    for (i in 0 until  numeroAlumnos){
        println("Dame la siguiente nota: ")
        val numeroNota=sc.nextInt()
        todasLasNotas[i] = numeroNota
        notasTotal += numeroNota;
    }
    media = notasTotal/numeroAlumnos
    println("Media: $media")
    for (i in 0 until todasLasNotas.size){
        if(todasLasNotas[i]!! >media){
            println("La nota ${todasLasNotas[i]} está por encima de la media")
        }
    }
}
fun hacerEleccionCinco(sc:Scanner) {
    println("Dime el numero del que debo partir")
        val numero=sc.nextInt()
    println("Dime cuantos numeros a partir del anterior quieres que busque")
        val numeroParaElFor=sc.nextInt()
        var contador=0;
        for (i in 0..numeroParaElFor){
            val numerito=numero+i
            if(numerito%11==0){
                println("El numero $numerito es divisible entre 11")
               contador++
            }
        }
        if (contador == 0){
            println("No hay ningún numero divisible entre 11 con el dato dado :O")
        }


}
fun hacerEleccionSeis(sc:Scanner) {
    println("Dime cuantas entradas vas a comprar ")
    val numeroEntrar=sc.nextInt()
    println("Para la fecha son las entradas (mete la fecha en formato dd/mm/yyyy)")
    val fecha=sc.next()

    var total=0.0;
        for(i in 1..numeroEntrar){
            println("Dime la edad de la persona para la entrada $i")
            val edad=sc.nextInt()
            if(edad>=3 && edad<=14){
                println("El precio de la entrada de la persona para la entrada $i es de 5,5$")
                total+=5.5;
            }else if(edad>=65 && edad<=100){
                println("El precio de la entrada de la persona para la entrada $i es de 4.5$")
                total+=4.5;

            }else if(edad>=15 && edad<=64){
             val fechita= LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (fechita.dayOfWeek == DayOfWeek.WEDNESDAY){
                    println("El precio de la entrada de la persona para la entrada $i es de 6$")
                    total+=6;


                }else{
                    println("El precio de la entrada de la persona para la entrada $i es de 9,6$")
                    total+=9.6;


                }
        }else{
                println("El precio de la entrada de la persona para la entrada $i es de 0.0$")
                total+=0.0;

            }
    }
println("--------------------------------------------------------------------------------")
println("El precio total de las entradas es $total")
    println("--------------------------------------------------------------------------------")

}
