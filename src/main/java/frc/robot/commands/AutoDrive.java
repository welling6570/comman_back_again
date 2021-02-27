package frc.robot.commands;

/**----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.Drive;


/**
 * A command that will turn the robot to the specified angle.
 */
public class AutoDrive extends PIDCommand {
  /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   */
  public AutoDrive(double targetDistanceInches, double asdclamp, Drive drive) {
    super(
        new PIDController(1, 0.1, 0),
        // Close loop on heading
        drive::getDistance,
        // Set reference to target
        targetDistanceInches,
        // Pipe output to turn robot
        output -> drive.autoStraightDrive(-MathUtil.clamp(output, -asdclamp, asdclamp)),
        // Require the drive
        drive);

    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController()
        .setTolerance(1.5, 10);
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atSetpoint();
  }
}