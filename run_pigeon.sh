dart run pigeon \
  --input pigeons/messages.dart \
  --dart_out lib/gen/messages.dart \
  --swift_out ios/Classes/messages.g.swift \
  --kotlin_out android/src/main/kotlin/com/example/pigeon_plugin/Messages.g.kt \
  --kotlin_package "com.example.pigeon_plugin"
