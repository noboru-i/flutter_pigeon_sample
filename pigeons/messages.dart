import 'package:pigeon/pigeon.dart';

class BatteryResult {
  BatteryResult({
    required this.level,
    required this.isCharging,
  });

  final int level;
  final bool isCharging;
}

@HostApi()
abstract class BatteryApi {
  @async
  BatteryResult getBatteryLevel();
}
