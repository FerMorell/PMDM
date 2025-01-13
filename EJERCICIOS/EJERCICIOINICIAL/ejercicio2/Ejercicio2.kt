package ejercicios.ejercicio2

fun main(){
val c= Cuadrado(
    nombre = "Cuadrado",
    figuras = Figuras.CUADRADO,
    lado = 6.0
);
    val t= Triangulo(
        nombre = "Triángulo",
        figuras = Figuras.TRIÁNGULO,
        lado = 7.0,
        altura = 14.0
    )

    val cir= Circulo(
        nome = "Círculo",
        radio = 8.0,
        figuras = Figuras.CÍRCULO
    );

val p= Plano(
    nombre = "Plano",
    figuras = Figuras.OTRAS
);
    println(c.toString()+"\n"+t.toString()+"\n"+cir.toString()+"\n"+p.toString()+"\n");
}

