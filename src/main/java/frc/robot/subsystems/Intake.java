/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final Spark intakemotor = new Spark(4);
  private final Spark armmotor = new Spark(6);
  public Intake() {

  }
  public void set(double out, double in){
      intakemotor.set((in - out) * .9); 
  }
  public void deploy(double out){
    armmotor.set(out); 
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
public Object stop() {
	return null;
}
}
