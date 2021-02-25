/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final Gyro m_gyro = new ADXRS450_Gyro();
  private final Spark left1 = new Spark(0);
  private final Spark left2 = new Spark(1);
  private final Spark right1 = new Spark(2);
  private final Spark right2 = new Spark(3);
  private final SpeedControllerGroup left = new SpeedControllerGroup(left1, left2);
  private final SpeedControllerGroup right = new SpeedControllerGroup(right1, right2);
  private final DifferentialDrive drivetrain = new DifferentialDrive(left, right);
  private final Encoder leftencoder = new Encoder(0, 1, false);
  private final Encoder rightencoder = new Encoder(2, 3, true);
  public Drive() {
    leftencoder.setDistancePerPulse(0.052359);
    rightencoder.setDistancePerPulse(0.052359);
  }

  public void arcadeDrive(double speed, double rotation, boolean sprint) {
    double finalspeed;
    double finalrot;
    if (sprint == false){
      finalspeed = speed * .75;
    } else {
      finalspeed = speed;
    }

      finalrot = rotation * 0.7;
    drivetrain.arcadeDrive(finalspeed, finalrot);
    SmartDashboard.putNumber("LeftEnc", leftencoder.getDistance());
    SmartDashboard.putNumber("RightEnc", rightencoder.getDistance());
    SmartDashboard.putNumber("Heading", getHeading());
    SmartDashboard.putNumber("Avedist", getDistance());

  }

  public void autoStraightDrive(double speed) {
    double finalspeed;
    double finalrot;
    double kPstraight = 0.25;
    finalspeed = speed;
    finalrot = (0 - getHeading() * kPstraight);
    //finalrot = Math.copySign(finalrot, finalspeed);
    drivetrain.arcadeDrive(finalspeed, finalrot);
    SmartDashboard.putNumber("LeftEnc", leftencoder.getDistance());
    SmartDashboard.putNumber("RightEnc", rightencoder.getDistance());
    SmartDashboard.putNumber("Heading", getHeading());
    SmartDashboard.putNumber("Avedist", getDistance());

  }
  public void resetEncoders() {
    leftencoder.reset();
    rightencoder.reset();
    m_gyro.reset();
  }

  public double getDistance() {
    //double avgdist = (leftencoder.getDistance() + rightencoder.getDistance()) / 2;
    double avgdist = leftencoder.getDistance();
    return avgdist;
  }

  public void zeroHeading() {
    m_gyro.reset();
  }

  public double getHeading() {
    //return Math.IEEEremainder(m_gyro.getAngle(), 360);
    return m_gyro.getAngle();
  }

  public double getTurnRate() {
    return m_gyro.getRate();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
