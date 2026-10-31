def añadirTorneoActivo(torneos: List[Torneo], torneoNuevo: Torneo): List[Torneo] = {
    torneos :+ torneoNuevo
}

def eliminarTorneosFinalizados(torneos: List[Torneo]): List[Torneo] = {
    torneos.filter(torneo => torneo.estado != TorneoFinalizado)
}

def listarTorneosActivos(torneos: List[Torneo]): String = {
    torneos match
        case Nil => ""
        case cabeza :: cola => cabeza.estado match
                                    case TorneoEnCurso => s"${cabeza.descripcion()}\n\n${listarTorneosActivos(cola)}"
                                    case _ => s"${{listarTorneosActivos(cola)}}"
}

def porEstado(e: EstadoTorneo): Torneo => Boolean = _.estado == e
def porVideojuego(titulo: String): Torneo => Boolean = _.videojuego.titulo == titulo

def y[A](p: A => Boolean, q: A => Boolean): A => Boolean = a => p(a) && q(a)
def o[A](p: A => Boolean, q: A => Boolean): A => Boolean = a => p(a) || q(a)
def no[A](p: A => Boolean): A => Boolean = a => !p(a)

object Main{
    def main(args: Array[String]): Unit = {
    }
}