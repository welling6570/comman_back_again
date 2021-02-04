/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final Spark climber = new Spark(5);
  public Climber() {

  }
  public void set(double speed){
    climber.set(speed);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
