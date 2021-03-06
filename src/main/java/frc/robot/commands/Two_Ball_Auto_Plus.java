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
public class Two_Ball_Auto_Plus extends SequentialCommandGroup 
{
  /** Creates a new One_Ball_Auto. */
  public Two_Ball_Auto_Plus(Shooter m_shooter, Intake m_intake, DrivetrainSubsystem m_drivetrain) 
  {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Auton_Intialize(m_drivetrain,m_intake),
      new ParallelRaceGroup(new Intake_On_Auton(m_intake, 125), new Drive_Auton_Heading(m_drivetrain, 0.3, 0.0, 0.0, 62000.0, true)), //111111
      new Intake_Bounce_Auton(m_intake),
      new ParallelCommandGroup(new Drive_Auton_Heading(m_drivetrain, -0.3, -0.3, 0.00, 25000.11, true),new Intake_On_Auton(m_intake, 50)),
      new Drive_Auton_Heading(m_drivetrain, -0.3, -0.0, 0.0, 61000.11, false),
      new Shoot_Auton(m_shooter, 200),
      new Drive_Auton_Heading(m_drivetrain, 0.3, -0.0, -0.0, 32000.0, true)
    );
  }
}
