/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final TalonSRX inside = new TalonSRX(2);
  private final TalonSRX outside = new TalonSRX(1);
  double trim = 0.0;
  
  public Shooter() {
// This stuff sets up our shooter motors so they can get the right speed going.
    // kF is the feed-forward term, which gets our wheels close to the right speed
    // kP is the proportional term, which decides how quickly our bot adjusts to get the exact speed
    // kI is the integral term, which helps our bot adjust without getting stuck, or jumping around the rpm
    inside.config_kF(0, 0.045);
    inside.config_kP(0, 0.01);
    inside.config_kI(0, 0.00001);
    outside.config_kF(0, 0.0355);
    outside.config_kP(0, 0.01);
    outside.config_kI(0, 0.00001);
    //Our motors are wired in such a way that the encoders think wheels are spinning backwards when really,
    //they're spinning forward. The setSensorPhase() function lets us fix that problem.
    outside.setSensorPhase(true);
    inside.setSensorPhase(true);
  }

  public void shoot(double inrpm, double outrpm) {
    double rpm = 600.0 / 4096;
    inside.set(ControlMode.Velocity,  ((-inrpm - (trim * 25)) / rpm)); 
    outside.set(ControlMode.Velocity, ((outrpm + (trim * 25)) / rpm));
  }
  public void dialup() {
    trim = trim + 1;
    if (trim > 100) {
      trim = 100;
    };
  }
  public void dialdown() {
    trim = trim - 1;
    if (trim < -20) {
      trim = -20;
    };
  } 
public void dialzero() {
trim = 0;
   }
  public void stop() {
    inside.set(ControlMode.PercentOutput, 0);
    outside.set(ControlMode.PercentOutput, 0);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //These lines figure out how fast the shooter is going, then sends those numbers to the
    //shuffleboard.
    double insiderpm = inside.getSelectedSensorVelocity() / 4096.0 * 600;
    SmartDashboard.putNumber("inside speed", insiderpm);
    double outsiderpm = outside.getSelectedSensorVelocity() / 4096.0 * 600;
    SmartDashboard.putNumber("outside speed", outsiderpm);
    SmartDashboard.putNumber("trim", trim); 
  }
}
