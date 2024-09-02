//
//  BatteryApi.swift
//  Runner
//
//  Created by noboru-i on 2024/09/01.
//

import Foundation

class BatteryApiImplementation: BatteryApi {
    func getBatteryLevel() throws -> BatteryResult {
        UIDevice.current.isBatteryMonitoringEnabled = true
        let level = UIDevice.current.batteryLevel
        
        let isCharging = UIDevice.current.batteryState == .charging
        return BatteryResult(level: Int64(level), isCharging: isCharging)
    }
}
