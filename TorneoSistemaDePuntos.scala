case class TorneoSistemaDePuntos(
    val pesoClasificacion: Int,
    val puntajePorBaja: Int,
    override val idTorneo: Int,
    override val nombreTorneo: String,
    override val videojuego: Videojuego,
    override val modalidad: ModalidadTorneo,
    override val equipos: List[Equipo],
    override val estado: EstadoTorneo,
    override val partidas: List[Partida],
    override val nombreTipo: String = "Torneo de sistema por puntos"
) extends Torneo(idTorneo, nombreTorneo, videojuego, modalidad, equipos, estado, partidas, nombreTipo) {
    def actualizarEstadoTorneo(estadoNuevo: EstadoTorneo): Torneo = copy(estado = estadoNuevo)

    protected def detalleFinal(partidas: List[Partida]): String = {
        val jugadas = partidas.flatMap {
            case p: PartidaPuntos => List(p)
            case _ => Nil
        }
        val tabla    = Utilidades.calcularTabla(equipos, jugadas)(ReglasPuntaje.clasica)
        val podio    = Utilidades.top(tabla, 3)(_.puntosTotales)
        val empates  = Utilidades.contar(jugadas)(_.estado == FinalizadoEmpate)
        val anotados = jugadas.foldLeft(0)((acc, p) => acc + p.puntaje1 + p.puntaje2)

        s"Podio:\n${Utilidades.formatearPodio(podio)}" +
        s"Partidas jugadas: ${Utilidades.longitud(jugadas)}\n" +
        s"Empates: $empates\n" +
        s"Puntos anotados en total: $anotados"
}
}