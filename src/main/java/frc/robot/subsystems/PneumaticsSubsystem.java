// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CanID;
import frc.robot.Constants.Pneumatics;
import java.util.HashMap;
import java.util.Map;

public class PneumaticsSubsystem extends SubsystemBase {
  private Map<Integer, Solenoid> solenoids;

  /** Creates a new ExampleSubsystem. */
  public PneumaticsSubsystem() {
    solenoids = new HashMap<>();

    for (int i = 0; i < Pneumatics.DEFAULT_CHANNELS.length; i++) {
      if (solenoids.containsKey(Pneumatics.DEFAULT_CHANNELS[i])) {
        DriverStation.reportError(
            "There are 2 solenoids with the same channel. Remove one of them from DEFAULT_CHANNELS in Constants.java",
            false);
      } else {
        Solenoid solenoid =
            new Solenoid(
                CanID.CTRE_PCM_CAN_ID,
                PneumaticsModuleType.CTREPCM,
                Pneumatics.DEFAULT_CHANNELS[i]);
        solenoid.set(false);

        solenoids.put(Pneumatics.DEFAULT_CHANNELS[i], solenoid);
      }
    }
  }

  /**
   * Command to toggle a solenoid on the given channel.
   *
   * @return The channel to toggle.
   */
  public CommandBase toggleSolenoid(int channel) {
    if (solenoids.containsKey(channel)) {
      return runOnce(() -> solenoids.get(channel).toggle());
    } else {
      return runOnce(
          () ->
              DriverStation.reportError(
                  "Tried to toggle a solenoid on a channel that isn't in DEFAULT_CHANNELS in Constants.java",
                  false));
    }
  }
}
