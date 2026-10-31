abstract class Torneo(
    val idTorneo: Int,
    val nombreTorneo: String,
    val videojuego: Videojuego,
    val modalidad: ModalidadTorneo,
    val equipos: List[Equipo],
    val estado: EstadoTorneo,
    val partidas: List[Partida],
    val nombreTipo: String
) {
    def descripcion(): String = s"$nombreTipo\nID: $idTorneo\nNombre: $nombreTorneo\nJuego: ${videojuego.titulo}\nModalidad: ${modalidad.descripcion()}\nEstado: ${estado.descripcion()}"

    def actualizarEstadoTorneo(estadoNuevo: EstadoTorneo): Torneo

    protected def detalleFinal(partidas: List[Partida]): String

    def resumenFinal(partidas: List[Partida]): String = 
        estado match
            case TorneoFinalizado => s"RESUMEN FINAL\n${descripcion()}\nEquipos participantes: ${Utilidades.longitud(equipos)}\n${detalleFinal(partidas)}"
            case _ => s"El torneo '$nombreTorneo' aún no ha finalizado (${estado.descripcion()})"
}