package com.unilocal.app.viewmodel

import androidx.lifecycle.ViewModel
import com.unilocal.app.model.City
import com.unilocal.app.model.Location
import com.unilocal.app.model.Place
import com.unilocal.app.model.PlaceType
import com.unilocal.app.model.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek
import java.time.LocalTime

class PlacesViewModel: ViewModel() {

    private val _places = MutableStateFlow(emptyList<Place>())
    val places: StateFlow<List<Place>> = _places.asStateFlow()

    init {
        loadPlaces()
    }

    fun loadPlaces(){

        _places.value = listOf(
            Place(
                id = "1",
                title = "El Fogón Paisa",
                description = "Sabores auténticos de la región antioqueña con el mejor ambiente familiar.",
                address = "Cra 12 # 12 - 12",
                location = Location(1.23, 2.34),
                images = listOf(
                    "https://picsum.photos/600/400",
                    "https://picsum.photos/300/200",
                    "https://picsum.photos/301/200",
                    "https://picsum.photos/302/200",
                    "https://picsum.photos/303/200"
                ),
                phoneNumber = "3123123123",
                type = PlaceType.RESTAURANT,
                city = City.ARMENIA,
                schedules = listOf(
                    Schedule(DayOfWeek.MONDAY, LocalTime.of(10, 0), LocalTime.of(20, 0)),
                    Schedule(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 0)),
                    Schedule(DayOfWeek.FRIDAY, LocalTime.of(10, 0), LocalTime.of(20, 0)),
                ),
                ownerId = "3"
            ),
            Place(
                id = "2",
                title = "Bar La Esquina",
                description = "Un bar moderno con buena música, cocteles artesanales y ambiente relajado.",
                address = "Calle 12 # 12 - 12",
                location = Location(1.23, 2.34),
                images = listOf("https://cdn0.uncomo.com/es/posts/6/8/4/como_gestionar_un_bar_22486_orig.jpg"),
                phoneNumber = "3123123123",
                type = PlaceType.BAR,
                city = City.ARMENIA,
                schedules = listOf(),
                ownerId = "2"
            ),
            Place(
                id = "3",
                title = "Hotel Mirador del Sol",
                description = "Descanso, confort y vistas increíbles en el corazón de la ciudad.",
                address = "Calle 12 # 12 - 12",
                location = Location(1.23, 2.34),
                images = listOf("https://cdn0.uncomo.com/es/posts/6/8/4/como_gestionar_un_bar_22486_orig.jpg"),
                phoneNumber = "3123123123",
                type = PlaceType.HOTEL,
                city = City.PEREIRA,
                schedules = listOf(),
                ownerId = "2"
            ),
            Place(
                id = "4",
                title = "Centro Comercial Andino Plaza",
                description = "Un lugar perfecto para compras, gastronomía y entretenimiento en un solo sitio.",
                address = "Calle 12 # 12 - 12",
                location = Location(1.23, 2.34),
                images = listOf("https://cdn0.uncomo.com/es/posts/6/8/4/como_gestionar_un_bar_22486_orig.jpg"),
                phoneNumber = "3123123123",
                type = PlaceType.SHOPPING,
                city = City.MEDELLIN,
                schedules = listOf(),
                ownerId = "3"
            ),
            Place(
                id = "5",
                title = "Galería Capital",
                description = "Centro comercial con marcas exclusivas, arte urbano y espacios gastronómicos únicos.",
                address = "Calle 12 # 12 - 12",
                location = Location(1.23, 2.34),
                images = listOf("https://cdn0.uncomo.com/es/posts/6/8/4/como_gestionar_un_bar_22486_orig.jpg"),
                phoneNumber = "3123123123",
                type = PlaceType.SHOPPING,
                city = City.BOGOTA,
                schedules = listOf(),
                ownerId = "3"
            ),
            Place(
                id = "6",
                title = "Parque Los Cedros",
                description = "Espacio natural ideal para pasear, hacer deporte o disfrutar de un picnic en familia.",
                address = "Calle 12 # 12 - 12",
                location = Location(1.23, 2.34),
                images = listOf("https://cdn0.uncomo.com/es/posts/6/8/4/como_gestionar_un_bar_22486_orig.jpg"),
                phoneNumber = "3123123123",
                type = PlaceType.PARK,
                city = City.BOGOTA,
                schedules = listOf(),
                ownerId = "3"
            )
        )


    }

    fun create(place: Place){
        _places.value = _places.value + place
    }

    fun findById(id: String): Place?{
        return _places.value.find { it.id == id }
    }

    fun findByType(type: PlaceType): List<Place>{
        return _places.value.filter { it.type == type }
    }

    fun findByName(name: String): List<Place>{
        return _places.value.filter { it.title.contains(other = name, ignoreCase = true) }
    }

    // Retorna los lugares de un usuario específico
    fun getPlacesByUser(userId: String): List<Place> {
        return _places.value.filter { it.ownerId == userId }
    }

    // Elimina un lugar por ID
    fun deletePlace(placeId: String) {
        _places.value = _places.value.filterNot { it.id == placeId }
    }

}