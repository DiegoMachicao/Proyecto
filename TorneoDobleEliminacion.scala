case class TorneoDobleEliminacion(
    val cuadroGanadores: List[Partida],
    val cuadroPerdedores: List[Partida],
    override val idTorneo: Int,
    override val nombreTorneo: String,
    override val videojuego: Videojuego,
    override val modalidad: ModalidadTorneo,
    override val equipos: List[Equipo],
    override val estado: EstadoTorneo,
    override val partidas: List[Partida],
    override val nombreTipo: String = "Torneo de doble eliminacion"
) extends Torneo(idTorneo, nombreTorneo, videojuego, modalidad, equipos, estado, partidas, nombreTipo) {
    def actualizarEstadoTorneo(estadoNuevo: EstadoTorneo): Torneo = copy(estado = estadoNuevo)

    protected def detalleFinal(partidas: List[Partida]): String = {
        val campeon    = Utilidades.ganadorFinal(partidas)   // la gran final es la última partida
        val subcampeon = Utilidades.perdedorFinal(partidas)
        val jugadas    = Utilidades.contar(partidas)(_.finalizada)

        val trayectoria = campeon match
            case None => "Trayectoria no determinada"
            case Some(c) => val derrotas = Utilidades.contar(partidas)(_.perdedor == Some(c))
                if derrotas == 0 then "Campeón invicto"
                else s"Campeón con $derrotas derrota(s) en el camino"

        s"Campeón: ${Utilidades.nombreDe(campeon)} ($trayectoria)\n" +
        s"Subcampeón: ${Utilidades.nombreDe(subcampeon)}\n" +
        s"Partidas jugadas: $jugadas"
    }
}