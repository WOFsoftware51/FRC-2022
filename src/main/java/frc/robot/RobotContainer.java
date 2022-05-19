// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.Drive_Auton_Heading;
import frc.robot.commands.Five_Ball_Auton;
import frc.robot.commands.HangarCommand;
import frc.robot.commands.Hangar_Grab_1_Command;
import frc.robot.commands.Hangar_Grab_2_Command;
import frc.robot.commands.Hangar_Ready_Command;
import frc.robot.commands.Hangar_Release_1_Command;
import frc.robot.commands.Hangar_Release_2_Command;
import frc.robot.commands.Hangar_Release_Both_Command;
import frc.robot.commands.Intake_Command;
import frc.robot.commands.Intake_Deploy_Command;
import frc.robot.commands.One_Ball_Auto;
import frc.robot.commands.Preload_Ball;
import frc.robot.commands.ShootCommand;
import frc.robot.commands.ShootCommandLow;
import frc.robot.commands.Shoot_Auton_Spit;
import frc.robot.commands.Three_Ball_Auton;
import frc.robot.commands.Two_Ball_Auto;
import frc.robot.commands.Two_Ball_Auto_Plus;
import frc.robot.commands.Two_One_Auton;
import frc.robot.commands.Two_Two_Auton;
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
  private final SendableChooser<Integer> s_chooser = new SendableChooser<>();
  private final SendableChooser<Integer> a_chooser = new SendableChooser<>();


  public RobotContainer() 
  {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis s-> left and right movement
    // Right stick X axis -> rotation

    SmartDashboard.putData("Shot Speed", s_chooser);
    //s_chooser.setDefaultOption("4500",15360);
  
    s_chooser.addOption("1500", 5120);
    s_chooser.addOption("1600", 5461);
    s_chooser.addOption("1700", 5803);
    s_chooser.addOption("1800", 6144);
    s_chooser.addOption("1900", 6485);
    s_chooser.addOption("1925", 6570);
    s_chooser.setDefaultOption("1950", 6655);
    s_chooser.addOption("1975", 6740);
    s_chooser.addOption("2000", 6826);
    s_chooser.addOption("2100", 7168);
    s_chooser.addOption("2500",8533);
    s_chooser.addOption("2700", 9216);
    s_chooser.addOption("2900", 9899);
    s_chooser.addOption("3100", 105815);
    s_chooser.addOption("4500", 15360);



    SmartDashboard.putData("Auton", a_chooser);

    a_chooser.setDefaultOption("One Ball", 1);
    a_chooser.addOption("Two Ball", 2);
    a_chooser.addOption("HP 2 Ball", 3);
    a_chooser.addOption("HP 5 Ball", 4);
    a_chooser.addOption("Hang 2 plus 1", 5);
    a_chooser.addOption("Hang 2 plus 2", 6);
    a_chooser.addOption("Three ball ", 7);

    a_chooser.addOption("Sit", 9);



    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand
    (
        m_drivetrainSubsystem,
        () -> -modifyAxis(m_controller.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
        () -> -modifyAxis(m_controller.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
        () -> -modifyAxis(m_controller.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND)
    );
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
    new Button(()-> m_controller2.getRightTriggerAxis() > 0.80).whileHeld(new ShootCommand(m_shooter,() -> s_chooser.getSelected()));
    new Button(()-> m_controller2.getLeftTriggerAxis() > 0.80).whileHeld(new ShootCommandLow(m_shooter));
   // new Button(m_controller2::g).whenPressed(m_hangar::claw1_open);
    new Button(m_controller2::getBButton).whenPressed(new Hangar_Grab_2_Command(m_hangar));
    new Button(m_controller2::getYButton).whenPressed(new Hangar_Release_2_Command(m_hangar));
    new Button(m_controller2::getAButton).whenPressed(new Hangar_Grab_1_Command(m_hangar));    
    new Button(m_controller2::getXButton).whenPressed(new Hangar_Release_1_Command(m_hangar));
    new Button(m_controller2::getBackButton).whenPressed(new Hangar_Release_Both_Command(m_hangar));

    new Button(()-> m_controller.getRightTriggerAxis() > 0.80).whenPressed(m_drivetrainSubsystem::BoostOn);   
    new Button(()-> m_controller.getRightTriggerAxis() > 0.80).whenReleased(m_drivetrainSubsystem::BoostOff);

    //new Button(m_controller2::getBackButton).whenPressed(new Drive_Auton(m_drivetrainSubsystem, 0.3, 0.0, 0.0, 111111.11));

    new Button(m_controller::getLeftBumper).whenPressed(new Hangar_Ready_Command(m_hangar), true);
    //new Button(m_controller2::getYButton).whenPressed(new Hangar_Traverse_Command(m_hangar), true);
    //new Button(m_controller2::getXButton).whenPressed(new Hangar_Release_Command(m_hangar), true);
    //new Button(m_controller2::getAButton).whenPressed(new Hangar_Abort_Command(m_hangar), true);
    new Button(m_controller2::getLeftBumper).whileHeld(new Intake_Command(m_intake,true), true);
    new Button(m_controller2::getLeftBumper).whenPressed(new Preload_Ball(m_shooter), true);
    new Button(m_controller2::getRightBumper).whileHeld(new Intake_Command(m_intake,false), true);
    new Button(m_controller::getXButton).toggleWhenPressed(new Intake_Deploy_Command(m_intake));
    //new Button(m_controller::getRightBumper).whenPressed(new Drive_Auton_Heading(m_drivetrainSubsystem, 0.3, -0.0, 0.0, 62000.0, true));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() 
  {
    // An ExampleCommand will run in autonomous
    switch (a_chooser.getSelected()) {
        case 1: return new One_Ball_Auto(m_shooter, m_drivetrainSubsystem, m_intake);
        case 2: return new Two_Ball_Auto(m_shooter, m_intake, m_drivetrainSubsystem);
        case 3: return new Two_Ball_Auto_Plus(m_shooter, m_intake, m_drivetrainSubsystem);
        case 4: return new Five_Ball_Auton(m_shooter, m_intake, m_drivetrainSubsystem);
        case 5: return new Two_One_Auton(m_shooter, m_intake, m_drivetrainSubsystem);
        case 6: return new Two_Two_Auton(m_shooter, m_intake, m_drivetrainSubsystem);
        case 7: return new Three_Ball_Auton(m_shooter, m_intake, m_drivetrainSubsystem);
        case 9: return new InstantCommand();
        
    
      default: return new One_Ball_Auto(m_shooter, m_drivetrainSubsystem, m_intake);
    }
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

