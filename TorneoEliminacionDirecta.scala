case class TorneoEliminacionDirecta(
    override val idTorneo: Int,
    override val nombreTorneo: String,
    override val videojuego: Videojuego,
    override val modalidad: ModalidadTorneo,
    override val equipos: List[Equipo],
    override val estado: EstadoTorneo,
    override val partidas: List[Partida],
    override val nombreTipo: String = "Torneo de eliminacion directa"
) extends Torneo(idTorneo, nombreTorneo, videojuego, modalidad, equipos, estado, partidas, nombreTipo) {
    def actualizarEstadoTorneo(estadoNuevo: EstadoTorneo): Torneo = copy(estado = estadoNuevo)

    protected def detalleFinal(partidas: List[Partida]): String = {
        val campeon    = Utilidades.ganadorFinal(partidas)   // la final es la última partida
        val subcampeon = Utilidades.perdedorFinal(partidas)
        val jugadas    = Utilidades.contar(partidas)(_.finalizada)

        s"Campeón: ${Utilidades.nombreDe(campeon)}\n" +
        s"Subcampeón: ${Utilidades.nombreDe(subcampeon)}\n" +
        s"Partidas jugadas: $jugadas"
}
}