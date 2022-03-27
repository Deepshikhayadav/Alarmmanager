package com.deepshikhayadav.notificationtesting

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepshikhayadav.notificationtesting.ui.theme.NotificationTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
   // Text(text = "Hello $name!")
    val context= LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Alarm Manager notification",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center)
                })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "set Alrm time 10 sec",
                modifier = Modifier.padding(10.dp),
                fontSize = 16.sp)
            Button(onClick = { setAlarm(context) }) {
                Text(text = "Set Alarm")
            }
        }

    }

}

fun setAlarm(context: Context) {
    val timesec= System.currentTimeMillis() +1000
    val alrmManager= context.getSystemService(ALARM_SERVICE) as AlarmManager
    val intent=Intent(context, MyAlarm::class.java)
    val pendinIntent =PendingIntent.getBroadcast(context,0,intent,0)
    alrmManager.set(AlarmManager.RTC_WAKEUP,timesec,pendinIntent)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotificationTestingTheme {
        Greeting("Android")
    }
}