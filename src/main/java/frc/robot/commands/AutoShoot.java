package frc.robot.commands;

/**----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;


/**
 * A command that will turn the robot to the specified angle.
 */
public class AutoShoot extends CommandBase {
  private final Shooter m_shooter;
  private double in;
  private double out;
  /**
   * Turns to robot to the specified angle.
   *
  
   */
  public AutoShoot(double inrpm, double outrpm, Shooter Shooter) {
    m_shooter = Shooter;
    addRequirements(m_shooter);
    out = outrpm;
    in = inrpm;

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
   
  }

  public void execute() {
    // TODO Auto-generated method stub
    super.execute();
    m_shooter.shoot(in, out);
  }
  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return true;
  }
}