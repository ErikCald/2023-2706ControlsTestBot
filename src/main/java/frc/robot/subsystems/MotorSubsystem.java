// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Motors;
import java.util.HashMap;
import java.util.Map;

public class MotorSubsystem extends SubsystemBase {
  private Map<Integer, WPI_TalonSRX> motors;

  /** Creates a new ExampleSubsystem. */
  public MotorSubsystem() {
    motors = new HashMap<>();

    for (int i = 0; i < Motors.DEFAULT_MOTORS.length; i++) {
      if (motors.containsKey(Motors.DEFAULT_MOTORS[i])) {
        DriverStation.reportError(
            "There are 2 motors with the same can id. Remove one of them from DEFAULT_MOTORS in Constants.java",
            false);
      } else {
        WPI_TalonSRX talon = new WPI_TalonSRX(Motors.DEFAULT_MOTORS[i]);
        talon.configFactoryDefault();
        talon.setNeutralMode(NeutralMode.Brake);
        motors.put(Motors.DEFAULT_MOTORS[i], talon);
      }
    }
  }

  public void setMotor(int canId, double percentOutput) {
    if (motors.containsKey(canId)) {
      motors.get(canId).set(percentOutput);
    } else {
      DriverStation.reportError(
          "Tried to control a motor on a can id that isn't in DEFAULT_CHANNELS in Constants.java",
          false);
    }
  }

  public void stopMotors() {
    for (WPI_TalonSRX motor : motors.values()) {
      motor.stopMotor();
    }
  }
}
