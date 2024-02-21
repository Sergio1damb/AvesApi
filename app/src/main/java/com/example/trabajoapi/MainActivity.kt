package com.example.trabajoapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.trabajoapi.ui.theme.TrabajoApiTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.AvesApi.repositorio.ViewModelRetrofit
import com.example.AvesApi.repositorio.estadoApi
import com.example.trabajoapi.repositorio.Ave

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabajoApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel : ViewModelRetrofit = viewModel()
                    val estado by viewModel.estadoLlamada.collectAsState()
                    val listaAves by viewModel.listaAves.collectAsState()
                    val listaHabitats by viewModel.listaHabitats.collectAsState()
                    if(estado == estadoApi.LOADING){
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
                            Text(
                                text = "Cargando Las Aves y Hábitats",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }else{
                        Column() {
                            Text(text = "Aves", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))
                            LazyColumn(){
                                items(listaAves!!){
                                    ItemAve(ave = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemAve(ave : Ave){
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(bottom = 32.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(model = ave.imagen, contentDescription = null, modifier =
            Modifier
                .size(300.dp)
                .wrapContentWidth(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit,
                alignment = Alignment.Center)
            Text(text = ave.nombre, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.onBackground, modifier = Modifier.padding(top = 8.dp)) // Reduce el espacio entre la imagen y el nombre
        }
    }
}