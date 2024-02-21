package com.example.AvesApi.repositorio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.AvesApi.repositorio.AvesApi
import com.example.AvesApi.repositorio.AvesApi.retrofitService
import com.example.trabajoapi.repositorio.Ave
import com.example.trabajoapi.repositorio.Habitat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class estadoApi{
    IDLE, LOADING, SUCCESS, ERROR
}

public class ViewModelRetrofit : ViewModel() {
    private val _listaAves : MutableStateFlow<List<Ave>?> = MutableStateFlow(null)
    var listaAves = _listaAves.asStateFlow()

    private val _listaHabitats : MutableStateFlow<List<Habitat>?> = MutableStateFlow(null)
    var listaHabitats = _listaHabitats.asStateFlow()

    private val _estadoLlamada : MutableStateFlow<estadoApi> = MutableStateFlow(estadoApi.IDLE)
    var estadoLlamada = _estadoLlamada.asStateFlow()

    private val _textoBusqueda : MutableStateFlow<String> = MutableStateFlow("")
    var textoBusqueda = _textoBusqueda.asStateFlow()

    fun actualizarTextoBusqueda(s: String) { _textoBusqueda.value = s }

    private val _activo = MutableStateFlow(false)
    var activo = _activo.asStateFlow()

    fun actualizarActivo(b : Boolean) {_activo.value = b}
    init {
        obtenerAves()
        obtenerHabitats()
    }
    fun obtenerAves(){
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            var respuesta = retrofitService.getAllAves()
            if(respuesta.isSuccessful){
                _listaAves.value = respuesta.body()
                _estadoLlamada.value = estadoApi.SUCCESS
                println(respuesta.body().toString())
            }
            else println("Ha habido algún error " + respuesta.errorBody())
        }
    }

    fun obtenerHabitats(){
        _estadoLlamada.value = estadoApi.LOADING
        viewModelScope.launch {
            var respuesta = retrofitService.getAllHabitats()
            if(respuesta.isSuccessful){
                _listaHabitats.value = respuesta.body()
                _estadoLlamada.value = estadoApi.SUCCESS
                println(respuesta.body().toString())
            }
            else println("Ha habido algún error " + respuesta.errorBody())
        }
    }
}