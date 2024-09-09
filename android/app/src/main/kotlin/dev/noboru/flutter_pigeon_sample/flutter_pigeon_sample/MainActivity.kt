package dev.noboru.flutter_pigeon_sample.flutter_pigeon_sample

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import com.example.pigeon_plugin.BatteryApi
import com.example.pigeon_plugin.BatteryResult
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        BatteryApi.setUp(
            flutterEngine.dartExecutor.binaryMessenger,
            BatteryApiImplementation(context)
        )
    }
}

class BatteryApiImplementation(private val context: Context) : BatteryApi {

    override fun getBatteryLevel(): BatteryResult {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { filter ->
            context.registerReceiver(null, filter)
        }

        val level: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

        return BatteryResult(
            level = (level * 100 / scale.toFloat()).toLong(), isCharging
        )
    }
}