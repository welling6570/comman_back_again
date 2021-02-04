package frc.robot.commands;

/**----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;


/**
 * A command that will turn the robot to the specified angle.
 */
public class AutoIntake extends CommandBase {
private final Intake m_intake;
double intakespeed; 
  /**
   * Turns to robot to the specified angle.
   *
  
   */
  public AutoIntake(double speed, Intake intake) {
    m_intake = intake;
    intakespeed = speed;
    addRequirements(m_intake);
    intake.set(speed, 0);

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
   
  }

  @Override

  public void execute() {
    m_intake.set(intakespeed, 0);
  }

  public boolean isFinished() {
    // End when the controller is at the reference.
    return false;
  }
}