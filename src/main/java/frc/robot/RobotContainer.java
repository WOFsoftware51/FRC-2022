// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.Supplier;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.HangarCommand;
import frc.robot.commands.Hangar_Ready_Command;
import frc.robot.commands.Intake_Command;
import frc.robot.commands.Intake_Deploy_Command;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.Shoot_Auton;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Hangar;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;



public class RobotContainer 
{
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final Shooter m_shooter = new Shooter();
  private final Hangar m_hangar = new Hangar();
  private final Intake m_intake = new Intake();

  private final XboxController m_controller = new XboxController(0);
  private final XboxController m_controller2 = new XboxController(1);

  public RobotContainer() 
  {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis s-> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
        m_drivetrainSubsystem,
        () -> -modifyAxis(m_controller.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND * Constants.DRIVE_SPEED,
        () -> -modifyAxis(m_controller.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND * Constants.DRIVE_SPEED,
        () -> -modifyAxis(m_controller.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND * Constants.DRIVE_SPEED ));
   // m_shooter.setDefaultCommand(new ShootCommand(m_shooter));
    m_hangar.setDefaultCommand(new HangarCommand(m_hangar,() -> -modifyAxis(m_controller2.getLeftY())));
    
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    // Back button zeros the gyroscop
    new Button(m_controller::getBackButton).whenPressed(m_drivetrainSubsystem::zeroGyroscope);
   // new Button(m_controller::getAButton).whileHeld(new ShootCommand(m_shooter));
    new Button(()-> m_controller2.getRightTriggerAxis() > 0.80).whileHeld(new ShootCommand(m_shooter));
   // new Button(m_controller2::g).whenPressed(m_hangar::claw1_open);
    new Button(m_controller2::getBButton).whileHeld(m_hangar::claw1_close);
    new Button(m_controller2::getYButton).whenPressed(m_hangar::claw2_close);
    new Button(m_controller2::getAButton).whileHeld(m_hangar::claw1_open);    
    new Button(m_controller2::getXButton).whenPressed(m_hangar::claw2_open);
    new Button(m_controller::getBButton).whileHeld(m_hangar::claw3_close);
    new Button(m_controller::getYButton).whenPressed(m_hangar::claw4_close);
    new Button(m_controller::getAButton).whileHeld(m_hangar::claw3_open);    
    new Button(m_controller::getXButton).whenPressed(m_hangar::claw4_open);

    new Button(m_controller2::getBackButton).whenPressed(new Shoot_Auton(m_shooter));

    new Button(m_controller::getLeftBumper).whenPressed(new Hangar_Ready_Command(m_hangar), true);
    //new Button(m_controller2::getYButton).whenPressed(new Hangar_Traverse_Command(m_hangar), true);
    //new Button(m_controller2::getXButton).whenPressed(new Hangar_Release_Command(m_hangar), true);
    //new Button(m_controller2::getAButton).whenPressed(new Hangar_Abort_Command(m_hangar), true);
    new Button(m_controller2::getLeftBumper).whileHeld(new Intake_Command(m_intake,true), true);
    new Button(m_controller2::getRightBumper).whileHeld(new Intake_Command(m_intake,false), true);
    new Button(m_controller::getStartButton).toggleWhenPressed(new Intake_Deploy_Command(m_intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

  private static double deadband(double value, double deadband) 
  {
    if (Math.abs(value) > deadband) 
    {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } 
       else 
      {
        return (value + deadband) / (1.0 - deadband);
      }
    }
     else 
    {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) 
  {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
      
    }
  }

