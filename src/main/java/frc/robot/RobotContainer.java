/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoMag;
import frc.robot.commands.AutoShoot;
import frc.robot.commands.AutoCurve;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Magazine;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Intake m_intake = new Intake();
  private final Shooter m_shooter = new Shooter();
  private final Drive m_drive = new Drive();
  private final Magazine m_magazine = new Magazine();
  private final Climber m_climber = new Climber();
  XboxController xbdrive = new XboxController(0);
  XboxController xbshoot = new XboxController(1);
  private final AutoDrive m_driveTest = new AutoDrive(25, m_drive);

  private final SequentialCommandGroup m_turnTest = new SequentialCommandGroup(
    new TurnToAngle(90, m_drive).beforeStarting(m_drive::zeroHeading)
    );

  private final SequentialCommandGroup m_looptest = new SequentialCommandGroup(
    new AutoCurve(90, 0.6, 0.6, m_drive).beforeStarting(m_drive::zeroHeading)
    );

  private final SequentialCommandGroup m_looptestv2 = new SequentialCommandGroup(
    new AutoCurve(90, 0.6, 0.6, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(90, 0.6, 0.6, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(90, 0.6, 0.6, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(90, 0.6, 0.6, m_drive).beforeStarting(m_drive::zeroHeading)
    );
    
  private final SequentialCommandGroup m_dontouchmabarrelstest = new SequentialCommandGroup(
    new AutoDrive(95, m_drive).beforeStarting(m_drive::resetEncoders),
    new AutoCurve(360, 0.69, 0.73, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoDrive(53, m_drive).beforeStarting(m_drive::resetEncoders),
    new AutoCurve(-310, 0.69, 0.78, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoDrive(46, m_drive).beforeStarting(m_drive::resetEncoders),
    new AutoCurve(-227, 0.69, 0.78, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoDrive(223, m_drive).beforeStarting(m_drive::resetEncoders)
    );

    private final SequentialCommandGroup m_slolonmtest = new SequentialCommandGroup(
    new AutoCurve(-75, 0.5, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(80, 1, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-270, 0.85, 0.75, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(80, 1, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-75, 0.5, 0.85, m_drive).beforeStarting(m_drive::zeroHeading)
    );

    private final SequentialCommandGroup m_Bouncetest = new SequentialCommandGroup(
    new AutoCurve(-90, 0.5, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-15, -1, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-150, -0.25, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-15, -1, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-15, 1, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-150, 0.25, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-15, 1, 0.85, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoCurve(-90, -0.5, 0.85, m_drive).beforeStarting(m_drive::zeroHeading)
    );

  private final SequentialCommandGroup m_sequencetest = new SequentialCommandGroup(
   new AutoDrive(24, m_drive).beforeStarting(m_drive::resetEncoders),
   new TurnToAngle(-30, m_drive).beforeStarting(m_drive::zeroHeading),
   new AutoDrive(10, m_drive).beforeStarting(m_drive::resetEncoders)
   );

   private final SequentialCommandGroup m_hopetishworks = new SequentialCommandGroup(
    new AutoDrive(24, m_drive).beforeStarting(m_drive::resetEncoders),
    new TurnToAngle(60, m_drive).beforeStarting(m_drive::zeroHeading),
    new AutoDrive(70, m_drive).beforeStarting(m_drive::resetEncoders),
    new TurnToAngle(-60, m_drive).beforeStarting(m_drive::zeroHeading),
    new ParallelCommandGroup(
      new AutoShoot(1150, 3350, m_shooter),
      new AutoDrive(28, m_drive).beforeStarting(m_drive::resetEncoders)
      ),
    new AutoMag(-1, m_magazine)
    );

    //The next line is a four-step auton, but has been crunched to one line for demonstration purposes.
    private final ParallelCommandGroup m_moooveshoot = new ParallelCommandGroup(new AutoShoot(1150, 3350, m_shooter), new AutoDrive(84, m_drive).beforeStarting(m_drive::resetEncoders), new SequentialCommandGroup(new  WaitCommand(5),new AutoMag(-1, m_magazine)));

    private final ParallelCommandGroup m_shoetest = new ParallelCommandGroup(
    new AutoShoot(1150, 3350, m_shooter),
    new AutoMag(-1, m_magazine)
    );

    private final ParallelCommandGroup m_ejecttest = new ParallelCommandGroup(
    new AutoShoot(1150, -3350, m_shooter),
    new AutoMag(-1, m_magazine)
    );

  

  SendableChooser<Command> m_chooser = new SendableChooser<>();


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    UsbCamera fisheye = CameraServer.getInstance().startAutomaticCapture(0);
    UsbCamera hd3k = CameraServer.getInstance().startAutomaticCapture(1);
    fisheye.setResolution(160, 120);
    fisheye.setFPS(15);
    hd3k.setResolution(160, 120);
    hd3k.setFPS(15);
    m_drive.setDefaultCommand(
      new RunCommand(() -> m_drive.arcadeDrive(xbdrive.getRawAxis(1), xbdrive.getRawAxis(4), xbdrive.getBumper(GenericHID.Hand.kRight)), m_drive));
    m_intake.setDefaultCommand(
    new RunCommand(() -> m_intake.set(xbshoot.getRawAxis(3), xbshoot.getRawAxis(2)), m_intake));
    m_shooter.setDefaultCommand(
    new RunCommand(() -> m_shooter.stop(), m_shooter));
    m_magazine.setDefaultCommand(
    new RunCommand(() -> m_magazine.set(0,0), m_magazine));
    m_climber.setDefaultCommand(
      new RunCommand(() -> m_climber.set(0), m_climber));
      // Add commands to the autonomous command chooser
      m_chooser.setDefaultOption("Turn Test", m_turnTest);
      m_chooser.addOption("Drive Test", m_driveTest);
      m_chooser.addOption("Sequence Test", m_sequencetest);
      m_chooser.addOption("hopetishworks", m_hopetishworks);
      m_chooser.addOption("shoetest", m_shoetest);
      m_chooser.addOption("moooveshoot", m_moooveshoot);
      m_chooser.addOption("ejecttest", m_ejecttest);
      m_chooser.addOption("looptest", m_looptest);
      m_chooser.addOption("looptestv2", m_looptestv2);
      m_chooser.addOption("dontouchmabarrelstest", m_dontouchmabarrelstest); 
      m_chooser.addOption("slolontest", m_slolonmtest);
      m_chooser.addOption("Bouncetest", m_Bouncetest);


  
      // Put the chooser on the dashboard
      Shuffleboard.getTab("Autonomous").add(m_chooser);
}
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  //The values of the shooter are going to change based on the condition of the power cell, if it is new they will need to be higher. 
  private void configureButtonBindings() {
    new JoystickButton(xbshoot, Button.kA.value).whileHeld( new RunCommand(() -> m_shooter.shoot(1150, 3350)));
    new JoystickButton(xbshoot, Button.kY.value).whileHeld( new RunCommand(() -> m_shooter.shoot(2000, -2500)));
    new JoystickButton(xbshoot, Button.kBumperLeft.value).whileHeld( new RunCommand(() -> m_magazine.set(0, 1)));
    new JoystickButton(xbshoot, Button.kBumperRight.value).whileHeld( new RunCommand(() -> m_magazine.set(-1, 0)));
    //new JoystickButton(xbdrive, Button.kY.value).whileHeld( new RunCommand(() -> m_climber.set(1)));
    new JoystickButton(xbshoot, Button.kA.value).whenReleased( new RunCommand(() -> m_shooter.stop()));
    new JoystickButton(xbshoot, Button.kBumperLeft.value).whenReleased(() -> m_magazine.set(0, 0));
    new JoystickButton(xbshoot, Button.kBumperRight.value).whenReleased(() -> m_magazine.set(0, 0));
    new JoystickButton(xbshoot, Button.kY.value).whenReleased(() -> m_shooter.stop());
    //new JoystickButton(xbdrive, Button.kY.value).whenReleased(() -> m_climber.set(0));
    new JoystickButton(xbshoot, Button.kX.value).whileHeld(() -> m_intake.deploy(1.0));
    new JoystickButton(xbshoot, Button.kX.value).whenReleased(() -> m_intake.deploy(0.0));
    new JoystickButton(xbshoot, Button.kB.value).whileHeld(() -> m_intake.deploy(-1.0));
    new JoystickButton(xbshoot, Button.kB.value).whenReleased(() -> m_intake.deploy(0.0));
   // new JoystickButton(xbshoot, Button.kStart.value).whenPressed( new RunCommand(() -> m_shooter.dialup(1)));
    //new JoystickButton(xbshoot, Button.kBack.value).whenReleased( new RunCommand(() -> m_shooter.dialup(-1)));
    new JoystickButton(xbshoot, Button.kStart.value).whenPressed(new InstantCommand(m_shooter::dialup, m_shooter));
    new JoystickButton(xbshoot, Button.kBack.value).whenPressed(new InstantCommand(m_shooter::dialdown, m_shooter));
    new JoystickButton(xbdrive, Button.kStart.value).whileHeld( new RunCommand(() -> m_climber.set(-1)));
    new JoystickButton(xbshoot, Button.kStickRight.value).whenPressed(new InstantCommand(m_shooter::dialzero, m_shooter));
    //new JoystickButton(xbdrive, Button.kStart.value).whenReleased(() -> m_climber.set(0));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
