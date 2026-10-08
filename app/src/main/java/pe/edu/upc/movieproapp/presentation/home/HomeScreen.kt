package pe.edu.upc.movieproapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.edu.upc.movieproapp.R

@Composable
fun HomeScreen(onPopularClick: () -> Unit, onFavoritesClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "Logo de MovieProApp",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = onPopularClick, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Películas populares")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(onClick = onFavoritesClick, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text("Mis favoritos")
        }
    }
}
