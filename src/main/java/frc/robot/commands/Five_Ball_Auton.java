// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider usin GetAbsolutePosition this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Five_Ball_Auton extends SequentialCommandGroup 
{
  /** Creates a new One_Ball_Auto. */
  public Five_Ball_Auton(Shooter m_shooter, Intake m_intake, DrivetrainSubsystem m_drivetrain) 
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(new Auton_Intialize(m_drivetrain,m_intake),  new Shoot_Auton(m_shooter,60)),
      new ParallelRaceGroup(new Intake_On_Auton(m_intake, 200), new Drive_Auton_Heading(m_drivetrain, 0.4, 0.12, 33.0, 88000.0, true)), //127-bot"
      new Auton_Step(1),
      new ParallelRaceGroup(new Intake_On_Auton(m_intake, 200), new Drive_Auton_Heading(m_drivetrain, 0.0, -0.4, -90.0, 135000.0, true), new Auton_Preload_Ball(m_shooter, 200)),  //130"
      new Auton_Step(1),
      new ParallelRaceGroup(new Intake_On_Auton(m_intake, 200), new Drive_Auton_Heading(m_drivetrain, -0.15, 0.3, 0.0, 123001.0, true), new Auton_Preload_Ball(m_shooter, 200)),  //80"
      new Auton_Step(1),
      //new Drive_Auton_Heading(m_drivetrain, -0.3, -0.0, 0.0, 34000.0, false),                                                              //50"
      new Shoot_Auton(m_shooter,150),
      new Drive_Auton_Heading(m_drivetrain, 0.4, 0.0, 0.0, 38000.0, true), 
      new Auton_Step(1),
      new ParallelRaceGroup(new Intake_On_Auton(m_intake, 1000), new Drive_Auton_Heading(m_drivetrain, 0.30, -0.6, -45.0, 204000.0, false)), //250"
      new Auton_Step(1),
      new Auton_Set_Gyro(m_drivetrain, -142),
      new Auton_Step(1),
      new ParallelCommandGroup(new Intake_On_Auton(m_intake, 1000), new Drive_Auton_Heading(m_drivetrain, 0.0, 0.0, -142.0, 500000.0, false)) //250"
      
    );
  }
}
