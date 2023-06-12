/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package mx.edu.utxj.ddi.practica07_200527.presentation

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import mx.edu.utxj.ddi.practica07_200527.R
import mx.edu.utxj.ddi.practica07_200527.presentation.theme.Practica07_200527Theme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    private var mHandler: Handler? = null
    private var mRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHandler = Handler()
        mRunnable = Runnable {
            updateTime()
            mRunnable?.let { mHandler?.postDelayed(it, 1000) }
        }

    }

    private fun updateTime() {
        val timeTextView = findViewById<TextView>(R.id.HoraParaVisualizar)

        val currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())

        timeTextView.text = currentTime
    }
    override fun onResume() {
        super.onResume()
        mRunnable?.let { mHandler?.post(it) }
    }

    override fun onPause() {
        super.onPause()
        mRunnable?.let { mHandler?.removeCallbacks(it) }
    }


}

@Composable
fun WearApp(greetingName: String) {
    Practica07_200527Theme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        Column(
            modifier = Modifier
                .fillMaxSize() // fill the entire screen
                .background(MaterialTheme.colors.background), // background color
            verticalArrangement = Arrangement.Center // centers the content vertically
        ) {
            Greeting(greetingName = greetingName) // pass the greeting name to the Greeting composable
        }
    }
}

@Composable
fun Greeting(greetingName: String) { // receives the greeting name from the WearApp composable
    Text(
        modifier = Modifier.fillMaxWidth(), // fill the entire width
        textAlign = TextAlign.Center, // centers the text
        color = MaterialTheme.colors.primary, // uses the primary color from the theme
        text = stringResource(R.string.hello_world, greetingName) // gets the string from the resources
    )
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true) // preview for a small round watch
@Composable
fun DefaultPreview() {
    WearApp("Preview Android") // pass the greeting name to the WearApp composable
}