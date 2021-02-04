/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Magazine extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  private final VictorSPX magazineleft = new VictorSPX(3);
  private final VictorSPX magazineright = new VictorSPX(4);


  public void set(double left, double right) {
    magazineleft.set(ControlMode.PercentOutput, left);
    magazineright.set(ControlMode.PercentOutput, right);
  }




  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
