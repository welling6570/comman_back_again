package frc.robot.commands;

/**----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;


/**
 * A command that will turn the robot to the specified angle.
 */
public class AutoMag extends CommandBase {
  private final Magazine m_magazine;
  double magspeed;
  /**
   * Turns to robot to the specified angle.
   *
  
   */
  public AutoMag(double speed, Magazine magazine) {
    m_magazine = magazine;
    magspeed = speed;
    addRequirements(m_magazine);

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
   
  }

  @Override

  public void execute() {
    m_magazine.set(magspeed, 0);
  }
  public boolean isFinished() {
    // End when the controller is at the reference.
    return false;
  }
}