// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.CanID;
import frc.robot.Constants.Motors;
import frc.robot.subsystems.MotorSubsystem;

/** An example command that uses an example subsystem. */
public class MotorsCommand extends CommandBase {
  private final MotorSubsystem m_subsystem;
  private final CommandXboxController m_joystick;
  private final int m_leftStickAxis, m_rightStickAxis;

  /**
   * Creates a new MotorsCommand.
   *
   * <p>Runs 2 motors on the Y axis of the left and right sticks.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MotorsCommand(
      MotorSubsystem subsystem,
      CommandXboxController joystick,
      Axis leftStickAxis,
      Axis rightStickAxis) {
    m_subsystem = subsystem;
    m_joystick = joystick;
    m_leftStickAxis = leftStickAxis.value;
    m_rightStickAxis = rightStickAxis.value;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_subsystem.setMotor(
        CanID.TALON_LEFT_STICK, m_joystick.getRawAxis(m_leftStickAxis) * Motors.MAX_SPEED);
    m_subsystem.setMotor(
        CanID.TALON_RIGHT_STICK, m_joystick.getRawAxis(m_rightStickAxis) * Motors.MAX_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_subsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
